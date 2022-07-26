package it.unibo.pps.view
import javax.swing.{JButton, JFrame, JLabel, JPanel, SwingUtilities}
import java.awt.{Component, BorderLayout, Canvas, Color, Dimension, Graphics}
import java.awt.event.{ActionEvent, ActionListener}
import it.unibo.pps.controller.ControllerModule
import monix.eval.{Task, TaskApp}
import javax.swing.WindowConstants
import monix.execution.Scheduler.Implicits.global

class MonadicGui(val width: Int, val height: Int, controller: ControllerModule.Controller):

  import GivenConversion.GuiConversion.given

  val frame: Task[JFrame] =
    for
      fr <- new JFrame("Chrono")
      _ <- fr.setSize(width, height)
      _ <- fr.setLocationRelativeTo(null)
      _ <- fr.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
    yield fr
  val btn =
    for
      jb <- new JButton()
      _ <- jb.setText("Start")
      _ <- jb.addActionListener(e => controller.notifyStart())
    yield jb
  val canvas: Task[Environment] =
    for
      cnv <- new Environment(width - 200, height - 100)
      _ <- cnv.setSize(width - 200, height - 100)
      _ <- cnv.setVisible(true)
    yield cnv
  val p = for
    fr <- frame
    jb <- btn
    cnv <- canvas
    _ <- fr.setLayout(new BorderLayout())
    _ <- fr.add(cnv, BorderLayout.NORTH)
    _ <- fr.add(jb, BorderLayout.SOUTH)
    _ <- fr.setVisible(true)
  yield ()
  println(canvas)
  p.runAsyncAndForget

  def render(i: Int): Unit =
    canvas.foreach(c => {
      c.element = i
      c.invalidate()
      c.repaint()
    })

class Environment(val w: Int, val h: Int) extends JPanel:
  var element = 0
  override def getPreferredSize = new Dimension(w, h)
  override def paintComponent(graphics: Graphics): Unit =
    graphics.setColor(Color.BLACK)
    graphics.drawString(element.toString, 50, 50)