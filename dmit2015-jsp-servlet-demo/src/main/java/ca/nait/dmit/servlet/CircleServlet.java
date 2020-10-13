package ca.nait.dmit.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import ca.nait.dmit.domain.Circle;

/**
 * Servlet implementation class CircleServlet
 */
@WebServlet("/servlet/CircleServlet")
public class CircleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CircleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Retrieve the radius value from the request
		String radiusString = request.getParameter("radius");
		// Convert the radius from a String to a double
		double radius = Double.parseDouble(radiusString);
		// Construct with Circle with the radius
		Circle circle1 = new Circle(radius);
		
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<Circle>> constraintViolations = validator.validate(circle1, javax.validation.groups.Default.class);
		
		// Output the area and perimeter of the circle directly to the HTTP response
		PrintWriter out = response.getWriter();
		if (constraintViolations.size() > 0) {
			out.println("<p>Please fix the following error: " + constraintViolations.iterator().next().getMessage() + "</p>");	
		} else {
			out.println("<p>The area is <strong>" + circle1.area() + "</strong> for a radius of " + radius + "</p>");
			out.println("<p>The perimeter is <strong>" + circle1.circumference() + "</strong> for a radius of " + radius + "</p>");
		}
		out.close();			
		
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
