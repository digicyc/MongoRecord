package code.comet

import net.liftweb._
import http._
import util._
import Helpers._

class CommandComet extends CometActor with CometListener {
  private var commands: Vector[String] = Vector()

  def registerWith = CommandActor

  override def lowPriority = {
    case command: Vector[String] => commands = command
    reRender()
  }

  def render =
    "li *" #> commands & ClearClearable
}