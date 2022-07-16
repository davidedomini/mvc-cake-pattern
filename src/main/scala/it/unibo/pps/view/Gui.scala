package it.unibo.pps.view

import javax.swing.{JFrame, JPanel, SwingUtilities}
import java.awt.{Canvas, Color, Dimension, Graphics}

class Gui(val width: Int, val height: Int):
  self =>
  private val frame = JFrame()
  private val canvas = Environment()
  frame.setSize(width, height)
  frame.setVisible(true)
  canvas.setVisible(true)
  frame.setLocationRelativeTo(null)
  canvas.setSize(width, height)
  frame.add(canvas)

  def render(i: Int): Unit = SwingUtilities.invokeLater{ () =>
    canvas.element = i
    canvas.invalidate()
    canvas.repaint()
  }

  private class Environment() extends JPanel:

    var element = 0

    override def getPreferredSize = new Dimension(self.width, self.height)
    override def paintComponent(graphics: Graphics): Unit =
      graphics.setColor(Color.BLACK)
      graphics.drawString(element.toString, 50, 50)