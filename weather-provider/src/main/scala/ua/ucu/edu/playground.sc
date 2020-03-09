import spray.json._
//import play.api.libs.json._
import scala.io.Source._
import scala.io.BufferedSource

val tweets_json: BufferedSource = scala.io.Source.fromResource("data.json")
val tweets_json_str: String = try tweets_json.mkString finally tweets_json.close()
//var tweets_array: JsValue = tweets_json_str.stripMargin.parseJson

tweets_json_str