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
    private Zone zone;                              //TODO check it
    private Displayable model ;                     //TODO check it
    private List<CoquilleBille> allPoints;

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
        this.controller = new Controller();
        this.zone = new Zone(controller);
        this.model = zone.getPopulation();
        this.allPoints = model.getAllPoints();
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
        XYChart.Series healthy = new XYChart.Series();
        XYChart.Series sick = new XYChart.Series();
        XYChart.Series recovered = new XYChart.Series();
        graphPanel.getData().addAll(healthy, sick, recovered);

        // fil mainGrid by graphPanel
        mainGrid.add(graphPanel, 1,0 );
        graphPanel.setPrefSize(371, 160);
        graphPanel.setLayoutX(138);
        graphPanel.setLayoutY(59);
        graphPanel.setHorizontalGridLinesVisible(false);
        graphPanel.setVerticalGridLinesVisible(false);

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(33), ev -> {
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
            healthy.getData().add(new XYChart.Data("", model.getNbIndividus()));                    //TODO doesn't work
            sick.getData().add(new XYChart.Data("", model.getNbSick()));
            recovered.getData().add(new XYChart.Data("", model.getNbRecovered() + model.getNbSick()));
            
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        zone.moving();
    }

    public Controllable getController() {
        return controller;
    }

    @FXML
    private void switchToSettings() throws IOException {
        App.setRoot("settings");
    }

}
