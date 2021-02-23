package org.coronabounce.views;

import javafx.scene.shape.Circle;

public class Dot
{
    private double DOT_RADIUS = 2.0;
    private double coordX;
    private double coordY;
    private String color;

    public Dot(double cX, double cY)
    {
        this.coordX = cX;
        this.coordY = cY;
        Circle c = new Circle(coordX, coordY, DOT_RADIUS);
        this.color = "#14902b";
    }

    public void changeColor(String newCol) { this.color = newCol; }

    public String getColor() { return this.color; }
}
