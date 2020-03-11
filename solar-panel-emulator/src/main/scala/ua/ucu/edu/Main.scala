package ua.ucu.edu

//import ua.ucu.edu.DataHandler
//import ua.ucu.edu.kafka.{Config, DummyDataProducer}
import org.apache.kafka.clients.producer._
import java.util.Properties

import Main.{Topic, getClass, producer}
import org.apache.kafka.clients.producer.{Callback, KafkaProducer, ProducerRecord, RecordMetadata}
import org.slf4j.LoggerFactory
import ua.ucu.edu.kafka.Config
import ua.ucu.edu.kafka.DummyDataProducer.logger





object Main extends App {

  val logger = LoggerFactory.getLogger(getClass)


  val BrokerList: String = System.getenv("KAFKA_BROKERS")
  val Topic = "cpv"
  val props = new Properties()
  props.put("bootstrap.servers", BrokerList)
  props.put("client.id", "cpv provider")
  props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
  props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")

  logger.info("initializing producer")

  val producer = new KafkaProducer[String, String](props)

  val testMsg = "hot weather"

//
//
  while (true) {
    Thread.sleep(5000)
      val handler = new DataHandler()
      val a = handler.parseJSON().toString()
    logger.info(s"[$Topic] $a")
//    println(a.toString())
    val data = new ProducerRecord[String, String](Topic, a)
    producer.send(data, (metadata: RecordMetadata, exception: Exception) => {
      logger.info(metadata.toString, exception)
    })
  }
//
//  producer.close()

//  new Runnable {
//    override def run(): Unit = {
//      logger.debug("weather request")
//            val handler = new DataHandler()
//            val a = handler.parseJSON()
//      logger.info(s"[$Topic]")
//      val data = new ProducerRecord[String, String](Topic, a.toString())
//      producer.send(data, (metadata: RecordMetadata, exception: Exception) => {
//        logger.info(metadata.toString, exception)
//      })
//      // ???
//      producer.close()
//
//      // todo - ask weather api and send data to kafka topic - recommended format is json - or you can come up with simpler string-based protocol
//    }
//  }

//  DummyDataProducer.pushTestData()
}
