package org.coronabounce;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import org.coronabounce.controllers.Controller;
import org.coronabounce.data.Data;
import org.coronabounce.models.CoquilleBille;
import org.coronabounce.models.Wall;
import org.coronabounce.models.Zone;
import org.coronabounce.mvcconnectors.Controllable;
import org.coronabounce.mvcconnectors.Displayable;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;
import static javafx.scene.paint.Paint.valueOf;

public class MainController
{
    private Controllable controller;              // controller with initial settings
    private Controllable currentController;       // controller with modified settings
    private Displayable model1;                   // left population (population1)
    private Displayable model2;                   // right population (population2)
    private Zone zone1 = null;                    // left population's (population1) zone
    private Zone zone2 = null;                    // right population's (population2) zone
    private List<CoquilleBille> points1;          // list of population1's individuals
    private List<CoquilleBille> points2;          // list of population2's individuals
    private Timeline tlPoints;                    // timeline for animation of point's moving
    private Timeline tlGraph;                     // timeline for animation of graph
    private boolean isLockDown1 = false;          // has left population (population1) lockdown
    private boolean isLockDown2 = false;          // has right population (population2) lockdown
    private boolean isWalls1 = false;             // has left population (population1) mobile bounds
    private boolean isWalls2 = false;             // has right population (population2) mobile bounds
    // private List<Wall> walls;                     // list of mobile bounds

    private XYChart.Series healthy1;              // charts and area chart tor population1's graph
    private XYChart.Series sick1;
    private XYChart.Series recovered1;
    private AreaChart graphPanel1 = null;

    private XYChart.Series healthy2;              // charts and area chart tor population2's graph
    private XYChart.Series sick2;
    private XYChart.Series recovered2;
    private AreaChart graphPanel2 = null;

    @FXML Pane panel1;                            // field with moving points of population1
    @FXML Pane panel2;                            // field with moving points of population2
    @FXML GridPane mainGrid;
    @FXML GridPane gridGraphStat1;                // grid contains statistic's grid (gridStatistic1) and graph (graphPanel1)
    @FXML GridPane gridGraphStat2;                // grid contains statistic's grid (gridStatisti2c) and graph (graphPanel2)
    @FXML GridPane gridStat1;                     // grid contains statistics and texts "Healthy", "Sick", "Recovered"
    @FXML GridPane gridStat2;                     // grid contains statistics and texts "Healthy", "Sick", "Recovered"
    @FXML Label labelHealthy1;                    // labels for gridStatistic1
    @FXML Label labelSick1;
    @FXML Label labelRecovered1;
    @FXML Label labelHealthy2;                    // labels for gridStatistic2
    @FXML Label labelSick2;
    @FXML Label labelRecovered2;
    @FXML MenuBar mbScenario1;
    @FXML MenuBar mbScenario2;
    @FXML MenuItem scenario_1_1;                  //TODO delete them if will not use them in java
    @FXML MenuItem scenario_1_2;
    @FXML MenuItem scenario_1_3;
    @FXML MenuItem scenario_1_4;
    @FXML MenuItem scenario_2_1;
    @FXML MenuItem scenario_2_2;
    @FXML MenuItem scenario_2_3;
    @FXML MenuItem scenario_2_4;
    @FXML Button btnStart;
    @FXML Button btnPause;
    @FXML Button btnReset;
    @FXML Button btnSettings;
    @FXML Button btnLegend;

    //========================= Constructor ===========================================================================/

    public MainController()
    {
        this.tlPoints = null;
        this.tlGraph = null;
        this.controller = new Controller();
        changeController(this.controller);
        this.currentController = controller;
        // this.walls = model1.getListWall();
    }

    /**
     * @summary Reinitialise Zones (and all key variables of class MainController) with new controller (= new settings)
     */
    public void changeController(Controllable c)
    {
        this.zone1 = new Zone(c,isLockDown1,isWalls1,false,1);
        this.model1 = zone1.getPopulation();
        this.points1 = model1.getAllPoints();

        this.zone2 = new Zone(c,isLockDown2,isWalls2,false,1);
        this.model2 = zone2.getPopulation();
        this.points2 = model2.getAllPoints();

        this.healthy1 = new XYChart.Series();
        this.sick1 = new XYChart.Series();
        this.recovered1 = new XYChart.Series();

        this.healthy2 = new XYChart.Series();
        this.sick2 = new XYChart.Series();
        this.recovered2 = new XYChart.Series();
    }

    //========================= Getters / Setters =====================================================================/

    public Controllable getController() { return this.currentController; }

    public void setSettingsController(Controllable c) { this.currentController = c; }

    //========================= Initialisation ========================================================================/

    /**
     * @summary Initialise Graphs, Panels and StatisticGrids
     */
    @FXML
    private void initialize()
    {
        // init graphPanel and fil mainGrid by graphPanel
        initGraphs();

        // init points
        drawPopulation(false);
        drawPopulation(true);

        // init statistic
        updateStatistics();
    }

    /**
     * @summary Initialisation of graphs and filing of mainGrid by graphStatGrid1 and graphStatGrid2
     */
    private void initGraphs()
    {
        // init graphPanel1
        NumberAxis xAxis1 = new NumberAxis(0, this.model1.getData().getNmbr(), 1);
        xAxis1.setTickLabelsVisible(false);
        xAxis1.setTickMarkVisible(false);
        NumberAxis yAxis1 = new NumberAxis(0, 100, 1);
        yAxis1.setTickLabelsVisible(false);
        yAxis1.setTickMarkVisible(false);
        this.graphPanel1 = new AreaChart(xAxis1, yAxis1);
        graphPanel1.getData().addAll(healthy1, sick1, recovered1);
        graphPanel1.setCreateSymbols(false);

        // fil gridGraphStat1 by graphPanel1
        graphPanel1.setHorizontalGridLinesVisible(false);
        graphPanel1.setVerticalGridLinesVisible(false);
        gridGraphStat1.add(graphPanel1, 1, 0);

        graphPanel1.setMinWidth(150);
        graphPanel1.setMinHeight(100);
        graphPanel1.setPrefWidth(USE_COMPUTED_SIZE);
        graphPanel1.setPrefHeight(100);
        graphPanel1.setMaxWidth(USE_COMPUTED_SIZE);
        graphPanel1.setMaxHeight(100);

        // init graphPanel2
        NumberAxis xAxis2 = new NumberAxis(0, this.model1.getData().getNmbr(), 1);
        xAxis2.setTickLabelsVisible(false);
        xAxis2.setTickMarkVisible(false);
        NumberAxis yAxis2 = new NumberAxis(0, 100, 1);
        yAxis2.setTickLabelsVisible(false);
        yAxis2.setTickMarkVisible(false);
        this.graphPanel2 = new AreaChart(xAxis2, yAxis2);
        graphPanel2.getData().addAll(healthy2, sick2, recovered2);
        graphPanel2.setCreateSymbols(false);


        // fil gridGraphStat2 by graphPanel2
        graphPanel2.setHorizontalGridLinesVisible(false);
        graphPanel2.setVerticalGridLinesVisible(false);
        gridGraphStat2.add(graphPanel2, 1, 0);

        graphPanel2.setMinWidth(150);
        graphPanel2.setMinHeight(100);
        graphPanel2.setPrefWidth(USE_COMPUTED_SIZE);
        graphPanel2.setPrefHeight(100);
        graphPanel2.setMaxWidth(USE_COMPUTED_SIZE);
        graphPanel2.setMaxHeight(100);
    }

    //=============================== Interrupters ====================================================================/

    /**
     * @summary Timer interrupter
     */
    private void stopTimer()
    {
        try
        {
            if (zone1 != null)
            {
                zone1.stopTimer(true);
                zone1.getPopulation().stopTimer(true);
            }
            if (zone2 != null)
            {
                zone2.stopTimer(true);
                zone2.getPopulation().stopTimer(true);
            }
        } catch (Exception e)
        {
            System.out.println("An error occurred when trying to stop old Populations Timers");
        }
    }

    /**
     * @summary Timeline interrupter
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
     * @summary Function for button "Start" - create Timeline and launch function moving() on zone
     */
    @FXML
    private void launchMoving()
    {
        this.currentController.setState(Controllable.eState.Working);
        launchPointsAndStat();
        launchDrawGraph();
        zone1.moving();
        zone2.moving();
        changeEnableDisable(btnStart);
    }

    /**
     * @summary Function for button "Settings" - redirect to window settings
     */
    @FXML
    private void switchToSettings() throws IOException
    {
        closePreviousTask();
        App.setRoot("settings");
    }

    /**
     * @summary Function for button "Pause/Resume"
     */
    @FXML
    private void makePauseResume()
    {
        if (this.currentController.getState() == Controllable.eState.Working)
        {
            this.currentController.setState(Controllable.eState.Paused);
        }
        else if (this.currentController.getState() == Controllable.eState.Paused)
        {
            this.currentController.setState(Controllable.eState.Working);
        }
    }

    /**
     * @summary Function for button "Reset"
     */
    @FXML
    private void resetModel()
    {
        closePreviousTask();          //stops Timelines of graph and points, and stop timers of both Populations and Zones
        retainPopulationsAndWalls();

        changeController(currentController);

        changeEnableDisable(btnStart);

        initialize();
    }

    /**
     * @summary Function for button "?"
     */
    @FXML
    private void showLegend()
    {
        Tooltip tooltip = new Tooltip();
        Image image = new Image(getClass().getResourceAsStream("color_scheme_0.jpg"));
        ImageView view = new ImageView(image);
        tooltip.setGraphic(view);
        Tooltip.install(panel1, tooltip);
        Tooltip.install(panel2, tooltip);

        Tooltip tooltip0 = new Tooltip();
        tooltip0.setText("Press this button and enter into elements of application to see theirs legends");
        Tooltip.install(btnLegend, tooltip0);

        Tooltip tooltip1 = new Tooltip();
        tooltip1.setText("Press this button to launch animation");
        Tooltip.install(btnStart, tooltip1);

        Tooltip tooltip2 = new Tooltip();
        tooltip2.setText("Press this button to change parameters:\nsize of population, duration of sick, radius of contamination etc.");
        Tooltip.install(btnSettings, tooltip2);

        Tooltip tooltip3 = new Tooltip();
        tooltip3.setText("Press this button to reload model with same settings");
        Tooltip.install(btnReset, tooltip3);

        Tooltip tooltip4 = new Tooltip();
        tooltip4.setText("Press this button to stop and resume animation");
        Tooltip.install(btnPause, tooltip4);

        Tooltip tooltip5 = new Tooltip();
        tooltip5.setText("Colors are almost the same as for the dots:\ngreen for healthy, red for incubating/sick and yellow for recovered");
        Tooltip.install(graphPanel1, tooltip5);
        Tooltip.install(graphPanel2, tooltip5);

        Tooltip tooltip6 = new Tooltip();
        Tooltip tooltip7 = new Tooltip();
        tooltip6.setText("Choose some scenario for the left population\nDefault without scenario");
        tooltip7.setText("Choose some scenario for the right population\nDefault without scenario");
        Tooltip.install(mbScenario1, tooltip6);
        Tooltip.install(mbScenario2, tooltip7);

    }

    //========================= MenuBar's functions ====================================================================/

    public void left_Scenario_1_Lockdown() throws IOException
    {
        setSettingsController(currentController);
        this.isLockDown1 = true;
        this.isWalls1 = false;
        closePreviousTask();
        changeController(currentController);
        btnStart.setDisable(false);
        this.currentController.setState(Controllable.eState.Paused);
        App.setRoot("corona bounce");
    }

    public void left_Scenario_2_Wall() throws IOException
    {
        setSettingsController(currentController);
        this.isLockDown1 = false;
        this.isWalls1 = true;
        closePreviousTask();
        changeController(currentController);
        initNewPopulation();
        btnStart.setDisable(false);
        this.currentController.setState(Controllable.eState.Paused);
        App.setRoot("corona bounce");
    }

    public void left_Scenario_3_WithoutScenario() throws IOException
    {
        setSettingsController(currentController);
        this.isLockDown1 = false;
        this.isWalls1 = false;
        closePreviousTask();
        changeController(currentController);
        initNewPopulation();
        btnStart.setDisable(false);
        this.currentController.setState(Controllable.eState.Paused);
        App.setRoot("corona bounce");
    }

    public void left_Scenario_4_WallAndLockdown() throws IOException
    {
        setSettingsController(currentController);
        this.isLockDown1 = true;
        this.isWalls1 = true;
        closePreviousTask();
        changeController(currentController);
        initNewPopulation();
        btnStart.setDisable(false);
        this.currentController.setState(Controllable.eState.Paused);
        App.setRoot("corona bounce");
    }

    public void right_Scenario_1_Lockdown() throws IOException
    {
        setSettingsController(currentController);
        this.isLockDown2 = true;
        this.isWalls2 = false;
        closePreviousTask();
        changeController(currentController);
        initNewPopulation();
        btnStart.setDisable(false);
        this.currentController.setState(Controllable.eState.Paused);
        App.setRoot("corona bounce");
    }

    public void right_Scenario_2_Wall() throws IOException
    {
        setSettingsController(currentController);
        this.isLockDown2 = false;
        this.isWalls2 = true;
        closePreviousTask();
        changeController(currentController);
        initNewPopulation();
        btnStart.setDisable(false);
        this.currentController.setState(Controllable.eState.Paused);
        App.setRoot("corona bounce");
    }

    public void right_Scenario_3_WithoutScenario() throws IOException
    {
        setSettingsController(currentController);
        this.isLockDown2 = false;
        this.isWalls2 = false;
        closePreviousTask();
        changeController(currentController);
        initNewPopulation();
        btnStart.setDisable(false);
        this.currentController.setState(Controllable.eState.Paused);
        App.setRoot("corona bounce");
    }

    public void right_Scenario_4_WallAndLockdown() throws IOException
    {
        setSettingsController(currentController);
        this.isLockDown2 = true;
        this.isWalls2 = true;
        closePreviousTask();
        changeController(currentController);
        initNewPopulation();
        btnStart.setDisable(false);
        this.currentController.setState(Controllable.eState.Paused);
        App.setRoot("corona bounce");
    }

    //===================== Functions to use in Settings Controller ===================================================/

    /**
     * @summary Function for correct closing tasks before changing the settings.
     * It stops Timelines of graph and points, and stop timers of both Populations and Zones.
     */
    public void closePreviousTask()
    {
        stopTimeLine(tlPoints);
        stopTimeLine(tlGraph);
        stopTimer();
    }

    /**
     * @summary Initialize graphPanel, fil mainGrid by graphPanel and draw new populations
     */
    public void initNewPopulation()
    {
        retainPopulationsAndWalls();
        initGraphs();
        drawPopulation(true);
        drawPopulation(false);
    }

    /**
     * @summary Change enable/disable the button btn
     */
    public void changeEnableDisable(Button btn)
    {
        if (btn.isDisabled())
        {
            btn.setDisable(false);
        }
        else
        {
            btn.setDisable(true);
        }
    }

    //========================= Button's auxiliary functions ==========================================================/

    /**
     * @summary Function used to provide graph's Timeline a milliseconds appropriated to population size
     * @return 100 ms for small populations and 500 for big one
     */
    private int choosePeriod()
    {
        if (controller.getPersonsCount() <= 100)
        {
            return 100;
        }
        return 500;
    }

    /**
     * @summary Timeline launcher for drawing the graphs in AreaChart
     * To show the layers of AreaChart correctly with superposition we take:
     *      NbRecovered = NbRecovered,
     *      nbSick = nbSick + nbRecovered,
     *      all the rest: NbHealthy + NbIncubating takes like 100%
     */
    private void launchDrawGraph()
    {
        stopTimeLine(tlGraph);
        healthy1.getData().retainAll();
        sick1.getData().retainAll();
        recovered1.getData().retainAll();

        healthy2.getData().retainAll();
        sick2.getData().retainAll();
        recovered2.getData().retainAll();

        model1.saveStatToData();
        model2.saveStatToData();

        tlGraph = new Timeline(new KeyFrame(Duration.millis(choosePeriod()), ev ->
        {
            //long startTime = System.currentTimeMillis();                           // code for debug

            healthy1.getData().clear();
            sick1.getData().clear();
            recovered1.getData().clear();

            healthy2.getData().clear();
            sick2.getData().clear();
            recovered2.getData().clear();

            model1.getData().Lock();
            model2.getData().Lock();

            Vector<Data.Slice> History1 = model1.getData().getFifo();
            Vector<Data.Slice> History2 = model2.getData().getFifo();

            // draw graph
            int x = 0;
            for (Data.Slice Slice : History1)
            {
                healthy1.getData().add(new XYChart.Data(x, 100));
                sick1.getData().add(new XYChart.Data(x, Slice.getPrcSick()));
                recovered1.getData().add(new XYChart.Data(x, Slice.getPrcRecovered()));
                x++;
            }

            int y = 0;
            for (Data.Slice Slice : History2)
            {
                healthy2.getData().add(new XYChart.Data(y, 100));
                sick2.getData().add(new XYChart.Data(y, Slice.getPrcSick()));
                recovered2.getData().add(new XYChart.Data(y, Slice.getPrcRecovered()));
                y++;
            }

            model1.getData().unLock();
            model2.getData().unLock();

            //long stopTime = System.currentTimeMillis();                                // code for debug
            //long diff = stopTime - startTime;
            //System.out.println("Difference: " + diff);
            //System.out.println("Graph Thread: " + Thread.currentThread().getId());

        }));
        tlGraph.setCycleCount(Animation.INDEFINITE);
        tlGraph.play();
    }

    /**
     * @summary Timeline launcher to draw moving points and update statistics
     */
    private void launchPointsAndStat()
    {
        stopTimeLine(tlPoints);

        tlPoints = new Timeline(new KeyFrame(Duration.millis(33), ev ->
        {
            // clean the panels
            retainPopulationsAndWalls();

            // update points
            drawPopulation(true);
            drawPopulation(false);

            // update walls
            drawWalls(true);
            drawWalls(false);

            // update statistic
            updateStatistics();

        }));
        tlPoints.setCycleCount(Animation.INDEFINITE);
        tlPoints.play();
    }

    //========================= Animation auxiliary functions =========================================================/

    /**
     * @summary Takes fresh numbers of healthy, sick, recovered to labels in gridStat
     */
    private void updateStatistics()
    {
        // statistics1
        labelHealthy1.setText(String.valueOf(model1.getNbHealthy()));
        labelSick1.setText(String.valueOf(model1.getNbSick()));
        labelRecovered1.setText(String.valueOf(model1.getNbRecovered()));

        // statistics2
        labelHealthy2.setText(String.valueOf(model2.getNbHealthy()));
        labelSick2.setText(String.valueOf(model2.getNbSick()));
        labelRecovered2.setText(String.valueOf(model2.getNbRecovered()));
    }

    /**
     * @summary Clean panels from all theirs children
     */
    private void retainPopulationsAndWalls()
    {
        panel1.getChildren().retainAll();
        panel2.getChildren().retainAll();
    }

    /**
     * @summary Function call drawPoint() for all points of list
     * @param is_panel1 helps to use this function for both populations
     */
    private void drawPopulation(boolean is_panel1)
    {
        double koeffW = panel1.getWidth()/controller.getSpaceSize()[0];
        double koeffH = panel1.getHeight()/controller.getSpaceSize()[1];
        if (is_panel1)
        {
            for (CoquilleBille cb : points1)
            {
                drawPoint(cb, true, koeffW, koeffH);
            }
        }
        else
        {
            for (CoquilleBille cb : points2)
            {
                drawPoint(cb, false, koeffW, koeffH);
            }
        }
    }

    /**
     * @summary Function which:
     * 1) adapt position in GUI's Pane relative to position in Model's Zone
     * 2) draw point according its status (Healthy, Sick, Recovered, Incubating)
     */
    private void drawPoint(CoquilleBille cb, boolean is_panel1, double koeffW, double koeffH)
    {
        String state = cb.getIndividual().healthState();
        double coordX = cb.getPosition().getX() * koeffW;
        double coordY = cb.getPosition().getY() * koeffH;
        Circle point = new Circle(coordX, coordY, controller.getRadiusDot() * koeffH);
        if (state.equals("Healthy")) {point.setFill(valueOf("70e000"));}    //green
        if (state.equals("Incubating")) {point.setFill(valueOf("ff1830"));}  //red
        if (state.equals("Recovered")) {point.setFill(valueOf("ffd22f"));}  //yellow
        if (state.equals("Sick")){point.setFill(valueOf("a80011"));}     // dark red
        if (is_panel1)
        {
            panel1.getChildren().add(point);
        }
        else
        {
            panel2.getChildren().add(point);
        }
    }

    /**
     * @summary Function of drawing the walls
     * (koeffW and koeffH serve to adapt dimensions the walls during changing dimensions the scene)
     */
    private void drawWalls(boolean is_panel1)
    {
        double koeffW = panel1.getWidth() / controller.getSpaceSize()[0];
        double koeffH = panel1.getHeight() / controller.getSpaceSize()[1];

        if (is_panel1)
        {
          System.out.println(isWalls1);//@a
            if (isWalls1)
            {
                ArrayList<Double> positionX1 = model1.getPositionsOfWalls();
                ArrayList<Double> heightOfWalls1 = model1.getHeigthsOfWalls();
                ArrayList<Double> thicknesses1 = model1.getThicknessesOfWalls();

                for (int i = 0; i < this.model1.getListWall().size(); i++)
                {
                    Rectangle wall1 = new Rectangle((positionX1.get(i) - thicknesses1.get(i) / 4) * koeffW, 0,
                                                    thicknesses1.get(i) * koeffW, heightOfWalls1.get(i) * koeffH);
                    wall1.setFill(valueOf("008B8B"));

                    // put into panel1
                    panel1.getChildren().add(wall1);
                }
            }
        }
        else
        {
            if (isWalls2)
            {
                ArrayList<Double> positionX2 = model2.getPositionsOfWalls();
                ArrayList<Double> heightOfWalls2 = model2.getHeigthsOfWalls();
                ArrayList<Double> thicknesses2 = model2.getThicknessesOfWalls();

                for (int i = 0; i < this.model1.getListWall().size(); i++)
                {
                  System.out.println();
                    Rectangle wall2 = new Rectangle((positionX2.get(i) - thicknesses2.get(i) / 4) * koeffW, 0,
                                                    thicknesses2.get(i) * koeffW, heightOfWalls2.get(i) * koeffH);

                    wall2.setFill(valueOf("008B8B"));

                    // put into panel2
                    panel2.getChildren().add(wall2);
                }
            }
        }
    }
}
