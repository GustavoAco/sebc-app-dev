name := "SPARK"
version := "0.1"
scalaVersion := "2.11.9"
javacOptions ++= Seq("-source", "1.8", "-target", "1.8")


resolvers += "Cloudera Repository" at "https://repository.cloudera.com/artifactory/cloudera-repos/"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "2.3.0.cloudera2" % "provided",
  "org.apache.spark" %% "spark-sql" % "2.3.0.cloudera2" % "provided",
  "org.apache.spark" %% "spark-streaming-flume" % "2.3.0.cloudera2" % "provided",
  "org.apache.spark" %% "spark-streaming" % "2.3.0.cloudera2" % "provided",
  "org.apache.spark" %% "spark-mllib" % "2.3.0.cloudera2"% "provided",
  "org.apache.spark" %% "spark-sql" % "2.3.0.cloudera2"% "provided",
  "org.apache.spark" %% "spark-streaming" % "2.3.0.cloudera2" % "provided",
  "org.apache.hbase" % "hbase-it" % "1.2.0-cdh5.14.3-SNAPSHOT",
  "org.apache.hbase" % "hbase-client" % "1.2.0-cdh5.14.3-SNAPSHOT",
  "org.apache.hbase" % "hbase-server" % "1.2.0-cdh5.14.3-SNAPSHOT",
  "org.apache.hbase" % "hbase-common" % "1.2.0-cdh5.14.3-SNAPSHOT",
  "org.apache.hbase" % "hbase-hadoop-compat" % "1.2.0-cdh5.14.3-SNAPSHOT",
  "org.scala-lang" % "scala-reflect" % scalaVersion.value
)
