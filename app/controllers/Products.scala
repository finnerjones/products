package controllers

import models.Product
import play.api.mvc.{Action, Controller, Flash}
import play.api.data.Form
import play.api.data.Forms.{mapping,longNumber,nonEmptyText}
import play.api.i18n.Messages


/**
 * Created by finner on 18/01/2015.
 */
object Products extends Controller {

  private val productForm : Form[Product] = Form(
    mapping(
      "ean" -> longNumber.verifying("validation.ean.duplicate", Product.findByEan(_).isEmpty),
      //"ean" -> longNumber,
      "name" -> nonEmptyText,
      "description" -> nonEmptyText
    )(Product.apply)(Product.unapply)
  )


  def list = Action { implicit request =>
    val products = Product.getAll

    Ok(views.html.products.list(products))

  }

  def show(ean: Long) = Action { implicit request =>

    Product.findByEan(ean).map {
      product =>
        Ok(views.html.products.details(product))
    }.head

  }

  def newProduct = Action { implicit request =>
    println("newProduct **************************************+")
    val form = if (flash.get("error").isDefined)
      productForm.bind(flash.data)
    else
      productForm

    Ok(views.html.products.editProduct(form))
  }


  def save = Action { implicit request =>
    val newProductForm = productForm.bindFromRequest()
    println("Saving a prodcut *********************")
    newProductForm.fold(
      hasErrors = { form => println("An error occurredd ******************************");
        Redirect(routes.Products.newProduct()).
          flashing(Flash(form.data) + ("error" -> Messages("validation.errors")))
      },
      success = { newProduct =>
          println("Inserting product *************************");
          Product.insert(newProduct)
          val message = Messages("products.new.success", newProduct.name)
          //Redirect(routes.Products.show(newProduct.ean)).flashing("success" -> message)
          Redirect(routes.Products.list()).flashing("success" -> message)
        }
    )
  }


}
