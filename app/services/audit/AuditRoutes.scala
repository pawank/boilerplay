package services.audit

import play.api.mvc.Call
import services.audit.AuditArgs._
import util.Logging

object AuditRoutes extends Logging {
  def getViewRoute(key: String, id: Seq[String]) = routeFor(key, getArg(id, _))

  private[this] def routeFor(key: String, arg: (Int) => String): Call = key.toLowerCase match {
    /* Start audit calls */

    case "audit" => controllers.admin.audit.routes.AuditController.view(uuidArg(arg(0)))
    case "auditrecord" => controllers.admin.audit.routes.AuditRecordController.view(uuidArg(arg(0)))
    case "note" => controllers.admin.note.routes.NoteController.view(uuidArg(arg(0)))
    case "user" => controllers.admin.user.routes.SystemUserController.view(uuidArg(arg(0)))

    /* End audit calls */

    case _ =>
      log.warn(s"Invalid model key [$key].")
      controllers.admin.system.routes.AdminController.explore()
  }
}
