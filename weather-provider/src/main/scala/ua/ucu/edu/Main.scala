//package ua.ucu.edu

import java.util.Properties

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord, RecordMetadata}
import ua.ucu.edu.kafka.{Config, DummyDataProducer}
import org.slf4j.LoggerFactory

import scala.concurrent.duration
import scala.language.postfixOps
import ua.ucu.edu.getData
import scalaj.http.{Http, HttpResponse}
import ua.ucu.edu.kafka.DummyDataProducer.logger

import scala.io.BufferedSource

object Procurements extends App {

  val logger = LoggerFactory.getLogger(getClass)

  logger.info("======== Prozorro Sales ========")

  val BrokerList: String = System.getenv("KAFKA_BROKERS")
//  val BrokerList: String = "localhost:9092"
  val Topic = "procuraments"
  val props = new Properties()
  props.put("bootstrap.servers", BrokerList)
  props.put("client.id", "procurement provider")
  props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
  props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")

  logger.info("initializing producer")

  val producer = new KafkaProducer[String, String](props)

// temporary 
  val sm = new getData()
  val a = sm.getDataPrepared()

  while (true) {
//    println("started waiting")
    Thread.sleep(8000)
//    we need to uncomment this part in order to send requests to api
//    for each run of the application, however we decided to do this only 
//    once in order not to bother API to much and not get banned for sending 
//    too many requests. We can uncomment it any time to start sending requests
//    per each run of the script.

//    val sm = new getData()
//    val a = sm.getDataPrepared().toString()
//    println("will be logging")
    logger.info(s"[$Topic] $a")

    a.foreach((d) => {
      val data = new ProducerRecord[String, String](Topic, d.cpv, d.auctionID)
//      println(d.cpv)
//      println(d.auctionID)
      producer.send(data, (metadata: RecordMetadata, exception: Exception) => {
        logger.info(metadata.toString, exception)
      })
    })
//    val data = new ProducerRecord[String, String](Topic, a)
//    producer.send(data, (metadata: RecordMetadata, exception: Exception) => {
//      logger.info(metadata.toString, exception)
//    })
  }

//  producer.close()


//  scheduler.schedule(5 seconds, 10 seconds,
//  new Runnable {
//    override def run(): Unit = {
//      logger.debug("weather request")
//      val sm = new getData()
//      val a = sm.getDataPrepared()
//      println(a.toString())
//      logger.info(s"[$Topic]")
//      val data = new ProducerRecord[String, String](Topic, a.toString())
////      producer.send(data, (metadata: RecordMetadata, exception: Exception) => {
////        logger.info(metadata.toString, exception)
////      })
//      // ???
//      producer.close()
//
//      //      val response: HttpResponse[String] = Http("https://openbudget.gov.ua/api/reports/expenses/")
////        .param("monthTo","1").param("year", "2020").param("budgetType", "NATIONAL").param("monthFrom", "1").param("fundType", "TOTAL").asString
//
////      DummyDataProducer.pushTestData()
//      // todo - ask weather api and send data to kafka topic - recommended format is json - or you can come up with simpler string-based protocol
//    }
//  }


  // for testing purposes only
//  DummyDataProducer.pushTestData()
}
