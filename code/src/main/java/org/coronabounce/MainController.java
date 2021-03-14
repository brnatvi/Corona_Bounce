package org.coronabounce;

import java.io.IOException;
import java.util.List;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import org.coronabounce.controllers.Controller;
import org.coronabounce.models.CoquilleBille;
import org.coronabounce.models.Zone;
import org.coronabounce.mvcconnectors.Controllable;
import org.coronabounce.mvcconnectors.Displayable;

import static javafx.scene.paint.Paint.valueOf;

public class MainController
{
    private Controllable controller;
    private Zone zone = null;                              //TODO check it
    private Displayable model ;                     //TODO check it
    private List<CoquilleBille> allPoints;
    private Timeline timeline;

    XYChart.Series healthy;
    XYChart.Series sick;
    XYChart.Series recovered;

    @FXML AnchorPane mainPane;
    @FXML Pane panel;                            // field with moving points
    @FXML GridPane mainGrid;                     // grid contains statistic's grid (gridStatistic) and graph (graphPanel)
    @FXML GridPane gridStatistic;
    @FXML ChoiceBox btnScenario;
    @FXML Label labelHealthy;
    @FXML Label labelSick;
    @FXML Label labelRecovered;

    public MainController()
    {
        timeline = null;
        System.out.println("New controller\n");
        this.controller = new Controller();
        changeController(controller);
    }

    public void changeController(Controllable c) {
        System.out.println("Change controller\n");
        if (null != this.zone)
        {
            this.zone.stop(true);
        }
        this.zone = new Zone(c);
        this.model = zone.getPopulation();
        this.allPoints = model.getAllPoints();
        this.healthy = new XYChart.Series();
        this.sick = new XYChart.Series();
        this.recovered = new XYChart.Series();
    }

    @FXML
    private void initialize()
    {
        // init graphPanel
        NumberAxis xAxis = new NumberAxis();
        xAxis.setTickLabelsVisible(false);
        xAxis.setTickMarkVisible(false);
        NumberAxis yAxis = new NumberAxis(0, model.getNbIndividus(), 1);
        yAxis.setTickLabelsVisible(false);
        yAxis.setTickMarkVisible(false);
        AreaChart graphPanel = new AreaChart(xAxis, yAxis);
        graphPanel.getData().addAll(healthy, sick, recovered);

        // fil mainGrid by graphPanel
        mainGrid.add(graphPanel, 1,0 );
        graphPanel.setPrefSize(371, 160);
        graphPanel.setLayoutX(138);
        graphPanel.setLayoutY(59);
        graphPanel.setHorizontalGridLinesVisible(false);
        graphPanel.setVerticalGridLinesVisible(false);

        // init points
        for (CoquilleBille cb : allPoints)
        {
            String state = cb.getIndividual().healthState();
            double coordX = cb.getPosition().getX();
            double coordY = cb.getPosition().getY();
            Circle point = new Circle(coordX, coordY, controller.getRadiusDot());
            if (state.equals("Healthy")) { point.setFill(valueOf("#A9E0F4")); }    //light blue
            if (state.equals("Recovered")) { point.setFill(valueOf("#CF7EEE")); }  //lilas
            if (state.equals("Sick")) { point.setFill(valueOf("#830B0B")); }      // red-brown
            panel.getChildren().add(point);
        }

        // init statistics
        labelHealthy.setText(String.valueOf(model.getNbHealthy()));
        labelSick.setText(String.valueOf(model.getNbSick()));
        labelRecovered.setText(String.valueOf(model.getNbRecovered()));

    }

    public Controllable getController() {
        return controller;
    }

    @FXML
    private void switchToSettings() throws IOException{
        App.setRoot("settings");
    }

    @FXML
    private void launchMoving() throws IOException
    {
        //System.out.println("Create time line\n");
        //for (StackTraceElement ste : Thread.currentThread().getStackTrace())
        //{
        //    System.out.println(ste);
        //}
        //System.out.println("***********************************");

        if (null != timeline)
        {
            timeline.stop();
            timeline = null;
        }

        timeline = new Timeline(new KeyFrame(Duration.millis(33), ev -> {
            panel.getChildren().retainAll();

            // update points
            for (CoquilleBille cb : allPoints)
            {
                String state = cb.getIndividual().healthState();
                double coordX = cb.getPosition().getX();
                double coordY = cb.getPosition().getY();
                Circle point = new Circle(coordX, coordY, controller.getRadiusDot());
                if (state.equals("Healthy")) { point.setFill(valueOf("#A9E0F4")); }    //light blue
                if (state.equals("Recovered")) { point.setFill(valueOf("#CF7EEE")); }  //lilas
                if (state.equals("Sick")) { point.setFill(valueOf("#830B0B")); }      // red-brown
                panel.getChildren().add(point);
            }

            // update statistics
            labelHealthy.setText(String.valueOf(model.getNbHealthy()));
            labelSick.setText(String.valueOf(model.getNbSick()));
            labelRecovered.setText(String.valueOf(model.getNbRecovered()));
              
            // draw graph
            /*healthy.getData().add(new XYChart.Data("", model.getNbIndividus()));                    //TODO doesn't work
            sick.getData().add(new XYChart.Data("", model.getNbSick()));
            recovered.getData().add(new XYChart.Data("", model.getNbRecovered() + model.getNbSick()));
             */
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        zone.moving();
    }

}
