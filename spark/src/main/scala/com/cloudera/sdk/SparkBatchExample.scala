import org.apache.spark.{SparkContext, SparkConf}
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.spark.rdd.RDD
object SPARK{
  def main(args: Array[String]): Unit = {
    if (args.length < 1) {
      System.err.println("Usage: SparkBatchExample <path>")
      System.exit(1)
    }

    val path = args(0)
    val conf = new SparkConf()
      .setAppName("Spark Batch Example")
      .setMaster("local[2]")
      .set("spark.io.compression.codec", "lz4")
    val sc = new SparkContext(conf)
    conf.set("spark.cleaner.ttl", "120000")
    //println("*****"*100)
    //println("************READ FILE *****************")
    //println("*****"*100)
    //println(args(0))
    val df=sc.textFile("hdfs://clouderan2.ricohits.ga"+path)
    val data =df.map(line => line.split(",")).map(arr => (arr(0), arr(1)))
    //println("*****"*100)
    //println("************ORDENO*****************")
    //println("*****"*100)
    //val orden=data.sortByKey()
    val orden=data.coalesce(1).sortByKey()
    orden.saveAsTextFile(path+"SPARK")

    //println("********"*100)


  }

}
