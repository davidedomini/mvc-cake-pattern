package it.unibo.pps.launcher

import it.unibo.pps.view.ViewModule
import it.unibo.pps.model.ModelModule
import it.unibo.pps.controller.ControllerModule

object Launcher
  extends  ModelModule.Interface
  with ViewModule.Interface
  with ControllerModule.Interface:

  override val model = ModelImpl()
  override val view = ViewImpl()
  override val controller = ControllerImpl()

  @main def Main(): Unit =
    println("starting simulation.....")
