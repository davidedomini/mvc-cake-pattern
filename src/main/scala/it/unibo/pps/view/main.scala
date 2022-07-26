package it.unibo.pps.view
import monix.execution.Scheduler.Implicits.global
object main extends App:
  new MonadicGui()
    .initGui()
    .runAsyncAndForget