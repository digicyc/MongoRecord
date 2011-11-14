package code.snippet

import scala.xml.{NodeSeq, Text}
import net.liftweb._
import http._
import js.JsCmds.SetHtml
import util._
import wizard._
import code.model._

import com.foursquare.rogue.Rogue._

object NewPlayer extends Wizard {
  val screen1 = new Screen {
    val name = field("Name", "")
    val age = field("Age", 0, minVal(13, "Too young!"))
  }

  val screen2 = new Screen {
    val rad = radio("Which Hat", "Hacker", List("Hacker", "Agent"))

    val email1 = field("Email:", "")
    val email2 = field("Confirm Email:", "", emailMatch _)

    val pwd1 = password("Password", "", valMinLen(6, "Password to short"))
    val pwd2 = password("Password Re-Enter", "", mustMatch _)

    def mustMatch(s: String): List[FieldError] =
      if (s != pwd1.is) "Password don't match" else Nil
    def emailMatch(s: String): List[FieldError] =
      if (s != email1.is) "Email don't match" else Nil
  }

  val screen3 = new Screen {

    val ta = textarea("Description", "")

  }

  def finish() {
    val addedPlayer = Player.createRecord.user_alias(screen1.name.is)
    addedPlayer.email(screen2.email2.is)
    addedPlayer.level(1).rep(1)
    addedPlayer.char_desc(screen3.ta.is)
    addedPlayer.save

    SetHtml("user_name", Text(screen1.name.is))
  }
}