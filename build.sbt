name := "MongoRecord"
 
scalaVersion := "2.9.1"
 
seq(webSettings: _*)

resolvers += "Java.net Maven2 Repository" at "http://download.java.net/maven/2/"

libraryDependencies ++= {
  val liftVersion = "2.4-M4"
  Seq(
    "com.foursquare" %% "rogue" % "1.0.27" intransitive(),
    "net.liftweb" %% "lift-mongodb" % liftVersion,
    "net.liftweb" %% "lift-mongodb-record" % liftVersion % "compile->default",
    "net.liftweb" %% "lift-webkit" % liftVersion % "compile->default",
    "net.liftweb" %% "lift-mapper" % liftVersion % "compile->default",
    "net.liftweb" %% "lift-wizard" % liftVersion % "compile->default")
}

// Customize any further dependencies as desired
libraryDependencies ++= Seq(
  "org.eclipse.jetty" % "jetty-webapp" % "7.5.1.v20110903=8" % "container", // For Jetty 7
  "org.scala-tools.testing" % "specs_2.9.0" % "1.6.8" % "test", // For specs.org tests
  "junit" % "junit" % "4.8" % "test->default", // For JUnit 4 testing
  "javax.servlet" % "servlet-api" % "2.5" % "provided->default",
  "com.h2database" % "h2" % "1.2.138", // In-process database, useful for development systems
  "ch.qos.logback" % "logback-classic" % "0.9.26" % "compile->default" // Logging
)
