package ua.ucu.edu

//import ua.ucu.edu.DataHandler
import ua.ucu.edu.kafka.{Config, DummyDataProducer}
import org.apache.kafka.clients.producer._
import java.util.Properties
import org.apache.kafka.clients.producer.{Callback, KafkaProducer, ProducerRecord, RecordMetadata}


import ua.ucu.edu.kafka.DummyDataProducer.logger





object Main extends App {
  val BrokerList: String = "localhost:9092"
  val Topic = "test_topic_out"
  val props = new Properties()
  props.put("bootstrap.servers", BrokerList)
  props.put("client.id", "weather-provider")
  props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
  props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")

  logger.info("initializing producer")

  val producer = new KafkaProducer[String, String](props)

  val testMsg = "hot weather"

//

  while (true) {
    Thread.sleep(1000)
      val handler = new DataHandler()
      val a = handler.parseJSON()
    val t = a(0).description
    logger.info(s"[$Topic] $t")
    val data = new ProducerRecord[String, String](Topic, testMsg)
    producer.send(data, (metadata: RecordMetadata, exception: Exception) => {
//      logger.info(metadata.toString, exception)
    })
  }

  producer.close()
//  DummyDataProducer.pushTestData()
}
