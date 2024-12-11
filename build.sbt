import sbt.Keys.*
import sbt.*

import org.typelevel.scalacoptions.ScalacOptions
import org.typelevel.scalacoptions.ScalaVersion.V3_0_0
import scala.Ordering.Implicits.*

val moduleSettings: Seq[Def.Setting[_]] = Seq(
  organization := "com.example.mycompany",
  scalaVersion := "3.3.4", // LTS version
  tpolecatScalacOptions += ScalacOptions.release("9"),
  tpolecatScalacOptions += ScalacOptions.sourceFuture,
  tpolecatScalacOptions += ScalacOptions.other("-rewrite"),
  tpolecatScalacOptions += ScalacOptions.advancedOption("max-inlines", List("200"), version => version >= V3_0_0),
  Test / compile / tpolecatExcludeOptions += ScalacOptions.warnNonUnitStatement,
  Test / compile / tpolecatExcludeOptions += ScalacOptions.warnValueDiscard,
  libraryDependencies ++= Seq("org.scalatest" %% "scalatest" % "3.2.19" % Test),
  Test / parallelExecution := false
)

val `module-a` = (project in file("./modules/module-a"))
  .settings(
    name := "module-a"
  )
  .settings(moduleSettings)

val `module-b` = (project in file("./modules/module-b"))
  .settings(
    name := "module-b"
  )
  .settings(moduleSettings)

val `bug-root` = (project in file("."))
  .settings(
    name := "bug-root",
    test / skip := true,
    compile / skip := true,
    Test / parallelExecution := true
  )
  .aggregate(
    `module-a`,
    `module-b`
  )
