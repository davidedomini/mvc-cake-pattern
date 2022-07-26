package it.unibo.pps.view

import it.unibo.pps.controller.ControllerModule

object ViewModule:

  trait View:
    def show(vitualTime: Int): Unit

  trait Provider:
    val view: View

  type Requirements = ControllerModule.Provider

  trait Component:
    context: Requirements =>
    class ViewImpl extends View:
      //private val gui = Gui(400, 200, context.controller)
      private val gui = MonadicGui(400, 200, context.controller)
      def show(virtualTime: Int): Unit = gui render virtualTime

  trait Interface extends Provider with Component:
    self: Requirements =>
