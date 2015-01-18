package controllers

import models.Product
import play.api.mvc.{Action, Controller}

/**
 * Created by finner on 18/01/2015.
 */
object Products extends Controller {

  def list = Action { implicit request =>
    val products = Product.findAll

    Ok(views.html.products.list(products))

  }

}
