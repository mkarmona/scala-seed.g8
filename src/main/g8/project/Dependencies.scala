import sbt._

object Dependencies {
  lazy val scalaLoggingDep = "ch.qos.logback" % "logback-classic" % "1.2.3"
  lazy val scalaLogging = "com.typesafe.scala-logging" %% "scala-logging" % "3.9.0"
  lazy val scalaTest = "org.scalatest" %% "scalatest" % "3.0.5"
  lazy val scopt = "com.github.scopt" %% "scopt" % "3.7.0"
  lazy val scalaCheck = "org.scalacheck" %% "scalacheck" % "1.13.5"
  lazy val configLB = "com.github.pureconfig" %% "pureconfig" % "0.9.1"
}
