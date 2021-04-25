package org.coronabounce.vues;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import org.coronabounce.models.CoquilleBille;
import org.coronabounce.mvcconnectors.Controllable;
import org.coronabounce.mvcconnectors.Displayable;

import java.util.ArrayList;
import java.util.List;

import static javafx.scene.paint.Paint.valueOf;

/**
 * Part of View - class to draw the points and boundaries.
 */
public class Renderer
{
    /** Population - model to draw points and walls. **/
    private Displayable model;
    /** Current controller. **/
    private Controllable currentController;
    /** List of points (individuals). **/
    private List<CoquilleBille> points;

    /**
     * Instantiates a new Renderer.
     * @param population the population
     * @param controller the current controller
     */
    public Renderer(Displayable population, Controllable controller)
    {
        this.model = population;
        this.currentController = controller;
        this.points = model.getAllPoints();
    }


    /**
     * {@summary Renderer of populations.}
     * Function call drawPoint() for all points in population.
     * @param panel the panel
     */
    public void drawPopulation(Pane panel)
    {
        double koeffW = panel.getWidth()/currentController.getSpaceSize()[0];
        double koeffH = panel.getHeight()/currentController.getSpaceSize()[1];
        for (CoquilleBille cb : this.points)
        {
            drawPoint(cb, koeffW, koeffH, panel);
        }
    }

    /**
     * {@summary Renderer of points.}
     * Function which:
     * <ul>
     * <li> adapt position in GUI's Pane relative to position in Model's Zone
     * <li> draw point according its status (Healthy, Sick, Recovered, Incubating)
     * </ul>
     *
     * @param cb     Coquille bille
     * @param koeffW Coefficient for width
     * @param koeffH Coefficient for height
     * @param panel  Panel
     */
    public void drawPoint(CoquilleBille cb, double koeffW, double koeffH, Pane panel)
    {
        String state = cb.getIndividual().healthState();
        double coordX = cb.getCurrentPosition().getX() * koeffW;
        double coordY = cb.getCurrentPosition().getY() * koeffH;
        Circle point = new Circle(coordX, coordY, currentController.getRadiusDot() * koeffH);
        if (state.equals("Healthy")) {point.setFill(valueOf("70e000"));}    //green
        if (state.equals("Incubating")) {point.setFill(valueOf("ff1830"));}  //red
        if (state.equals("Recovered")) {point.setFill(valueOf("ffd22f"));}  //yellow
        if (state.equals("Sick")){point.setFill(valueOf("a80011"));}     // dark red
        panel.getChildren().add(point);
    }

    /**
     * {@summary Renderer of walls}
     * koeffW and koeffH serve to adapt dimensions the walls during changing dimensions the scene.
     * @param panel   Panel
     * @param isWalls Boolean serves to know if it is scenario with walls.
     */
    public void drawWall(Pane panel, boolean isWalls)
    {
        double koeffW = panel.getWidth() / currentController.getSpaceSize()[0];
        double koeffH = panel.getHeight() / currentController.getSpaceSize()[1];
        if (isWalls)
            {
                ArrayList<Double> positionX1 = model.getPositionsOfWalls();
                ArrayList<Double> heightOfWalls1 = model.getHeigthsOfWalls();
                ArrayList<Double> thicknesses1 = model.getThicknessesOfWalls();

                for (int i = 0; i < currentController.getWallsCount(); i++)
                {
                    Rectangle wall = new Rectangle((positionX1.get(i) - thicknesses1.get(i) / 2) * koeffW, 0,
                            thicknesses1.get(i) * koeffW, heightOfWalls1.get(i) * koeffH);
                    wall.setFill(valueOf("008B8B"));

                    panel.getChildren().add(wall);
                }
            }
    }
}
