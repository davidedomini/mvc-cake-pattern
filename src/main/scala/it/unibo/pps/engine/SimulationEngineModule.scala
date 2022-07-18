package it.unibo.pps.engine

import it.unibo.pps.view.ViewModule
import it.unibo.pps.model.ModelModule
import monix.eval.{Task, TaskApp}
import monix.execution.Scheduler
import concurrent.duration.{Duration, DurationInt, FiniteDuration}
import scala.language.postfixOps
import scala.language.implicitConversions

object SimulationEngineModule:
  trait SimulationEngine:
    def simulationLoop(): Task[Unit]

  trait Provider:
    val simulationEngine: SimulationEngine

  type Requirements = ViewModule.Provider with ModelModule.Provider

  trait Component:
    context: Requirements =>
    class SimulationEngineImpl extends SimulationEngine:

      given unitToTask: Conversion[Unit, Task[Unit]] = Task(_)
      given intToTask: Conversion[Int, Task[Int]] = Task(_)

      def simulationLoop(): Task[Unit] =
        for
          _ <- waitFor(1 seconds)
          vt <- computeNewVt()
          _ <- updateModel(vt)
          _ <- updateView()
        yield()

      private def computeNewVt(): Task[Int] = context.model.getVirtualTime() + 1

      private def updateModel(t: Int): Task[Unit] =
        context.model.updateVirtualTime(t)

      private def updateView(): Task[Unit] =
        val vt = context.model.getVirtualTime()
        context.view.show(vt)

      private def waitFor(d: FiniteDuration): Task[Unit] =
        Task.sleep(d)

  trait Interface extends Provider with Component:
    self: Requirements =>