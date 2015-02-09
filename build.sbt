name := "products"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  "net.sf.barcode4j" % "barcode4j" % "2.0",
  "mysql" % "mysql-connector-java" % "5.1.18"
)     

play.Project.playScalaSettings
