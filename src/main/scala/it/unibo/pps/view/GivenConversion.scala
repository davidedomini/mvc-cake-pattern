package it.unibo.pps.view

import it.unibo.pps.view.GivenConversion.CommonConversion
import monix.eval.Task
import it.unibo.pps.view.Environment

import java.awt.Component
import javax.swing.{JButton, JFrame, JPanel}

object GivenConversion:

  sealed trait CommonConversion:
    given Conversion[Unit, Task[Unit]] = Task(_)

  object GuiConversion extends CommonConversion:
    given Conversion[JFrame, Task[JFrame]] = Task(_)
    given Conversion[JButton, Task[JButton]] = Task(_)
    given Conversion[Environment, Task[Environment]] = Task(_)
    given Conversion[Component, Task[Component]] = Task(_)

