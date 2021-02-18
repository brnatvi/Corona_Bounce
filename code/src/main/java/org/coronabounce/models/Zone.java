package org.coronabounce.models;

public class Zone {
    private Population p;
    private static double width;
    private static double height;
    public Zone(double w,double h , int nbIndividual){
        this.width=w;
        this.height=h;
        this.p=new Population(nbIndividual);
    }
    public static void setHeight(double height) {Zone.height = height;
    }

    public static void setWidth(double width){
        Zone.width = width;
    }

    public static double getWidth() {
        return width;
    }

    public static double getHeight() {
        return height;
    }

    public Population getP() {
        return p;
    }

    public void test(){
        p.printPop();
        p.printMovement();

    }
}
