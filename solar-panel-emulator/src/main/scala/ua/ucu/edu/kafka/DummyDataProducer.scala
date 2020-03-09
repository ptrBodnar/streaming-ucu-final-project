package ua.ucu.edu.kafka

import java.util.Properties

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}
import org.slf4j.{Logger, LoggerFactory}

// delete_me - for testing purposes
object DummyDataProducer {

  val logger: Logger = LoggerFactory.getLogger(getClass)

  // This is just for testing purposes
<<<<<<< HEAD
  def pushTestData(): Unit = {
    val BrokerList: String = "localhost:9092"
    val Topic = "test_topic_out"
=======
  def pushTestData(a: Any): Unit = {
    println(a)
    val BrokerList: String = System.getenv(Config.KafkaBrokers)
    val Topic = "sensor-data"
>>>>>>> cfcccf053578e10da418f12d5d70281fc23109a9

    val props = new Properties()
    props.put("bootstrap.servers", BrokerList)
    props.put("client.id", "solar-panel-1")
    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")

    logger.info("initializing producer")

    val producer = new KafkaProducer[String, String](props)

    val testMsg = "12.3702"

    while (true) {
      Thread.sleep(10000)
      logger.info(s"[$Topic] $testMsg")
      val data = new ProducerRecord[String, String](Topic, testMsg)
      producer.send(data)
    }

    producer.close()
  }
}

object Config {
  val KafkaBrokers = "KAFKA_BROKERS"
}