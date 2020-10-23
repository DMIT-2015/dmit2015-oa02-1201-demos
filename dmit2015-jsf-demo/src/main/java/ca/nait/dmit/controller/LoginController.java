package ca.nait.dmit.controller;

import lombok.Getter;
import lombok.Setter;
import org.omnifaces.util.Messages;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class LoginController implements Serializable {

    @Getter @Setter
    private String username;

    @Getter @Setter
    private String password;

    public String login() {
        String nextUrl = "";
        if (username.equalsIgnoreCase("user2015") && password.equals("Password2015")) {
//            nextUrl = "/protected/home";
            nextUrl = "/protected/home?faces-redirect=true";
        } else {
            Messages.addError(null,"Incorrect username and/or password.");
        }
        return nextUrl;
    }

}
