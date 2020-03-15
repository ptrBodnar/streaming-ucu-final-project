package ua.ucu.edu

import scala.io.BufferedSource
import play.api.libs.json.{JsPath, JsValue, Reads, Writes, __}
import play.api.libs.functional.syntax._

import play.api.libs.json.Json

class DataHandler {

  case class CodesCPV(code: String, description: String)
  case class CPVRisk(code: String, risk: Int)


  object CodesCPV {
    implicit val reads: Reads[CodesCPV] = (
      (JsPath \ "cpv").read[String] ~
        (JsPath \ "description").read[String]
//        (JsPath \ "risk").read[Int]
      ) (CodesCPV.apply _)
  }

  object CPVRisk {
    implicit val reads: Reads[CPVRisk] = (
      (JsPath \ "cpv").read[String] ~
        (JsPath \ "risk").read[Int]

      ) (CPVRisk.apply _)
  }

  def parseJSON(): Tuple2[List[CodesCPV], List[CPVRisk]] = {
    val json: BufferedSource = scala.io.Source.fromResource("cpvDescriptionOfProcurements.json")
    val json_str: String = try json.mkString finally json.close()

    val risk: BufferedSource = scala.io.Source.fromResource("cpvRisks.json")
    val risk_str: String = try risk.mkString finally risk.close()

    val CPVarray = Json.parse(json_str).as[List[CodesCPV]]
    val RiskArray = Json.parse(risk_str).as[List[CPVRisk]]


    (CPVarray, RiskArray)
  }

}
