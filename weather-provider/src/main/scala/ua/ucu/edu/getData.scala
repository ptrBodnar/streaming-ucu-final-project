package ua.ucu.edu

import scalaj.http.{Http, HttpRequest, HttpResponse}
import play.api.libs.json._
import scala.util.parsing.json.JSON
import ua.ucu.edu.Procurements

class getData {

//  def ParseResponse(resp: String): List[String]= {
//    val values: JsValue = Json.parse(resp)
//    println("before")
//    val parsed = (values \ "data" ).as[List[Map[String, String]]]
//    println("after")
//
//    parsed.map(_("id"))
//  }

//  case class Procurements(status: String, auctionID: String, date: String,
//                          title: String, cpv: String, price: Float)


  def getDataPrepared(): List[Procurements] = {
    val procurementList = parseProcurementList()
    val casesClasses = procurementList.map(d => parseSale(d))

    casesClasses
  }

  def parseSale(procurement_id: String): Procurements = {
    val request: HttpRequest = Http("https://public.api.ea2.openprocurement.net/api/2/auctions/" + procurement_id)
    val response = request.asString
    val result: String = response.body

    val values: JsValue = Json.parse(result)

    val status = (values  \ "data" \ "procurementMethod").as[String]
    val auctionID = (values  \ "data" \  "auctionID").as[String]
    val date = (values  \ "data" \  "date").as[String]
    val title = (values  \ "data" \  "title").as[String]
    val cpv = (values  \ "data" \  "items" \ 0 \ "classification" \ "id").as[String]
    val price = (values  \ "data" \  "value" \ "amount").as[Float]

    val proc = new Procurements(status, auctionID, date, title, cpv, price)

    proc

  }

  def parseProcurementList(): List[String] = {

//  http get request to prozorro sales list
    val request: HttpRequest = Http("https://public.api.ea2.openprocurement.net/api/2/auctions").param("offset", "2020-01-01")
    val response = request.asString
    val result: String = response.body

//    parsing json to get id's
    val values: JsValue = Json.parse(result)
    val parsed = (values \ "data" ).as[List[Map[String, String]]]

    parsed.map(_("id"))

  }

//  def getAuction(): procurements = {
//
//  }



}
