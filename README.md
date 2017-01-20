# kafka-spark-streaming-dsti

Spark Streaming Job which does run on the amazonaws cluster.
Woking with HDP 2.5.3.0-37 (spark 1.6.2)

## package
```bash
mvn package
```

## Upload the jar
after the mvn package two packages will be created in the target directory.
- kafka-spark-streaming-dsti.jar
- kafka-spark-streaming-jar-with-dependencies.jar

upload the second jar on the client (the one you are going to run spark-submit from).
You can use scp command or filezilla to do it.

## Run the jar
Submit the jar through spark-submit in yarn-cluster mode
```bash
spark-submit --master yarn-cluster spark_streaming-1.0-jar-with-dependencies.jar --driver-cores 1 --num-executors 2
```

you can use the tracking url directly to chheckout the state of your application.
