package ca.nait.dmit.servlet;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ApplicationListener implements ServletContextListener {

	public static LocalDateTime applicationStartupTime = null;
	public static final String SESSION_COUNT = "sessionCount";

	/**
	 * This method is executed when the webapp is stopped.
	 */
	public void contextDestroyed(ServletContextEvent sce) {
		ServletContext application = sce.getServletContext();
		application.log("DMIT2015 Demo: webapp shutdown");
		application.log(application.getServletContextName() + " stopped at " + LocalDateTime.now());
//		System.out.println(application.getServletContextName() + " stopped at " + LocalDateTime.now());
		
	}

	/**
	 * This method is executed at the startup of the webapp.
	 */
	public void contextInitialized(ServletContextEvent sce) {
		// ServletContext contains methods for communicating with the servlet/web container
		applicationStartupTime = LocalDateTime.now();
		ServletContext application = sce.getServletContext();
		application.log("DMIT2015 Demo: webapp started");
		application.setAttribute("applicationStartupTime", applicationStartupTime);
		application.log(application.getServletContextName() + " started at " + applicationStartupTime);
//		System.out.println(application.getServletContextName() + " started at " + applicationStartupTime);
		
		// Store the number of session in application scope
		application.setAttribute(SESSION_COUNT, 0);
		
		// Create a new Properties object
		Properties userProps = new Properties();
		try {
			// Load the Properties object with data from "/users.properties"
			userProps.load(getClass().getResourceAsStream("/users.properties"));
			// Store the appProperties in application scope
			application.setAttribute("userProps", userProps);
		} catch (IOException e) {
			application.log("Server loading user.properties");
			e.printStackTrace();
		}
		
	}
}

