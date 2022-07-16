package it.unibo.pps.controller

import it.unibo.pps.view.ViewModule
import it.unibo.pps.model.ModelModule

object ControllerModule :

  trait Controller:
    def notifyStart(): Unit

  trait Provider:
    val controller: Controller

  type Requirements = ViewModule.Provider with ModelModule.Provider