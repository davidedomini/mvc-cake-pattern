package it.unibo.pps.view.charts

import org.jfree.chart.{ChartFactory, ChartPanel, JFreeChart}
import org.jfree.chart.plot.PlotOrientation
import org.jfree.data.xy.{XYDataset, XYSeries, XYSeriesCollection}

class ChartManager:
  private var series: Option[XYSeries] = Option.empty
  private var chart: Option[JFreeChart] = Option.empty

  def emptyLineChart(title: String, xLabel: String, yLabel: String, serieName: String): ChartPanel =
    series = Option(XYSeries(serieName))
    chart = Option(createChart(title, xLabel, yLabel, XYSeriesCollection(series.get)))
    ChartPanel(chart.get)

  def addValue(x: Double, y: Double): Unit =
    series.foreach(s => s.add(x, y))
    chart.foreach(c => c.getXYPlot.setDataset(XYSeriesCollection(series.get)))

  private def createChart(title: String, xLabel: String, yLabel: String, dataset: XYDataset): JFreeChart =
    val chart = ChartFactory.createXYLineChart(
      title,
      xLabel,
      yLabel,
      dataset,
      PlotOrientation.VERTICAL,
      true,
      true,
      false
    )
    chart
