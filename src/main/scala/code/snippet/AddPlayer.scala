package code.snippet

import code.model._
import net.liftweb.util.Helpers._
import net.liftweb.http._

object AddPlayer extends StatefulSnippet {
  private var userAlias = ""
  private var userEmail = ""
  private var userDesc = ""

  def dispatch = {
    case "render" => render
    case "playerconfirm" => playerConfirm
  }

  private def process() = {
    val newPerson = Player.createRecord
    if(userAlias.length > 1)
      newPerson.user_alias(userAlias)
    if(userEmail.length > 1)
      newPerson.email(userEmail)

    newPerson.level(1)
    newPerson.save

  }

  def render = {
    "name=user_alias" #> SHtml.text(userAlias, userAlias = _, "id" -> "user_alias") &
    "name=user_email" #> SHtml.text(userEmail, userEmail = _, "id" -> "user_email") &
    "name=user_desc" #> SHtml.textarea(userDesc, userDesc = _, "id" -> "user_desc") &
    "type=submit" #> SHtml.onSubmitUnit(process)
  }

  def playerConfirm =
    "#user_alias" #> "Infos"
}