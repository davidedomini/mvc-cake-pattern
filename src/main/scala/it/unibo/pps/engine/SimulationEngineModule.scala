package it.unibo.pps.engine

import it.unibo.pps.view.ViewModule
import it.unibo.pps.model.ModelModule
import monix.eval.{Task, TaskApp}
import monix.execution.Scheduler
import concurrent.duration.{Duration, DurationInt, FiniteDuration}
import scala.language.postfixOps

object SimulationEngineModule:
  trait SimulationEngine:
    def simulationLoop(): Task[Unit]

  trait Provider:
    val simulationEngine: SimulationEngine

  type Requirements = ViewModule.Provider with ModelModule.Provider

  trait Component:
    context: Requirements =>
    class SimulationEngineImpl extends SimulationEngine:

      def simulationLoop(): Task[Unit] =
        for
          _ <- waitFor(1 seconds)
          _ <- updateModel()
          _ <- updateView()
        yield()

      private def updateModel(): Task[Unit] =
        context.model.updateVirtualTime()

      private def updateView(): Task[Unit] =
        val vt = context.model.getVirtualTime()
        context.view.show(vt)

      private def waitFor(d: FiniteDuration): Task[Unit] =
        Task.sleep(d)

      implicit def unitToTask(exp: => Unit): Task[Unit] = Task { exp }

  trait Interface extends Provider with Component:
    self: Requirements =>