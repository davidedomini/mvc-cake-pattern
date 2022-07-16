package it.unibo.pps.controller

import it.unibo.pps.view.ViewModule
import it.unibo.pps.model.ModelModule

object ControllerModule:

  trait Controller:
    def notifyStart(): Unit

  trait Provider:
    val controller: Controller

  type Requirements = ViewModule.Provider with ModelModule.Provider

  trait Component:
    context: Requirements =>
    class ControllerImpl extends Controller:
      def notifyStart(): Unit =
        context.model.init()
        new Thread(() => {
          while true do
            context.model.updateVirtualTime()
            context.view.show(context.model.getVirtualTime())
            Thread.sleep(1000)
        }).start()

  trait Interface extends Provider with Component:
    self: Requirements =>