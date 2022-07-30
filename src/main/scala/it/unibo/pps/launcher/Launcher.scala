package it.unibo.pps.launcher

import it.unibo.pps.view.ViewModule
import it.unibo.pps.model.ModelModule
import it.unibo.pps.controller.ControllerModule
import it.unibo.pps.engine.SimulationEngineModule
import monix.execution.Scheduler.Implicits.global
import monix.reactive.Consumer
object Launcher
    extends ModelModule.Interface
    with ViewModule.Interface
    with ControllerModule.Interface
    with SimulationEngineModule.Interface:

  override val model = new ModelImpl()
  override val controller = new ControllerImpl()
  override val view = new ViewImpl()
  override val simulationEngine = new SimulationEngineImpl()

  @main def Main(): Unit =
    println("starting simulation.....")
