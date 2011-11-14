package code.comet

/**
 * Our commnd table. Is a Pattern Matching statement,
 * but will probably have to be turned into an if else if due to
 * a bug in Scala. 
 */

import net.liftweb._
import http._
import actor._
import code.model._

object CommandActor extends LiftActor with ListenerManager {
  private var commands = Vector("")

  def createUpdate = commands


  // This is our command interface.
  // We will want to split it up per .split(" ")
  def processCmd(cmdArgs: String) = {
    val cmdValues = cmdParse(cmdArgs)
    cmdValues(0) match {
      case "ls" => doList
      // movements in CS
      case "next" => "Jump Next"
      case "back" => "Jump Back"
      case "right" => "Jump Right"
      case "left" => "Jump Left"
      case x => "Command not found: %s" format(x.toString)
    }
  }

  def cmdParse(cmd: String): List[String] = {
    cmd.split(" ").toList
  }

  def doList = {"../\n./"}

  override def lowPriority = {
    case cmd: String => commands :+= processCmd(cmd)
    updateListeners()
  }
}