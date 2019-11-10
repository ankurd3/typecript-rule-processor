package com.prac

import java.nio.file.Paths

import com.prac.service.{Npm, Tsc}

object RuleProcessorApp extends App {
  val npmDir = "/Users/ankurd3/git/rule-processor/src/main/resources/rule-node-home"
  val ruleProject = Paths.get("/Users/ankurd3/git/rule-service/src")

  val npm = Npm("npm")
  val workingDirectory = Paths.get(npmDir)

  npm install workingDirectory

  val tscDir = workingDirectory
    .resolve("node_modules")
    .resolve("typescript")
    .resolve("bin")
    .resolve("tsc")

  println(s"Tsc Dir : ${tscDir}")
  val tsc = Tsc(tscDir.toString)

  tsc compile ruleProject
}
