package it.unibo.pps.controller

import it.unibo.pps.view.ViewModule
import it.unibo.pps.model.ModelModule
import it.unibo.pps.engine.SimulationEngineModule
import monix.execution.Scheduler.Implicits.global

object ControllerModule:

  trait Controller:
    def notifyStart(): Unit

  trait Provider:
    val controller: Controller

  type Requirements = ModelModule.Provider with SimulationEngineModule.Provider

  trait Component:
    context: Requirements =>
    class ControllerImpl extends Controller:
      def notifyStart(): Unit =
        context.model.init()
        context.simulationEngine
          .simulationLoop()
          .loopForever
          .runAsyncAndForget

  trait Interface extends Provider with Component:
    self: Requirements =>