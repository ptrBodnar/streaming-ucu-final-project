package ua.ucu.edu

import scalaj.http.{Http, HttpRequest, HttpResponse}
import play.api.libs.json._

import scala.util.parsing.json.JSON

class getData {

  def ParseResponse(resp: String): String = {
    val values: JsValue = Json.parse(resp)
    val parsed = (values \ "data" \ 0 \ "id").get.toString()

    parsed.toString()
  }

  case class procurements(id: String)

  def parseAPI(): procurements = {

    val request: HttpRequest = Http("https://public.api.ea2.openprocurement.net/api/2/auctions").param("offset", "2020-01-01")
    val response = request.asString
    val result: String = response.body

    new procurements(ParseResponse(result))
//    result match {
//      // Matches if jsonStr is valid JSON and represents a Map of Strings to Any
//      case Some(map: Map[String, String]) => map
//      case None => Map()
//      case other => Map()
//    }

  }

//  def handleWeatherRequest(request: String): (String,WeatherData) = {
//
//    val response_body: JsValue = Json.parse(request)
//
//    val location = (response_body \ "list" \ 0 \ "name").get.toString().stripPrefix("\"").stripSuffix("\"")
//    val temperature = (response_body \ "list" \ 0 \ "main" \ "temp").get.toString().toFloat
//    val humidity = (response_body \ "list" \ 0 \ "main" \ "humidity").get.toString().toInt
//    val pressure = (response_body \ "list" \ 0 \ "main" \ "pressure").get.toString().toInt
//    val cloudiness = (response_body \ "list" \ 0 \ "clouds" \ "all").get.toString().toInt
//
//    val now = new Date().getTime
//    val result = (location, new WeatherData(
//      timestamp = now,
//      locationName = location,
//      locationTemperature = temperature,
//      locationHumidity = humidity,
//      locationPressure = pressure,
//      locationCloudiness = cloudiness
//    ))
//    result
//  }


}
