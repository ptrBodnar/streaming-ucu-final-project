package ua.ucu.edu

import scalaj.http.{Http, HttpRequest, HttpResponse}
import play.api.libs.json._
import scala.util.parsing.json.JSON

class getData {

//  def ParseResponse(resp: String): List[String]= {
//    val values: JsValue = Json.parse(resp)
//    println("before")
//    val parsed = (values \ "data" ).as[List[Map[String, String]]]
//    println("after")
//
//    parsed.map(_("id"))
//  }

  case class procurements(id: String, dateModified: String)

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
