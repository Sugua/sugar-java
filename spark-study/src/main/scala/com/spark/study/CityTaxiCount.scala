package com.spark.study

import org.apache.spark.{SparkConf, SparkContext}

/**
  * 城区出租车统计
  */
object CityTaxiCount {
  def main(args: Array[String]): Unit = {
    //本地环境运行
    val conf = new SparkConf().setMaster("local").setAppName("CityTaxiCount")
    val sc = new SparkContext(conf)

    val taxi_gps = sc.textFile("./data/taxi_gps.txt")
    val district = sc.textFile("./data/district.txt")


    val cities=district.collect().map(city => {

      val cityInfo = city.split(",")

      val cityTaxi = taxi_gps.map(taxi => {
        val taxiInfo = taxi.split(",")

        val x = taxiInfo(4).toDouble - cityInfo(1).toDouble
        val y = taxiInfo(5).toDouble - cityInfo(2).toDouble
        val distance = Math.sqrt(x * x + y * y)

        (cityInfo(0), taxiInfo(0), distance)
      }).filter(_._3 < cityInfo(3).toDouble)
//      cityTaxi.saveAsTextFile("./cityTaxi2/"+cityInfo(0))

      (cityInfo(0),cityTaxi.count())

    })

    cities.foreach(city=>{
      println("城区："+city._1+"，车辆位置点数："+city._2)
    }
    )

  }

}
