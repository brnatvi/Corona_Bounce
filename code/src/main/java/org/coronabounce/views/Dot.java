package org.coronabounce.views;

import javafx.scene.shape.Circle;

public class Dot extends Circle
{
    private double coordX;
    private double coordY;
    private double DOT_RADIUS = 2.0;
    private String color;

    public Dot(double cX, double cY)
    {
        this.coordX = cX;
        this.coordY = cY;
        Circle c = new Circle(coordX, coordY, DOT_RADIUS);
        this.color = "#14902b";
    }

    public String getColor() { return this.color; }

    public void changeColor(String newCol) { this.color = newCol; }

}
