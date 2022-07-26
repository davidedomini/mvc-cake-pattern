package it.unibo.pps.view
import javax.swing.{JButton, JFrame, JLabel, JPanel, SwingUtilities}
import java.awt.{Component, BorderLayout, Canvas, Color, Dimension, Graphics}
import java.awt.event.{ActionEvent, ActionListener}
import it.unibo.pps.controller.ControllerModule
import monix.eval.{Task, TaskApp}
import javax.swing.WindowConstants


given componentToTask: Conversion[Component, Task[Component]] = Task(_)
given unitToTask: Conversion[Unit, Task[Unit]] = Task(_)
given jfrToTask: Conversion[JFrame, Task[JFrame]] = Task(_)
given jbToTask: Conversion[JButton, Task[JButton]] = Task(_)

class MonadicGui:

  def initGui(): Task[Unit] =
    val frame: Task[JFrame] = for
      fr <- createJFr()
      _ <- fr.setSize(400, 200)
      _ <- fr.setLocationRelativeTo(null)
      _ <- fr.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
    yield fr
    val btn = for
      jb <- new JButton()
      _ <- jb.setSize(100, 50)
      _ <- jb.setText("ciao")
      _ <- jb.addActionListener(e => println("ciao"))
    yield jb
    for
      fr <- frame
      jb <- btn
      _ <- fr.add(jb)
      _ <- fr.setVisible(true)
    yield ()

  def createJFr(): Task[JFrame] = new JFrame("Chrono")
