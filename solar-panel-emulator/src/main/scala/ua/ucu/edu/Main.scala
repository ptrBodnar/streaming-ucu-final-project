package ua.ucu.edu
so

object Main extends App {


  val handler = new DataHandler()
  val a = handler.parseJSON()

  println(a.map(_.description))


//  DummyDataProducer.pushTestData()
}
