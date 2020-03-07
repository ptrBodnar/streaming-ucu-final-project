package ua.ucu.edu

import ua.ucu.edu.kafka.DummyDataProducer

import scala.io.BufferedSource
import play.api.libs.json._

import ua.ucu.edu.DataHandler


object Main extends App {


  val handler = new DataHandler()

  val a = handler.parseJSON()

  println(a.map(_.description))


//  println(tweets_array(0).get("Код CPV").get)

//  DummyDataProducer.pushTestData()
}
