package org.coronabounce;

import java.io.IOException;
import java.util.List;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
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
    private Zone zone1 = null;
    private Zone zone2 = null;
    private Displayable model1;
    private Displayable model2;
    private List<CoquilleBille> allPoints1;
    private List<CoquilleBille> allPoints2;
    private Timeline timeline;

    XYChart.Series healthy;
    XYChart.Series sick;
    XYChart.Series recovered;

    @FXML AnchorPane mainPane;
    @FXML Pane panel1;                            // field with moving points
    @FXML Pane panel2;                            // field with moving points
    @FXML GridPane mainGrid;                     // grid contains statistic's grid (gridStatistic) and graph (graphPanel)
    @FXML GridPane gridStatistic;
    @FXML ChoiceBox btnScenario;
    @FXML Label labelHealthy;
    @FXML Label labelSick;
    @FXML Label labelRecovered;

    //========================= Constructors ==========================================================================/

    public MainController()
    {
        this.timeline = null;
        System.out.println("New controller\n");
        this.controller = new Controller();
        changeController(this.controller);
    }

    public void changeController(Controllable c) {
        System.out.println("Change controller\n");
        if (null != this.zone1)
        {
            this.zone1.stop(true);
        }
        this.zone1 = new Zone(c);
        this.model1 = zone1.getPopulation();
        this.allPoints1 = model1.getAllPoints();
        this.healthy = new XYChart.Series();
        this.sick = new XYChart.Series();
        this.recovered = new XYChart.Series();

        if (null != this.zone2)
        {
            this.zone2.stop(true);
        }
        this.zone2 = new Zone(c);
        this.model2 = zone2.getPopulation();
        this.allPoints2 = model2.getAllPoints();
    }

    //========================= Getters ===============================================================================/

    public Controllable getController() {
        return controller;
    }

    //========================= Own functions =========================================================================/

    @FXML
    private void initialize()
    {
        // init graphPanel
        NumberAxis xAxis = new NumberAxis();
        xAxis.setTickLabelsVisible(false);
        xAxis.setTickMarkVisible(false);
        NumberAxis yAxis = new NumberAxis(0, model1.getNbIndividus(), 1);
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
        for (CoquilleBille cb : allPoints1)
        {
            String state1 = cb.getIndividual().healthState();
            double coordX1 = cb.getPosition().getX();
            double coordY1 = cb.getPosition().getY();
            Circle point = new Circle(coordX1, coordY1, controller.getRadiusDot());
            if (state1.equals("Healthy")) { point.setFill(valueOf("#A9E0F4")); }    //light blue
            if (state1.equals("Recovered")) { point.setFill(valueOf("#CF7EEE")); }  //lilas
            if (state1.equals("Sick")) { point.setFill(valueOf("#830B0B")); }      // red-brown
            panel1.getChildren().add(point);
        }

        for (CoquilleBille cb : allPoints2)
        {
            String state2 = cb.getIndividual().healthState();
            double coordX2 = cb.getPosition().getX();
            double coordY2 = cb.getPosition().getY();
            Circle point = new Circle(coordX2, coordY2, controller.getRadiusDot());
            if (state2.equals("Healthy")) { point.setFill(valueOf("#A9E0F4")); }    //light blue
            if (state2.equals("Recovered")) { point.setFill(valueOf("#CF7EEE")); }  //lilas
            if (state2.equals("Sick")) { point.setFill(valueOf("#830B0B")); }      // red-brown
            panel2.getChildren().add(point);
        }

        // init statistics
        labelHealthy.setText(String.valueOf(model1.getNbHealthy()));
        labelSick.setText(String.valueOf(model1.getNbSick()));
        labelRecovered.setText(String.valueOf(model1.getNbRecovered()));

    }

    @FXML
    private void closeAction(ActionEvent evt)
    {
        System.exit(0);
    }

    //========================= Button's functions ====================================================================/

    /**
     * Function for button "Settings" - redirect to window settings
     */
    @FXML
    private void switchToSettings() throws IOException
    {
        App.setRoot("settings");
    }

    /**
     * Function for button "Start" - create Timeline and launch function moving() on zone
     */
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


            panel1.getChildren().retainAll();
            panel2.getChildren().retainAll();
            
            // update points
            for (CoquilleBille cb : allPoints1)
            {
                String state = cb.getIndividual().healthState();
                double coordX = cb.getPosition().getX();
                double coordY = cb.getPosition().getY();
                Circle point = new Circle(coordX, coordY, controller.getRadiusDot());
                if (state.equals("Healthy")) { point.setFill(valueOf("#A9E0F4")); }    //light blue
                if (state.equals("Recovered")) { point.setFill(valueOf("#CF7EEE")); }  //lilas
                if (state.equals("Sick")) { point.setFill(valueOf("#830B0B")); }      // red-brown
                panel1.getChildren().add(point);
            }

            for (CoquilleBille cb : allPoints2)
            {
                String state2 = cb.getIndividual().healthState();
                double coordX2 = cb.getPosition().getX();
                double coordY2 = cb.getPosition().getY();
                Circle point = new Circle(coordX2, coordY2, controller.getRadiusDot());
                if (state2.equals("Healthy")) { point.setFill(valueOf("#A9E0F4")); }    //light blue
                if (state2.equals("Recovered")) { point.setFill(valueOf("#CF7EEE")); }  //lilas
                if (state2.equals("Sick")) { point.setFill(valueOf("#830B0B")); }      // red-brown
                panel2.getChildren().add(point);
            }

            // update statistics
            labelHealthy.setText(String.valueOf(model1.getNbHealthy()));
            labelSick.setText(String.valueOf(model1.getNbSick()));
            labelRecovered.setText(String.valueOf(model1.getNbRecovered()));

            // draw graph
            /*healthy.getData().add(new XYChart.Data("", model.getNbIndividus()));                    //TODO doesn't work
            sick.getData().add(new XYChart.Data("", model.getNbSick()));
            recovered.getData().add(new XYChart.Data("", model.getNbRecovered() + model.getNbSick()));
            */
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        zone1.moving();
        zone2.moving();
    }

    @FXML
    private void makePause()
    {

    }

    @FXML
    private void resetModel()
    {
        
    }
}
