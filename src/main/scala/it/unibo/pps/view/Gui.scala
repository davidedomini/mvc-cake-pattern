package it.unibo.pps.view

import javax.swing.{JFrame, JPanel, JButton, SwingUtilities}
import java.awt.{Canvas, Color, Dimension, Graphics, BorderLayout}
import java.awt.event.{ActionEvent, ActionListener}

class Gui(val width: Int, val height: Int):
  self =>
  private val frame = JFrame()
  private val canvas = Environment()
  val btnPanel = JPanel()
  val layout = BorderLayout()
  val startButton = new JButton("Start")
  frame.setSize(width, height)
  frame.setLocationRelativeTo(null)
  canvas.setSize(width, height)
  btnPanel.add(startButton)
  startButton.addActionListener(new ActionListener {
    override def actionPerformed(e: ActionEvent): Unit =
      println("start pressed")
  })
  frame.add(canvas, BorderLayout.NORTH)
  frame.add(btnPanel, BorderLayout.SOUTH)
  frame.setLayout(layout)
  frame.setVisible(true)
  canvas.setVisible(true)

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