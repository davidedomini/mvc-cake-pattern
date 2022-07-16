package it.unibo.pps.engine

import it.unibo.pps.view.ViewModule
import it.unibo.pps.model.ModelModule
import cats.effect.IO

object SimulationEngineModule:
  trait SimulationEngine:
    def simulationLoop(): IO[Unit]

  trait Provider:
    val simulationEngine: SimulationEngine

  type Requirements = ViewModule.Provider with ModelModule.Provider

  trait Component:
    context: Requirements =>
    class SimulationEngineImpl extends SimulationEngine:

      def simulationLoop(): IO[Unit] = ???

  trait Interface extends Provider with Component:
    self: Requirements =>