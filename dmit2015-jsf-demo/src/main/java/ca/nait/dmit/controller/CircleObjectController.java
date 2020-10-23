package ca.nait.dmit.controller;

import ca.nait.dmit.model.Circle;
import org.omnifaces.util.Messages;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class CircleObjectController implements Serializable {

    private Circle currentCircle = new Circle();

    public Circle getCurrentCircle() {
        return currentCircle;
    }

    public String area() {
        Messages.addInfo(null,"The area of the circle with a radus of {0} is {1}", currentCircle.getRadius(), currentCircle.area() );
        return "";
    }

    public String circumference() {
        Messages.addInfo(null,"The circumference of the circle with a radius of {0} is {1}", currentCircle.getRadius(), currentCircle.circumference());
        return "";
    }

    public String diameter() {
        Messages.addInfo(null,"The diameter of the circle with a radius of {0} is {1}", currentCircle.getRadius(), currentCircle.diameter());
        return "";
    }
}
