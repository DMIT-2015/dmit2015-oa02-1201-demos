package ca.nait.dmit.controller;

import org.omnifaces.util.Messages;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class CircleController implements Serializable {

    private double radius = 1;

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public String area() {
        double areaOfCircle = Math.PI * radius * radius;
        Messages.addInfo(null,"The area of the circle with a radus of {0} is {1}", radius, areaOfCircle );
        return "";
    }

    public String circumference() {
        double circumferenceOfCircle = 2 * Math.PI * radius;
        Messages.addInfo(null,"The circumference of the circle with a radius of {0} is {1}", radius, circumferenceOfCircle);
        return "";
    }

    public String diameter() {
        double diameterOfCircle = 2 * radius;
        Messages.addInfo(null,"The diameter of the circle with a radius of {0} is {1}", radius, diameterOfCircle);
        return "";
    }
}
