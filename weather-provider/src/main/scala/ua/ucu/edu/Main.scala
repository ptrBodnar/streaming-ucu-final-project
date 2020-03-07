//package ua.ucu.edu

import ua.ucu.edu.kafka.DummyDataProducer
import org.slf4j.LoggerFactory

import scala.concurrent.duration
import scala.language.postfixOps
import ua.ucu.edu.getData
import scalaj.http.{Http, HttpResponse}

import scala.io.BufferedSource

object Main extends App {

  val logger = LoggerFactory.getLogger(getClass)

  logger.info("======== Prozorro Sales ========")

//  val sm = new getData()
////  println(sm.parseAPI())
//  val a = sm.getDataPrepared()
//  println(a)



//  scheduler.schedule(5 seconds, 10 seconds,
  new Runnable {
    override def run(): Unit = {
      logger.debug("weather request")
      // ???
//      val response: HttpResponse[String] = Http("https://openbudget.gov.ua/api/reports/expenses/")
//        .param("monthTo","1").param("year", "2020").param("budgetType", "NATIONAL").param("monthFrom", "1").param("fundType", "TOTAL").asString

//      DummyDataProducer.pushTestData()
      // todo - ask weather api and send data to kafka topic - recommended format is json - or you can come up with simpler string-based protocol
    }
  }


  // for testing purposes only
//  DummyDataProducer.pushTestData()
}
