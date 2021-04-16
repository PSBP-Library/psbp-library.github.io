lazy val root = project
  .in(file("."))
  .settings(
    name := "PSBP",
    version := "1.0.0",

    scalaVersion := "3.0.0-RC2",
    
    // scalaVersion := dottyLatestNightlyBuild.get,

    libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.2.3",
    libraryDependencies ++= Seq(
       "com.typesafe.akka" %% "akka-actor-typed" % "2.6.13",
       "com.typesafe.akka" %% "akka-slf4j"       % "2.6.13"
    ).map(_.withDottyCompat(scalaVersion.value))
        
  )
