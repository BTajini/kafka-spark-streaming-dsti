

import kafka.serializer.StringDecoder
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.{SparkConf, Logging}


object Streamer extends Logging {

  def main(args : Array[String]): Unit = {

    /**
      setup spark context
     */
    val conf = new SparkConf().setAppName("The Best Streamer")
    val ssc = new StreamingContext(conf, Seconds(1))

    /**
    create kafka list
     */
    var kafka_list = ""
    kafka_list = kafka_list + "ec2-52-211-98-209.eu-west-1.compute.amazonaws.com:6667"+","
    kafka_list = kafka_list + "ec2-52-214-216-145.eu-west-1.compute.amazonaws.com:6667"+","
    kafka_list = kafka_list + " ec2-52-213-241-169.eu-west-1.compute.amazonaws.com:6667"
    val kafkaParams: Map[String, String] = Map[String, String]("metadata.broker.list" -> kafka_list)

    /**
      * Create kafka topic configuration
      */
    val topicsSet = Set("lucas_kafka_topic")
    /**
      *  DStream Configuration and creation
      */
    val messages = KafkaUtils.createDirectStream[String, String, StringDecoder, StringDecoder](ssc, kafkaParams, topicsSet)

    /**
      * Now you can use the messages as an array of RDD
      */
    messages.foreachRDD { x =>
      x.foreachPartition { y =>

        /**
          * computation runs on the tweets
          */

        y.foreach(record => {
          System.out.println("has received -> " + record)

        })
      }
    }

    /**
      * spark streaming lifecycle
      */
    ssc.start()
    ssc.awaitTermination()
  }
}