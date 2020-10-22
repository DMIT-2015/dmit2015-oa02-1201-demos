package ca.nait.dmit.controller;

import org.omnifaces.util.Messages;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named      // allows you to access an object of this class with the name sessionScopeCounterController using EL
@ApplicationScoped  // create this object once for the application
                    // class must implement Serializable
public class ApplicationScopeCounterController implements Serializable {

    private int counter = 0;

    public int getCounter() {
        return counter;
    }

    public String submit() {
        counter += 1;
        Messages.addInfo(null,"ApplicationScoped Counter = {0}", counter);
        Messages.addInfo("counterResult", "ApplicationScoped Count Result = {0}", counter);
        return "";
    }
}
