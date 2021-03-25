package org.coronabounce;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import org.coronabounce.controllers.Controller;
import org.coronabounce.data.Data;
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
    private List<CoquilleBille> points1;
    private List<CoquilleBille> points2;
    private Timeline tlPoints;
    private Timeline tlGraph;
    private Button btnLegend;

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
    @FXML MenuItem scenario_1_1;
    @FXML MenuItem scenario_1_2;
    @FXML MenuItem scenario_2_1;
    @FXML MenuItem scenario_2_2;


    //========================= Constructors ==========================================================================/

    public MainController()
    {
        this.tlPoints = null;
        System.out.println("New controller\n");
        this.controller = new Controller();
        changeController(this.controller);
    }

    public void changeController(Controllable c)
    {
        stopTimer();
        System.out.println("Change controller\n");
        this.zone1 = new Zone(c);
        this.model1 = zone1.getPopulation();
        this.points1 = model1.getAllPoints();

        this.healthy = new XYChart.Series();
        this.sick = new XYChart.Series();
        this.recovered = new XYChart.Series();

        this.zone2 = new Zone(c);
        this.model2 = zone2.getPopulation();
        this.points2 = model2.getAllPoints();
    }

    //========================= Getters ===============================================================================/

    public Controllable getController()
    {
        return controller;
    }

    //========================= Initialisation ========================================================================/

    @FXML
    private void initialize()
    {
        Image image = new Image(getClass().getResourceAsStream("images_1.png"));
        ImageView view = new ImageView(image);
        this.btnLegend = new Button();
        btnLegend.setMaxSize(42, 42);
        btnLegend.setGraphic(view);
        btnLegend.setLayoutX(940);                      //mainPane.getWidth()
        mainPane.getChildren().add(btnLegend);


        // init graphPanel
        NumberAxis xAxis = new NumberAxis(0, model1.getData().getNmbr(), 1);
        xAxis.setTickLabelsVisible(false);
        xAxis.setTickMarkVisible(false);
        NumberAxis yAxis = new NumberAxis(0, 100, 1);
        yAxis.setTickLabelsVisible(false);
        yAxis.setTickMarkVisible(false);
        AreaChart graphPanel = new AreaChart(xAxis, yAxis);
        graphPanel.getData().addAll(healthy, sick, recovered);

        // fil mainGrid by graphPanel
        mainGrid.add(graphPanel, 1, 0);
        graphPanel.setPrefSize(371, 160);
        graphPanel.setLayoutX(138);
        graphPanel.setLayoutY(59);
        graphPanel.setHorizontalGridLinesVisible(false);
        graphPanel.setVerticalGridLinesVisible(false);

        // init points
        drawPopulation(points1, false);
        drawPopulation(points2, true);

        // init statistics
        labelHealthy.setText(String.valueOf(model1.getNbHealthy()));
        labelSick.setText(String.valueOf(model1.getNbSick()));
        labelRecovered.setText(String.valueOf(model1.getNbRecovered()));

    }

    private void drawPopulation(List<CoquilleBille> lcb, boolean is_panel2)
    {
        for (CoquilleBille cb : lcb)
        {
            drawPoint(cb, is_panel2);
        }
    }

    private void drawPoint(CoquilleBille cb, boolean is_panel2)
    {
        String state = cb.getIndividual().healthState();
        double coordX = cb.getPosition().getX();
        double coordY = cb.getPosition().getY();
        Circle point = new Circle(coordX, coordY, controller.getRadiusDot());
        if (state.equals("Healthy")) {point.setFill(valueOf("70e000"));}    //green
        if (state.equals("Incubating")) {point.setFill(valueOf("ff1830"));}  //red
        if (state.equals("Recovered")) {point.setFill(valueOf("ffd22f"));}  //yellow
        if (state.equals("Sick")){point.setFill(valueOf("a80011"));}     // dark red
        if (is_panel2)
        {
            panel2.getChildren().add(point);
        }
        else
        {
            panel1.getChildren().add(point);
        }
    }

    //=============================== Interrupters ====================================================================/

    /**
     * Timeline interrupter
     */
    public void stopTimer()
    {
        try
        {
            if (zone1 != null)
            {
                zone1.stopTimer(true);
                zone1.getPopulation().stopTimer();
            }
            if (zone2 != null)
            {
                zone2.stopTimer(true);
                zone2.getPopulation().stopTimer();
            }
        } catch (Exception e)
        {
            System.out.println("An error occurred when trying to stop old Populations Timers");
        }
    }

    /**
     * Timeline interrupter
     */
    private void stopTimeLine(Timeline t)
    {
        if (null != t)
        {
            t.stop();
            t = null;
        }
    }


    //========================= Button's functions ====================================================================/

    /**
     * Function for button "Start" - create Timeline and launch function moving() on zone
     */
    @FXML
    private void launchMoving() throws IOException
    {
        launchPointsAndStat();
        launchDrawGraph();
        zone1.moving();
        zone2.moving();
    }

    /**
     * Functions for scenarios
     */

    public void performAction(ActionEvent actionEvent) throws IOException {

        MenuItem target  = (MenuItem) actionEvent.getSource();
        System.out.println("Clicked On Item:"+target.getId());

        Controllable c = new Controller();
        c.setPersonsCount(99);

        changeController(c);
        App.setRoot("corona bounce");
    }

    public void performAction2(ActionEvent actionEvent) throws IOException {

        MenuItem target  = (MenuItem) actionEvent.getSource();
        System.out.println("Clicked On Item:"+target.getId());

        Controllable c = new Controller();
        c.setPersonsCount(150);

        changeController(c);
        App.setRoot("corona bounce");
    }

    /**
     * Function for button "Settings" - redirect to window settings
     */
    @FXML
    private void switchToSettings() throws IOException
    {
        if (null != tlPoints)
        {
            tlPoints.stop();
            tlPoints = null;
        }
        stopTimer();
        App.setRoot("settings");
    }

    /**
     * Function for button "Pause"
     */
    @FXML
    private void makePause()
    {

    }

    /**
     * Function for button "Reset"
     */
    @FXML
    private void resetModel()
    {

    }

    /**
     * Function for button "?"
     */
    private void showLegend()
    {

    }

    //========================= Button's auxiliary functions ==========================================================/

    /**
     * Timeline launcher for drawing the graphs in AreaChart
     */
    private void launchDrawGraph()
    {
        stopTimeLine(tlGraph);
        healthy.getData().retainAll();
        sick.getData().retainAll();
        recovered.getData().retainAll();

        model1.saveStatToData();

        tlGraph = new Timeline(new KeyFrame(Duration.millis(100), ev ->
        {
           // long startTime = System.currentTimeMillis();

            healthy.getData().clear();
            sick.getData().clear();
            recovered.getData().clear();

            ArrayList<Data.Slice> History = model1.getData().getFifo();

            // draw graph
            int x = 0;
            for (Data.Slice Slice : History)
            {
                healthy.getData().add(new XYChart.Data(x, 100));
                sick.getData().add(new XYChart.Data(x, Slice.getPrcSick()));
                recovered.getData().add(new XYChart.Data(x, Slice.getPrcRecovered()));
                x++;
            }

           // long stopTime = System.currentTimeMillis();
           // long diff = stopTime - startTime;
           // System.out.println("Difference: " + diff);

            System.out.println("Graph Thread: " + Thread.currentThread().getId());

        }));
        tlGraph.setCycleCount(Animation.INDEFINITE);
        tlGraph.play();
    }

    /**
     * Timeline launcher to draw moving points and update statistics
     */
    private void launchPointsAndStat()
    {
        stopTimeLine(tlPoints);

        tlPoints = new Timeline(new KeyFrame(Duration.millis(33), ev ->
        {
            panel1.getChildren().retainAll();
            panel2.getChildren().retainAll();

            // update points
            drawPopulation(points1, false);
            drawPopulation(points2, true);

            // update statistics
            labelHealthy.setText(String.valueOf(model1.getNbHealthy()));
            labelSick.setText(String.valueOf(model1.getNbSick()));
            labelRecovered.setText(String.valueOf(model1.getNbRecovered()));
        }));
        tlPoints.setCycleCount(Animation.INDEFINITE);
        tlPoints.play();
    }

    private void makeLegend()
    {
        Pane legend = new Pane();
        legend.setStyle("-fx-background-color: white;");
        legend.setPrefSize(200, 100);
        TextArea desc = new TextArea();

    }
}