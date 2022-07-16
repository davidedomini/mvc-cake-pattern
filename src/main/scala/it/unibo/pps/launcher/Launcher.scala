package it.unibo.pps.launcher

import it.unibo.pps.view.ViewModule
import it.unibo.pps.model.ModelModule
import it.unibo.pps.controller.ControllerModule

object Launcher
  extends ModelModule.Interface
  with ViewModule.Interface
  with ControllerModule.Interface:

  override val model = new ModelImpl()
  override val controller = new ControllerImpl()
  override val view = new ViewImpl()

  @main def Main(): Unit =
    println("starting simulation.....")
