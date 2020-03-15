package ua.ucu.edu

//import java.ues
import java.util.concurrent.TimeUnit
import java.util.Properties

import org.apache.kafka.streams.scala.ImplicitConversions._
import org.apache.kafka.streams.scala._
import org.apache.kafka.streams.{KafkaStreams, StreamsConfig}
import org.slf4j.LoggerFactory
import Serdes._
import org.apache.kafka.streams.kstream.{JoinWindows, Joined, TimeWindows}
import play.api.libs.json._
import org.apache.kafka.streams.scala.ImplicitConversions._




// dummy app for testing purposes
object DummyStreamingApp extends App {

  val logger = LoggerFactory.getLogger(getClass)

  val BrokerList: String = System.getenv("KAFKA_BROKERS")
//  val BrokerList: String = "localhost:9092"
  val Topic = "merged_data"
  val props = new Properties()
  props.put("bootstrap.servers", BrokerList)
  props.put("client.id", "cpv provider")
  props.put("application.id", "smth")
  props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
  props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")


  val builder = new StreamsBuilder

  val cpv = builder.stream[String, String]("cpv")
  val procuraments = builder.stream[String, String]("procuraments")
  val cpvRisk = builder.stream[String, String]("cpvRisk")


  val firstDataset = procuraments.leftJoin(cpv)((lV: String, rV: String)=> s"$rV, $lV",
      windows = JoinWindows.of(50000))
//  (
      Joined.keySerde(Serdes.String)
      .withValueSerde(Serdes.String)
//  )
  val secondDataset = firstDataset.leftJoin(cpvRisk)((lV: String, rV: String)=> s"$rV, $lV",
  windows = JoinWindows.of(20000))

  secondDataset.foreach { (k, v) =>
          logger.info(s"key $k, values are = $v")
          println(s"key $k, values are = $v")
    }
  secondDataset.to(Topic)

  val streams = new KafkaStreams(builder.build(), props)
  streams.cleanUp()
  streams.start()

  sys.addShutdownHook {
    streams.close(10, TimeUnit.SECONDS)
  }

  object Config {
    val KafkaBrokers = "KAFKA_BROKERS"
  }
}
