package code.model

import net.liftweb._
import mongodb._
import util.Props
import com.mongodb.{ServerAddress, Mongo}

object AdminDb extends MongoIdentifier {
  val jndiName = "admin"
}

object MongoConfig {
  def mUser = Props.get("mongo.user")
  def mPass = Props.get("mongo.pass")


  def init: Unit = {
    val srvr = new ServerAddress(
      Props.get("mongo.host", "127.0.0.1"),
      Props.getInt("mongo.port", 27017)
    )
    MongoDB.defineDb(DefaultMongoIdentifier, new Mongo(srvr), "mygame")
    MongoDB.defineDb(AdminDb, new Mongo(srvr), "admin")
  }
}