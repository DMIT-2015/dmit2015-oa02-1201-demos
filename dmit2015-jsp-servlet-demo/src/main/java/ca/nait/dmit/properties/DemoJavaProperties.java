package ca.nait.dmit.properties;

import java.io.IOException;
import java.util.Properties;

import javax.swing.JOptionPane;

public class DemoJavaProperties {

	public static void main(String[] args) {

		DemoJavaProperties app = new DemoJavaProperties();
		try {
			app.readFromPropertiesFile();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Failed to read from properties file");
		}

	}
	
	public void readFromPropertiesFile() throws IOException {
		// 1. Construct a Properties object
		Properties appProps = new Properties();
		// 2. Load data from a file to the Properties object
		appProps.load(getClass().getResourceAsStream("/application.properties"));
		// 3. Read a property from the Properties object
		String username = appProps.getProperty("username");
		String mailto = appProps.getProperty("mailto");
		String mailToName = appProps.getProperty("mailto.name");
		// 4. Do something with the property
		String message = String.format("username: %s\n Email: %s\n Name: %s", username, mailto, mailToName);
		// Print the message to the Console
		System.out.println(message);
		// Show the message using a Java Swing OptionPane
		JOptionPane.showMessageDialog(null, message);
		
	}

}
