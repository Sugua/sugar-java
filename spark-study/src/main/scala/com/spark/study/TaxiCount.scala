package com.spark.study

import org.apache.spark.{SparkConf, SparkContext}

/**
  * 出租车统计
  */
object TaxiCount {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local").setAppName("TaxiCount")
    val sc = new SparkContext(conf)

    //设置数据路径
    val text = sc.textFile("./data/taxi_gps.txt")

    //将文本数据按行处理，每行只截取车辆标识
    val carId = text.map(line => {
      line.split(",")(0)
    })

    //去重复统计车辆
    val countCar = carId.distinct().count()
    println("出租车的总数是："+countCar)




  }












}
