package ca.nait.dmit.servlet;

import java.time.LocalDateTime;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ApplicationListener implements ServletContextListener {

	public static LocalDateTime applicationStartupTime = null;

	/**
	 * This method is executed when the webapp is stopped.
	 */
	public void contextDestroyed(ServletContextEvent sce) {
		ServletContext application = sce.getServletContext();
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
		application.setAttribute("applicationStartupTime", applicationStartupTime);
		application.log(application.getServletContextName() + " started at " + applicationStartupTime);
//		System.out.println(application.getServletContextName() + " started at " + applicationStartupTime);
	}
}

