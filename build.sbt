name := "cassandra-scala"

version := "1.0"

scalaVersion := "2.11.8"


libraryDependencies ++= {
  val cassandraVersion = "3.0.0"

  val specs2Version = "2.3.11"
  val scalatestVersion = "2.2.4"

  Seq(
    "com.datastax.cassandra" % "cassandra-driver-core" % cassandraVersion,

    "org.specs2" %% "specs2-core" % specs2Version % "test",
    "org.specs2" %% "specs2-junit" % specs2Version % "test",
    "org.specs2" %% "specs2-mock" % specs2Version % "test",
    "org.scalatest" %% "scalatest" % scalatestVersion % "test"
  )
}