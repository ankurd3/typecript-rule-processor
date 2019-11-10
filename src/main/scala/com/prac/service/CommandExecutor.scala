package com.prac.service


import java.io.File
import java.nio.file.Path

import com.prac.util.CommandProcessor
sealed trait Command

sealed class Npm(processor:CommandProcessor) extends Command{
  def install(npmTargetDir: Path) = processor.run("install")(npmTargetDir.toFile)
}
object Npm {
  def apply(path:String): Npm = new Npm(CommandProcessor(path:String))
}

sealed class Tsc(processor:CommandProcessor) extends Command{
  def compile(workingDir:Path) = processor.run("--project",workingDir.toFile.getAbsolutePath,"--outDir",s"${workingDir.toFile.getAbsolutePath}${File.separator}target")(workingDir.toFile)
}
object Tsc {
  def apply(path:String): Tsc = new Tsc(CommandProcessor(path:String))
}

