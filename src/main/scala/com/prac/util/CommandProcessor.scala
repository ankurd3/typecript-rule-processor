package com.prac.util

import java.io.File

import scala.sys.process.{Process, ProcessLogger}
import scala.util.{Failure, Success, Try}

sealed trait CommandProcessor {
  def cmdPath: String
  implicit val logger:ProcessLogger = ProcessLogger((str:String) => println(str))
  def run(args:String*)(dir: File) : CommandProcessor = {
    val cmd = cmdPath +: args
    println(s"running command :- ${cmd mkString " "} in directory ${dir}")
    CommandProcessor.execute(cmd,dir)
    this
  }

}

object CommandProcessor {

  def apply(cmdName: String): CommandProcessor = new Impl(cmdName)

  def execute(cmdLine: Seq[String], cwd:File)(implicit logger: ProcessLogger): Unit = {
    Try(Process(cmdLine,Some(cwd)) ! logger) match {
      case Success(value) => if (value != 0) println("Command Execution Failed...") else println("Command Executed...")
      case Failure(e : Exception) => println("Error in Executing the Command")
    }
  }

  case class Impl(cmdPath: String) extends CommandProcessor
}
