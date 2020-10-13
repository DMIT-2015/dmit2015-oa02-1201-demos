package ca.nait.dmit.domain;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Pattern;

/**
 * The Circle class models a circle shape.
 * 
 * @author Sam Wu
 * @version 2018.01.18
 */
public class Circle {
	
	/**
	 * Return 10 circles where the radius increments by 1 from the current value
	 * @return an array of circles
	 */
	public Circle[] next10Circles() {
		final int MAX_CIRCLES = 10;
		// Create a new array of Circle
		Circle[] circleArray = new Circle[MAX_CIRCLES];
		double currentRadius = radius;
		for (int index = 0; index < MAX_CIRCLES; index += 1) {
			// Create a single Circle
			Circle currentCircle = new Circle(currentRadius++);
			// Add the currentCircle to circleArray
			circleArray[index] = currentCircle;
		}
		return circleArray;
	}
	

	/** The radius of the circle */
	@Pattern(regexp = "/^\\d*\\.?\\d*$/", message="The radius must be a decimal number")
	private String radiusString;
	
	@DecimalMin(value = "1", message = "The radius of the circle must be equal or greater than {value}")
	private double radius;

	/** Construct a circle with a radius 1 */
	public Circle() {
		radius = 1;
	}

	/** Construct a circle with a specified radius */
	public Circle(double newRadius) {
		radius = newRadius;
	}

	/**
	 * Return the radius
	 * 
	 * @return The value in the radius field
	 */
	public double getRadius() {
		return radius;
	}

	/**
	 * Set the radius
	 * 
	 * @param radius The value to store in the radius field
	 */
	public void setRadius(double newRadius) {
//		if (newRadius > 0.5) {
//			this.radius = newRadius;			
//		} else {
//			throw new RuntimeException("The radius of the circle must be greater than 0.5");
//		}
		
		
		this.radius = newRadius;
	}

	
	public String getRadiusString() {
		return radiusString;
	}

	public void setRadiusString(String radiusString) {
		this.radiusString = radiusString;
	}

	/**
	 * Returns the area of the circle using the formula: area = PI * radius * radius
	 * 
	 * @return The calculated area of the circle
	 */
	public double area() {
		return Math.PI * Math.pow(radius, 2);
	}

	/**
	 * Returns the diameter of the circle using the formula: diameter = radius * 2
	 * 
	 * @return The calculated diameter of the circle
	 */
	public double diameter() {
		return radius * 2;
	}

	/**
	 * Returns the circumference of the circle using the formula: circumference = 2
	 * * PI * radius
	 * 
	 * @return The calculated circumference of the circle
	 */
	public double circumference() {
		return 2 * Math.PI * radius;
	}
	
	
}