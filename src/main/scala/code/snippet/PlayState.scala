package code.snippet

/**
 * Just playing with Objects and Classes and how they hold their state.
 * Objects are global for EVERYONE.
 * Classes are specific to the user and until they refresh.
 */

import code.lib._
import net.liftweb.util.Helpers._
import net.liftweb.http._
import SHtml._

class PlayState extends StatefulSnippet {
  var pHolder = ""
  lazy val cTestState = new CTestState("0")

  def processState() = {
    OTestState.ostate_is = pHolder
    cTestState.state_is = pHolder
  }

  def dispatch = {
    case "render" => render
    case "results" => myRes
  }

  def render = {
    "#state_field" #> text(pHolder, pHolder = _) &
    "type=submit" #> onSubmitUnit(processState)
  }

  def myRes = {
    "#object_state *+" #> OTestState.ostate_is.toString &
    "#class_state *+" #> cTestState.state_is.toString &
    "#pholder *" #> pHolder.toString
  }
}