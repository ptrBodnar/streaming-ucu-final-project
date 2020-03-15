import java.util.concurrent.TimeUnit

import org.apache.kafka.streams.scala.ImplicitConversions._
import org.apache.kafka.streams.scala._
import org.apache.kafka.streams.{KafkaStreams, StreamsConfig}
import org.slf4j.LoggerFactory
import Serdes._

object Stream extends App {

  val logger = LoggerFactory.getLogger(getClass)


  //  val BrokerList: String = System.getenv("KAFKA_BROKERS")
  val BrokerList: String = "localhost:9092"
  val Topic = "merged_data"
  val props = new Properties()
  props.put("bootstrap.servers", BrokerList)
  props.put("client.id", "cpv provider")
  props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
  props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")


  val builder = new StreamsBuilder

  val cpv = builder.stream[String, String]("cpv")
  val procuraments = builder.stream[String, String]("procuraments")


  cpv.foreach { (k, v) =>
    logger.info(s"record processed $k->$v")
  }

  testStream.to(Topic)

  val streams = new KafkaStreams(builder.build(), props)
  streams.cleanUp()
  streams.start()

  sys.addShutdownHook {
    streams.close(10, TimeUnit.SECONDS)
  }

  object Config {
    val KafkaBrokers = "KAFKA_BROKERS
}
