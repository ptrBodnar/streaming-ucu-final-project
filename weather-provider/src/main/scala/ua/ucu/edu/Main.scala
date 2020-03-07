//package ua.ucu.edu

import ua.ucu.edu.kafka.DummyDataProducer
import org.slf4j.LoggerFactory

import scala.concurrent.duration
import scala.language.postfixOps
import ua.ucu.edu.getData
import scalaj.http.{Http, HttpResponse}

object Main extends App {

  val logger = LoggerFactory.getLogger(getClass)

  logger.info("======== Weather Provider App Init ========")

  val sm = new getData()
//  println(sm.parseAPI())
  val a = sm.parseProcurementList()
  println(a(2))


//  val values = sm.parseAPI()
//
//
//  val a = (values\ "data" \ 0  \ "id").get
//
//  println(a)


//  scheduler.schedule(5 seconds, 10 seconds,
  new Runnable {
    override def run(): Unit = {
      logger.debug("weather request")
      // ???
//      val response: HttpResponse[String] = Http("https://openbudget.gov.ua/api/reports/expenses/")
//        .param("monthTo","1").param("year", "2020").param("budgetType", "NATIONAL").param("monthFrom", "1").param("fundType", "TOTAL").asString

      DummyDataProducer.pushTestData()
      // todo - ask weather api and send data to kafka topic - recommended format is json - or you can come up with simpler string-based protocol
    }
  }

//  println("hello")
//  val response: HttpResponse[String] = Http("http://foo.com/search").param("q","monkeys").asString
//  response.body
//  response.code
//  response.headers
//  response.cookies

  //  println(response)

  //)

  // for testing purposes only
//  DummyDataProducer.pushTestData()
}
