package it.unibo.pps.view

object ViewModule:

  trait View:
    def show(i: Int): Unit

  trait Provider:
    val view: View

  type Requirements = ???

  trait Component:
    context: Requirements =>
    class ViewImpl extends View:

      def show(i: Int): Unit = ???

  trait Interface extends Provider with Component:
    self: Requirements =>
