import org.apache.spark.{SparkConf, SparkContext }
import org.apache.spark.ml.regression.LinearRegression
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.spark.rdd.RDD
import org.apache.spark.sql._
import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.{Row, SaveMode, SparkSession}


object SPARK{

    def main(args: Array[String]): Unit = {
      if (args.length < 2) {
        System.err.println("Usage: SparkMLExample <input> <iterations>")
        System.exit(1)
      }

      val path = args(0)
      val iters = args(1).toInt
      val conf = new SparkConf()
        .setAppName("Spark ML Example")
        //          .setMaster("local[2]")
        .set("spark.io.compression.codec", "lz4")
      val sc = new SparkContext(conf)

      conf.set("spark.cleaner.ttl", "120000")
      //      conf.set("spark.driver.allowMultipleContexts", "true")

      val spark = SparkSession
        .builder()
        .config(conf)
        .getOrCreate()

      Logger.getRootLogger.setLevel(Level.WARN)

      val training = spark.read.format("libsvm").load(path)//.toDF("price", "text", "label")

      //println(path)
      //println("***********"*100)
      training.show()
      val lr = new LinearRegression()
        .setMaxIter(iters)
        .setRegParam(0.3)
        .setElasticNetParam(0.8)

      // Fit the model
      val lrModel = lr.fit(training)

      // Print the coefficients and intercept for linear regression
      println(s"Coefficients: ${lrModel.coefficients} Intercept: ${lrModel.intercept}")
      //

      // Summarize the model over the training set and print out some metrics
      val trainingSummary = lrModel.summary
      println(s"numIterations: ${trainingSummary.totalIterations}")
      println(s"objectiveHistory: [${trainingSummary.objectiveHistory.mkString(",")}]")
      //trainingSummary.residuals.show()
      println(s"RMSE: ${trainingSummary.rootMeanSquaredError}")
      println(s"r2: ${trainingSummary.r2}")
      /* RESULTS */
      println("********************************* \n"*10)
      println("predicted_house_price = intercept +"+lrModel.coefficients(0)+" x num_of_rooms +"+lrModel.coefficients(1)+"x num_of_baths +"+lrModel.coefficients(2)+" x size_of_house +"+lrModel.coefficients(3)+"  x one_if_pool_zero_otherwise"  )

    }


}
