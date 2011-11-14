package bootstrap.liftweb

import net.liftweb._
import util._
import Helpers._

import common._
import http._
import sitemap._
import Loc._
import mapper._

import mongodb.{MongoDB, DefaultMongoIdentifier, MongoIdentifier}
import com.mongodb.{Mongo, ServerAddress}

import code.model._
import org.h2.engine.Database

import org.slf4j.LoggerFactory

/**
 * A class that's instantiated early and run.  It allows the application
 * to modify lift's environment
 */
class Boot {
  def logger = LoggerFactory.getLogger("Boot")


  def boot {

    logger.info("[X][X][X] Setting up boot()")
    // where to search snippet
    LiftRules.addToPackages("code")


    logger.info("[X][X][X] Init MongConfig")
    // MongoDB Connection Setup
    MongoConfig.init
    logger.info("[X][X][X] MongoConfig Initialized!")


    if (!DB.jndiJdbcConnAvailable_?) {
      DB.defineConnectionManager(DefaultConnectionIdentifier, Database)
      //LiftRules.unloadHooks.append(Database.closeAllConnections_! _)
    }

    // Build SiteMap
    LiftRules.setSiteMap(
      SiteMap(
        Menu.i("Home") / "index",
        Menu.i("Player") / "player" / "player" submenus(
          Menu.i("Create New") / "player" / "createplayer"
        ),
        Menu.i("Console") / "console",
        Menu.i("State") / "state" >> User.AddUserMenusAfter
     )
    )


    // Use jQuery 1.4
    LiftRules.jsArtifacts = net.liftweb.http.js.jquery.JQuery14Artifacts

    //Show the spinny image when an Ajax call starts
    LiftRules.ajaxStart =
      Full(() => LiftRules.jsArtifacts.show("ajax-loader").cmd)
    
    // Make the spinny image go away when it ends
    LiftRules.ajaxEnd =
      Full(() => LiftRules.jsArtifacts.hide("ajax-loader").cmd)

    // Force the request to be UTF-8
    LiftRules.early.append(_.setCharacterEncoding("UTF-8"))

    // What is the function to test if a user is logged in?
    LiftRules.loggedInTest = Full(() => User.loggedIn_?)

    // Use HTML5 for rendering
    LiftRules.htmlProperties.default.set((r: Req) =>
      new Html5Properties(r.userAgent))    

    // Make a transaction span the whole HTTP request
    S.addAround(DB.buildLoanWrapper)
  }

  object Database extends StandardDBVendor(
    Props.get("db.class").openOr("org.h2.Driver"),
    Props.get("db.url").openOr("jdbc:h2:database/chapter_12;FILE_LOCK=NO"),
    Props.get("db.user"),
    Props.get("db.pass")
  )
}
