package ca.nait.dmit.domain;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class Student {

	@NotBlank(message="StudentID is required")
//	@Pattern(regexp = "^[0-9]{9,9}$", message = "StudentID must contain exactly 9 digits")
	@Pattern(regexp = "[0-9]{9,9}", message = "StudentID must contain exactly 9 digits")
	private String studentId;	// studentId is required and must contain exactly 9 digits
	
	@NotBlank(message = "First name is required")
//	@NotNull(message = "First name is required")
//	@NotEmpty(message = "First name is required")
	@Size(min = 1, message = "First name must contain at least one character")
	private String firstName;	// firstName is required and must contain at least 1 characters
	
	@NotBlank(message = "Last name is required")
	@Size(min = 2, message = "Last name must contain at least two characters")
	private String lastName;	// lastName is required and must contain at least 2 characters
	
	@Min(value = 18, message = "Age must be between 18 and 65")
	@Max(value = 65, message = "Age must be between 18 and 65")
	private int age;			// age must be between 18 and 65
	
	@DecimalMin(value = "0.00", message="GPA must be between 0.0 and 4.0")
	@DecimalMax(value = "4.00", message="GPA must be between 0.0 and 4.0")
	private BigDecimal gpa;		// gpa must be between 0.0 and 4.0
	
}
