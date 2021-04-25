package org.coronabounce.vues;

import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.GridPane;

import org.coronabounce.data.Data;
import org.coronabounce.mvcconnectors.Displayable;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

/**
 * Part of View - class to draw the graphs.
 */
public class Graph
{
    /** Population - model to draw graph. **/
    private Displayable model;
    /** Chart of healthy. **/
    private XYChart.Series healthy;
    /** Chart of sick. **/
    private XYChart.Series sick;
    /** Chart of recovered. **/
    private XYChart.Series recovered;
    /** AreaChart for population's graph **/
    private AreaChart graphPanel = null;


    /**
     * Instantiates a new Graph.
     * @param model the population = model
     */
    public Graph (Displayable model)
    {
        this.model = model;

        this.healthy = new XYChart.Series();
        this.sick = new XYChart.Series();
        this.recovered = new XYChart.Series();
    }

    /**
     * {@summary Return AreaChart. }
     * @return the graph panel
     */
    public AreaChart getGraphPanel() { return this.graphPanel; }

    /**
     * {@summary Initialisation of graph and filing mainGrid by graphStatGrid}
     * @param gridGraphStat the grid of graph ans statistic
     */
    public void initGraphs(GridPane gridGraphStat)
    {
        // init graphPanel
        NumberAxis xAxis1 = new NumberAxis(0, this.model.getData().getNmbr(), 1);
        xAxis1.setTickLabelsVisible(false);
        xAxis1.setTickMarkVisible(false);
        NumberAxis yAxis1 = new NumberAxis(0, 100, 1);
        yAxis1.setTickLabelsVisible(false);
        yAxis1.setTickMarkVisible(false);
        this.graphPanel = new AreaChart(xAxis1, yAxis1);
        graphPanel.getData().addAll(healthy, sick, recovered);
        graphPanel.setCreateSymbols(false);

        // fil gridGraphStat by graphPanel
        graphPanel.setHorizontalGridLinesVisible(false);
        graphPanel.setVerticalGridLinesVisible(false);
        gridGraphStat.add(graphPanel, 1, 0);

        graphPanel.setMinWidth(150);
        graphPanel.setMinHeight(100);
        graphPanel.setPrefWidth(USE_COMPUTED_SIZE);
        graphPanel.setPrefHeight(100);
        graphPanel.setMaxWidth(USE_COMPUTED_SIZE);
        graphPanel.setMaxHeight(100);

    }

    /**
     * {@summary Delete all elements of charts healthy, sick and recovered. }
     */
    public void retainCharts()
    {
        healthy.getData().retainAll();
        sick.getData().retainAll();
        recovered.getData().retainAll();
    }

    /**
     * {@summary Clear data of charts healthy, sick and recovered. }
     */
    public void clearData()
    {
        healthy.getData().clear();
        sick.getData().clear();
        recovered.getData().clear();
    }

    /**
     * {@summary Create new charts healthy, sick and recovered on the basis of data from Slice. }
     * @param slice the slice
     * @param x     iterator
     */
    public void drawGraph(Data.Slice slice, int x)
    {
        healthy.getData().add(new XYChart.Data(x, 100));
        sick.getData().add(new XYChart.Data(x, slice.getPrcSick()));
        recovered.getData().add(new XYChart.Data(x, slice.getPrcRecovered()));
    }
}
