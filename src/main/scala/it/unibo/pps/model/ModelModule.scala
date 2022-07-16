package it.unibo.pps.model

object ModelModule:

  trait Model:
    def init(): Unit
    def updateVirtualTime(): Unit
    def getVirtualTime(): Int

  trait Provider:
    val model: Model

  trait Component:
    class ModelImpl extends Model:

      var virtualTime: Int = 0

      def init(): Unit = virtualTime = 0
      def updateVirtualTime(): Unit = virtualTime = virtualTime + 1
      def getVirtualTime(): Int = virtualTime

  trait Interface extends Provider with Component