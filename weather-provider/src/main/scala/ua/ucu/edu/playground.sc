import play.api.libs.json._
import scalaj.http.{Http, HttpRequest, HttpResponse}

val request: HttpRequest = Http("https://public.api.ea2.openprocurement.net/api/2/auctions").param("offset", "2020-01-01")
val response = request.asString
val result: String = response.body

val values: JsValue = Json.parse(result)



val parsed = (values \ "data" ).as[Array[Map[String, String]]]
parsed.map(_("id"))

