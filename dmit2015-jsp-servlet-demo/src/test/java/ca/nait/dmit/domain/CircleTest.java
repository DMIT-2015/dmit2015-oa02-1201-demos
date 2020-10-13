package ca.nait.dmit.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.Test;

class CircleTest {
	
	@Test
	void testRadiusValidationFailure()
	{
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		
		// Constructor a circle with a radius of 0.5
		Circle circle1 = new Circle(0.5);
		// Validate the constraints in the circle1 object
		Set<ConstraintViolation<Circle>> constraintViolations = validator.validate(circle1, javax.validation.groups.Default.class);
		assertEquals(1, constraintViolations.size());
		assertEquals("The radius of the circle must be equal or greater than 1",
				constraintViolations.iterator().next().getMessage());
		
	}
	
	@Test
	void testRadiusValidationPass()
	{
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		
		// Constructor a circle with a radius of 5
		Circle circle1 = new Circle(5);
		// Validate the constraints in the circle1 object
		Set<ConstraintViolation<Circle>> constraintViolations = validator.validate(circle1, javax.validation.groups.Default.class);
		assertEquals(0, constraintViolations.size());
		
	}
	

	@Test
	void testGetArea() {
		// Create a Circle object with a radius 5
		Circle circle1 = new Circle(5);
		// Verify the area is 78.54
		assertEquals(78.54, circle1.area(), 0.001);
	}

	@Test
	void testGetDiameter() {
		// Create a Circle object with a radius 5
		Circle circle1 = new Circle(5);
		// Verify the diameter is 10
		assertEquals(10, circle1.diameter());
	}

	@Test
	void testGetCircumference() {
		// Create a Circle object with a radius 5
		Circle circle1 = new Circle(5);
		// Verify the circumference is 31.42
		assertEquals(31.42, circle1.circumference(), 0.005);
	}

	@Test
	void testAllThreeMethods() {
		// Create a Circle object with a radius 5
		Circle circle1 = new Circle(5);
		assertAll("all methods", 
			() -> assertEquals(78.54, circle1.area(), 0.01),
			() -> assertEquals(10, circle1.diameter()),
			() -> assertEquals(31.42, circle1.circumference(), 0.005)
		);
	}

}
