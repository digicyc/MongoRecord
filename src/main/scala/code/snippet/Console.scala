package code.snippet

import net.liftweb._
import http.js.JsCmds.SetValById
import http.js.JsCmds.FocusOnLoad
import http.SHtml
import http.S
import util.Helpers._
import code.comet.CommandActor

object Console {
  
  def render = SHtml.onSubmit(cmd => {
    CommandActor ! cmd
    SetValById("command_in", "")
  })
}