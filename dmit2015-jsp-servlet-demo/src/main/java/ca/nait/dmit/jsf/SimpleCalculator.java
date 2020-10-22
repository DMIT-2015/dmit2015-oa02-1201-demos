package ca.nait.dmit.jsf;

import lombok.Getter;
import lombok.Setter;
import org.omnifaces.util.Messages;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named          // allows you to access an object of this class using JSF EL
@RequestScoped  // create an object for one request and discard after sending a response
public class SimpleCalculator {

    @Getter @Setter
    private double operand1 = 1;

    @Getter @Setter
    private double operand2 = 2;

    @Getter @Setter
    private double result;

    public String add() {
        result = operand1 + operand2;
        String message = String.format("%.1f + %.1f = %.1f", operand1,operand2,result);
        Messages.addInfo(null,message);
        return "";
    }

    public String subtract() {
        result = operand1 - operand2;
        String message = String.format("%.1f - %.1f = %.1f", operand1,operand2,result);
        Messages.addInfo("subtract",message);
        return "";
    }

    public String multiply() {
        result = operand1 * operand2;
        String message = String.format("%.1f * %.1f = %.1f", operand1,operand2,result);
        Messages.addInfo(null,message);
        return "";
    }

    public String divide() {
        result = operand1 / operand2;
        String message = String.format("%.1f / %.1f = %.1f", operand1,operand2,result);
        Messages.addInfo(null,message);
        return "";
    }
}
