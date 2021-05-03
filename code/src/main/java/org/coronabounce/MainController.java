package org.coronabounce;

import java.util.Vector;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import org.coronabounce.controllers.Controller;
import org.coronabounce.data.Data;
import org.coronabounce.models.Zone;
import org.coronabounce.mvcconnectors.Controllable;
import org.coronabounce.mvcconnectors.Displayable;
import org.coronabounce.vues.Graph;
import org.coronabounce.vues.Renderer;

/**
 * Main controller class. Runs by launch() in App class and launches all processes.
 */
public class MainController
{
    /** Controller contained initial or modified settings **/
    private Controllable currentController;
    /** Left population (population1) **/
    private Displayable model1;
    /** Right population (population2) **/
    private Displayable model2;
    /** Left population's (population1) zone **/
    private Zone zone1;
    /** Right population's (population2) zone **/
    private Zone zone2;
    /** Timeline for animation of point's moving **/
    private Timeline tlPoints;
    /** Timeline for animation of graph **/
    private Timeline tlGraph;
    /** Boolean for population1's soft lockdown **/
    private boolean isLockDown1;
    /** Boolean for population2's soft lockdown **/
    private boolean isLockDown2;
    /** Boolean for population1's boundaries scenario **/
    private boolean isWalls1;
    /** Boolean for population2's boundaries scenario **/
    private boolean isWalls2;
    /** Boolean for population1's strict lockdown **/
    private boolean isRestrictionMovement1;
    /** Boolean for population2's strict lockdown **/
    private boolean isRestrictionMovement2;
    /** Graph for population1 **/
    private Graph graph1;
    /** Graph for population2 **/
    private Graph graph2;
    /** Renderer of points and walls for population1 **/
    private Renderer renderer1;
    /** Renderer of points and walls for population2 **/
    private Renderer renderer2;

    /** Field with moving points of population1 **/
    @FXML Pane panel1;
    /** Field with moving points of population2 **/
    @FXML Pane panel2;
    @FXML GridPane mainGrid;
    /** Grid contains statistic's grid (gridStatistic1) and graph (graphPanel1) **/
    @FXML GridPane gridGraphStat1;
    /** Grid contains statistic's grid (gridStatisti2c) and graph (graphPanel2) **/
    @FXML GridPane gridGraphStat2;
    /** Grid contains statistics and texts "Healthy", "Sick", "Recovered" for population1 **/
    @FXML GridPane gridStat1;
    /** Grid contains statistics and texts "Healthy", "Sick", "Recovered" for population2 **/
    @FXML GridPane gridStat2;
    /** Label of healthy for population1 **/
    @FXML Label labelHealthy1;
    /** Label of sick for population1 **/
    @FXML Label labelSick1;
    /** Label of recovered for population1 **/
    @FXML Label labelRecovered1;
    /** Label of healthy for population2 **/
    @FXML Label labelHealthy2;
    /** Label of sick for population2 **/
    @FXML Label labelSick2;
    /** Label of recovered for population2 **/
    @FXML Label labelRecovered2;
    /** Menu bar of scenarios for population1 **/
    @FXML MenuBar mbScenario1;
    /** Menu bar of scenarios for population2 **/
    @FXML MenuBar mbScenario2;
    /** Button "Start" **/
    @FXML Button btnStart;
    /** Button "Pause-Resume" **/
    @FXML Button btnPause;
    /** Button "Reset" **/
    @FXML Button btnReset;
    /** Button "Settings" **/
    @FXML Button btnSettings;
    /** Button "Help" **/
    @FXML Button btnLegend;

    //========================= Constructor ===========================================================================/

    /**
     * Instantiates a new Main controller.
     */
    public MainController()
    {
        this.tlPoints = null;
        this.tlGraph = null;
        this.currentController = new Controller();
        changeController(this.currentController);
        isLockDown1 = false;
        isLockDown2 = false;
        isWalls1 = false;
        isWalls2 = false;
        isRestrictionMovement1 = false;
        isRestrictionMovement2 = false;
    }

    /**
     * {@summary Reinitialise Zones (and all key variables of class MainController) with new controller (= new settings)}
     *
     * @param controller the controller
     */
    public void changeController(Controllable controller)
    {
        this.zone1 = new Zone(controller,isLockDown1,isWalls1,isRestrictionMovement1);
        this.model1 = zone1.getPopulation();
        this.graph1 = new Graph(model1);
        this.renderer1 = new Renderer(model1, currentController);

        this.zone2 = new Zone(controller,isLockDown2,isWalls2,isRestrictionMovement2);
        this.model2 = zone2.getPopulation();
        this.graph2 = new Graph(model2);
        this.renderer2 = new Renderer(model2, currentController);
    }

    //========================= Getters / Setters =====================================================================/

    /**
     * {@summary Returns current controller which consist all settings done.}
     *
     * @return current controller
     */
    public Controllable getController() { return this.currentController; }

    /**
     * {@summary Uploads new current controller}
     *
     * @param newCurrentController the newCurrentController
     */
    public void setSettingsController(Controllable newCurrentController) { this.currentController = newCurrentController; }

    //========================= Initialisation ========================================================================/

    /**
     * {@summary Initialises Graphs, Panels and StatisticGrids and pauses all timers in model}
     */
    @FXML
    public void initialize()
    {
        retainPopulationsAndWalls();

        // init graphPanel and fil mainGrid by graphPanel
        initGraphs();

        // init points
        drawPopulations();

        // init statistic
        updateStatistics();

        // pause all timers
        this.currentController.setState(Controllable.eState.Paused);

        btnPause.setDisable(true);
    }

    /**
     * {@summary Initialisation of graphs and filing of mainGrid by graphStatGrid1 and graphStatGrid2}
     */
    private void initGraphs()
    {
        graph1.initGraphs(this.gridGraphStat1);
        graph2.initGraphs(this.gridGraphStat2);
    }

    //=============================== Interrupters ====================================================================/

    /**
     * {@summary Timer's interrupter}
     */
    private void stopTimer()
    {
        try
        {
            if (zone1 != null)
            {
                zone1.stopTimerTask(true);
                zone1.getPopulation().stopTimer(true);
            }
            if (zone2 != null)
            {
                zone2.stopTimerTask(true);
                zone2.getPopulation().stopTimer(true);
            }
        } catch (Exception e)
        {
            System.out.println("An error occurred when trying to stop old Populations Timers");
        }
    }

    /**
     * {@summary Timeline's interrupter.}
     * @param t timeline to stop.
     */
    private void stopTimeLine(Timeline t)
    {
        if (null != t)
        {
            t.stop();
            t = null;
        }
    }

    /**
     * {@summary Function for correct tasks closing before changing the settings.}
     * Stops Timelines of graph and points, and stops timers of both Populations and Zones.
     */
    private void closePreviousTask()
    {
        stopTimeLine(tlPoints);
        stopTimeLine(tlGraph);
        stopTimer();
    }

    //========================= Button's functions ====================================================================/

    /**
     * {@summary Function for button "Start".}
     * Creates Timeline and launch function moving() on zone.
     */
    @FXML
    private void launchMoving()
    {
        this.currentController.setState(Controllable.eState.Working);
        launchPointsAndStat();
        launchDrawGraph();
        zone1.moving();
        zone2.moving();
        btnStart.setDisable(true);
        btnPause.setDisable(false);
    }

    /**
     * {@summary Function for button "Settings"}
     * Redirects to window "Settings".
     */
    @FXML
    private void switchToSettings()
    {
        closePreviousTask();
        this.currentController.setState(Controllable.eState.Paused);
        App.setRoot("settings");
    }

    /**
     * {@summary Function for button "Pause/Resume".}
     * Pauses animation.
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
     * {@summary Function for button "Reset"}
     * Resets animation with the same parameters (settings) and scenarios applied.
     */
    @FXML
    private void resetModel()
    {
        // stops Timelines of graph and points, and stop timers of both Populations and Zones
        closePreviousTask();
        retainPopulationsAndWalls();
        changeController(currentController);
        btnStart.setDisable(false);

        //initializes new populations, statistic and graphs and pauses all timers
        initialize();
    }

    /**
     * {@summary Function for button "?".}
     * Creates tooltips for all elements of the scene.
     * Tooltips are showed during few seconds, then need to push "?" again.
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
        Tooltip.install(graph1.getGraphPanel(), tooltip5);
        Tooltip.install(graph2.getGraphPanel(), tooltip5);

        Tooltip tooltip6 = new Tooltip();
        Tooltip tooltip7 = new Tooltip();
        tooltip6.setText("Choose some scenario for the left population\nDefault without scenario");
        tooltip7.setText("Choose some scenario for the right population\nDefault without scenario");
        Tooltip.install(mbScenario1, tooltip6);
        Tooltip.install(mbScenario2, tooltip7);

    }

    //========================= MenuBar's functions ====================================================================/

    /**
     * {@summary Scenario "Soft lockdown" for the left population.}
     */
    public void left_Scenario_1_SoftLockdown()
    {
        applyScenario( true, false, false, model1);
    }

    /**
     * {@summary Scenario "Strict lockdown" for the left population.}
     */
    public void left_Scenario_2_StrictLockdown()
    {
        applyScenario( false, false, true, model1);
    }

    /**
     * {@summary Scenario "Boundaries" for the left population.}
     */
    public void left_Scenario_3_Wall()
    {
        applyScenario( false, true, false, model1);
    }

    /**
     * {@summary Scenario "Boundaries and soft lockdown" for the left population.}
     */
    public void left_Scenario_4_WallAndLockdown()
    {
        applyScenario( true, true, false, model1);
    }

    /**
     * {@summary Scenario "Without scenario" for the left population.}
     */
    public void left_Scenario_5_WithoutScenario()
    {
        applyScenario( false, false, false, model1);
    }

    /**
     * {@summary Scenario "Soft lockdown" for the right population.}
     */
    public void right_Scenario_1_SoftLockdown()
    {
        applyScenario( true, false, false, model2);
    }

    /**
     * {@summary Scenario "Strict lockdown" for the right population.}
     */
    public void right_Scenario_2_StrictLockdown()
    {
        applyScenario( false, false, true, model2);
    }

    /**
     * {@summary Scenario "Boundaries" for the right population.}
     */
    public void right_Scenario_3_Wall()
    {
        applyScenario( false, true, false, model2);
    }

    /**
     * {@summary Scenario "Boundaries and soft lockdown" for the right population.}
     */
    public void right_Scenario_4_WallAndLockdown()
    {
        applyScenario( true, true, false, model2);
    }

    /**
     * {@summary Scenario "Without scenario" for the right population.}
     */
    public void right_Scenario_5_WithoutScenario()
    {
        applyScenario( false, false, false, model2);
    }

    //========================= Button's auxiliary functions ==========================================================/

    /**
     * {@summary Choose a period for graph's Timeline, appropriated to population size}
     * it helps to reduce workload on threads for big size populations
     * @return 100 ms for small populations and 500 ms for big ones
     */
    private int choosePeriod()
    {
        if (currentController.getPersonsCount() <= 500)
        {
            return 100;
        }
        return 200;
    }

    /**
     * {@summary Timeline launcher for drawing the graphs in AreaChart}
     * To show correctly superposed layers in AreaChart we take:
     * <ul>
     * <li> NbHealthy taken as 100% (bottom layer)
     * <li> nbSick = nbSick + NbIncubating + nbRecovered (middle layer)
     * <li> NbRecovered = NbRecovered (top layer)
     * </ul>
     * Superposed they present ratio of these tree values (nbHealthy, nbSick/Incubating and nbRecovered) in 100%
     */
    private void launchDrawGraph()
    {
        stopTimeLine(tlGraph);

        graph1.retainCharts();
        graph2.retainCharts();

        model1.saveStatToData();
        model2.saveStatToData();

        tlGraph = new Timeline(new KeyFrame(Duration.millis(choosePeriod()), ev ->
        {
            //long startTime = System.currentTimeMillis();                           // code for debug

            graph1.clearData();
            graph2.clearData();

            model1.getData().Lock();
            model2.getData().Lock();

            Vector<Data.Slice> History1 = model1.getData().getFifo();
            Vector<Data.Slice> History2 = model2.getData().getFifo();

            // draw graph
            int x = 0;
            for (Data.Slice Slice : History1)
            {
                graph1.drawGraph(Slice, x);
                x++;
            }

            int y = 0;
            for (Data.Slice Slice : History2)
            {
                graph2.drawGraph(Slice, y);
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
     * {@summary Timeline launcher to draw moving points, walls and update statistics}
     */
    private void launchPointsAndStat()
    {
        stopTimeLine(tlPoints);

        tlPoints = new Timeline(new KeyFrame(Duration.millis(33), ev ->
        {
            // clean the panels
            retainPopulationsAndWalls();

            // update points
            drawPopulations();

            // update walls
            drawWalls();

            // update statistic
            updateStatistics();
        }));
        tlPoints.setCycleCount(Animation.INDEFINITE);
        tlPoints.play();
    }

    /**
     * {@summary Apply scenarios according boolean values. }
     * Function which :
     * <ul>
     * <li> save settings in current controller;
     * <li> initializes booleans managed scenarios;
     * <li> close previous task;
     * <li> update current controller;
     * <li> initialize new zones, populations, points, statistics and graphs;
     * <li> disable button Start;
     * <li> switch to main window.
     * </ul>
     */
    private void applyScenario(boolean isLockDown, boolean isWalls, boolean isRestrict, Displayable pop)
    {
        setSettingsController(currentController);
        if (pop.equals(model1))
        {
            this.isLockDown1 = isLockDown;
            this.isWalls1 = isWalls;
            this.isRestrictionMovement1 = isRestrict;
        }
        else
        {
            this.isLockDown2 = isLockDown;
            this.isWalls2 = isWalls;
            this.isRestrictionMovement2 = isRestrict;
        }
        closePreviousTask();
        changeController(currentController);
        initialize();
        btnStart.setDisable(false);
        App.setRoot("corona bounce");
    }

    //========================= Animation auxiliary functions =========================================================/

    /**
     * {@summary Takes fresh numbers of healthy, sick, recovered to labels in gridStat}
     */
    private void updateStatistics()
    {
        // statistics1
        labelHealthy1.setText(String.valueOf(model1.getNbHealthy()));
        labelSick1.setText(String.valueOf(model1.getNbInfected()));
        labelRecovered1.setText(String.valueOf(model1.getNbRecovered()));

        // statistics2
        labelHealthy2.setText(String.valueOf(model2.getNbHealthy()));
        labelSick2.setText(String.valueOf(model2.getNbInfected()));
        labelRecovered2.setText(String.valueOf(model2.getNbRecovered()));
    }

    /**
     * {@summary Cleans panels from all theirs children}
     */
    private void retainPopulationsAndWalls()
    {
        panel1.getChildren().retainAll();
        panel2.getChildren().retainAll();
    }

    /**
     * {@summary Renderer of populations.}
     * Function calls drawPoint() for all points of list.
     */
    private void drawPopulations()
    {
        renderer1.drawPopulation(panel1);
        renderer2.drawPopulation(panel2);
    }

    /**
     * {@summary Renderer of walls}
     * koeffW and koeffH serve to adapt dimensions the walls during changing dimensions the scene.
     */
    private void drawWalls()
    {
        renderer1.drawWall(panel1, isWalls1);
        renderer2.drawWall(panel2, isWalls2);
    }
}
