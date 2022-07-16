package it.unibo.pps.view

import it.unibo.pps.controller.ControllerModule

object ViewModule:

  trait View:
    def show(i: Int): Unit

  trait Provider:
    val view: View

  type Requirements = ControllerModule.Provider

  trait Component:
    context: Requirements =>
    class ViewImpl extends View:
      val gui = Gui(400, 200, context.controller)
      def show(i: Int): Unit = gui render i

  trait Interface extends Provider with Component:
    self: Requirements =>
