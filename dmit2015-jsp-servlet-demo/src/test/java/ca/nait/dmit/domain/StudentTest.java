package ca.nait.dmit.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.groups.Default;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class StudentTest {

	static ValidatorFactory validatorFactory;
	static Validator validator;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
	}

	@Test
	void testSetFirstName() {
		Student student1 = new Student();
		student1.setFirstName(null);
		
		Set<ConstraintViolation<Student>> constraintViolations = validator.validateProperty(student1, "firstName", Default.class);
		assertEquals(1, constraintViolations.size());
		assertEquals("First name is required", constraintViolations.iterator().next().getMessage());
		
		student1.setFirstName("");
		constraintViolations = validator.validateProperty(student1, "firstName", Default.class);
		assertEquals(2, constraintViolations.size());

		student1.setFirstName("          ");
		constraintViolations = validator.validateProperty(student1, "firstName", Default.class);
		assertEquals(1, constraintViolations.size());		

		student1.setFirstName("J");
		constraintViolations = validator.validateProperty(student1, "firstName", Default.class);
		assertEquals(0, constraintViolations.size());		
	}
	
	@Test
	void testAge() {
		Student student1 = new Student();
		student1.setAge(17);
		
		Set<ConstraintViolation<Student>> constraintViolations = validator.validateProperty(student1, "age", Default.class);
		assertEquals(1, constraintViolations.size());		
		assertEquals("Age must be between 18 and 65", constraintViolations.iterator().next().getMessage());

		student1.setAge(66);
		
		constraintViolations = validator.validateProperty(student1, "age", Default.class);
		assertEquals(1, constraintViolations.size());		
		assertEquals("Age must be between 18 and 65", constraintViolations.iterator().next().getMessage());
		
		student1.setAge(18);		
		constraintViolations = validator.validateProperty(student1, "age", Default.class);
		assertEquals(0, constraintViolations.size());		

		student1.setAge(65);		
		constraintViolations = validator.validateProperty(student1, "age", Default.class);
		assertEquals(0, constraintViolations.size());		

	}
	
	@Test
	void testGpa() {
		Student student1 = new Student();
		student1.setGpa(BigDecimal.valueOf(-0.5));
		
		Set<ConstraintViolation<Student>> constraintViolations = validator.validateProperty(student1, "gpa", Default.class);
		assertEquals(1, constraintViolations.size());		
		assertEquals("GPA must be between 0.0 and 4.0", constraintViolations.iterator().next().getMessage());

		student1.setGpa(BigDecimal.valueOf(4.1));
		
		constraintViolations = validator.validateProperty(student1, "gpa", Default.class);
		assertEquals(1, constraintViolations.size());		
		assertEquals("GPA must be between 0.0 and 4.0", constraintViolations.iterator().next().getMessage());
		
		student1.setGpa(BigDecimal.valueOf(3.0));
		constraintViolations = validator.validateProperty(student1, "gpa", Default.class);
		assertEquals(0, constraintViolations.size());		
	}

	@Test
	void testStudentId() {
		Student student1 = new Student();
		student1.setStudentId("12345678");
		
		Set<ConstraintViolation<Student>> constraintViolations = validator.validateProperty(student1, "studentId", Default.class);
		assertEquals(1, constraintViolations.size());		
		assertEquals("StudentID must contain exactly 9 digits", constraintViolations.iterator().next().getMessage());

		student1.setStudentId("1234567890");
		
		constraintViolations = validator.validateProperty(student1, "studentId", Default.class);
		assertEquals(1, constraintViolations.size());		
		assertEquals("StudentID must contain exactly 9 digits", constraintViolations.iterator().next().getMessage());

		student1.setStudentId("a123456789z");
		
		constraintViolations = validator.validateProperty(student1, "studentId", Default.class);
		assertEquals(1, constraintViolations.size());		
		assertEquals("StudentID must contain exactly 9 digits", constraintViolations.iterator().next().getMessage());

		student1.setStudentId("123456789");
		constraintViolations = validator.validateProperty(student1, "studentId", Default.class);
		assertEquals(0, constraintViolations.size());		
	}
}
