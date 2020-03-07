package ua.ucu.edu

import scala.io.BufferedSource

import play.api.libs.json.{JsPath, Json, Reads, Writes, __}
import play.api.libs.functional.syntax._


class DataHandler {

  case class CodesCPV(code: String, description: String, risk: Int)

  object CodesCPV {

    implicit val reads: Reads[CodesCPV] = (
      (JsPath \ "Код CPV").read[String] ~
        (JsPath \ "Опис").read[String] ~
        (JsPath \ "risk").read[Int]
      ) (CodesCPV.apply _)
  }

  def parseJSON(): List[CodesCPV] = {
    val json: BufferedSource = scala.io.Source.fromResource("cpvRisk.json")
    val json_str: String = try json.mkString finally json.close()

    var CPVarray = Json.parse(json_str).as[List[CodesCPV]]

    CPVarray
  }

}
