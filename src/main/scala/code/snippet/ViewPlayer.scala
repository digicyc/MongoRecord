package code.snippet

import net.liftweb._
import http.{SHtml, WiringUI}
import http.SHtml._
import util.ValueCell
import util.Helpers._
import common.{Box, Empty, Full}
import http.js.JsCmds.Noop
import http.js.jquery.JqWiringSupport

import com.foursquare.rogue.Rogue._

import code.model.Player
import xml.{Text, NodeSeq}
import javax.mail.FetchProfile

class ViewPlayer {
  private val productValue = ValueCell[Box[Player]](Empty)
  private var userAlias = ""

  private def pullPlayer = productValue.lift(_.map(x =>
    Player where (_.user_alias eqs userAlias) fetch(1)
  ))

  def playerSearch = {
    
  }

  def render =
    "*" #> " "

  def lowLevels = {
    val players = Player where (_.level < 5) fetch(10)
    ".lines" #> players.map(x =>
      <tr><td>{x.user_alias.is}</td><td>{x.level.is}</td></tr>)
  }
}