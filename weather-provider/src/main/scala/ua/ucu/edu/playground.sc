import play.api.libs.json._
import scalaj.http.{Http, HttpRequest, HttpResponse}

val request: HttpRequest = Http("https://public.api.ea2.openprocurement.net/api/2/auctions/86faded9e2ac4a43845157f762a983b2")
val response = request.asString
val result: String = response.body

val values: JsValue = Json.parse(result)

case class procurements(status: String, sm: String)


val a = (values  \ "data" \ "procurementMethod").as[String]
val proc = new procurements(a, "sm")

proc.status