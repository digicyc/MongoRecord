package code.model

import com.foursquare.rogue.Rogue._

import net.liftweb.mongodb.record._
import net.liftweb.mongodb.record.field._
import net.liftweb.record.field._
import net.liftweb.record._
import org.bson.types._
import org.joda.time.{DateTime, DateTimeZone}


class Player private () extends MongoRecord[Player] with MongoId[Player] {
  def meta = Player

  object user_alias extends StringField(this, 255)
  object email extends OptionalEmailField(this, 100)
  object level extends IntField(this, 1)
  object date_added extends DateTimeField(this)
  object rep extends IntField(this, 1)
  object char_desc extends TextareaField(this, 250)
  object logged_in extends BooleanField(this, false)
}
object Player extends Player with MongoMetaRecord[Player] {
  override def collectionName = "player"
}
