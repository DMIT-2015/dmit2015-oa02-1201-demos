package dmit2015.view;

import common.interceptor.LoggingInterceptor;
import dmit2015.model.CourseAssignment;
import dmit2015.service.CourseAssignmentRepository;
import lombok.Getter;
import org.omnifaces.util.Messages;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.interceptor.Interceptors;
import java.util.logging.Logger;

@Named("currentCourseAssignmentCreateController")
@RequestScoped
@Interceptors(LoggingInterceptor.class)
public class CourseAssignmentCreateController {

    @Inject
    private Logger logger;

    @Inject
    private CourseAssignmentRepository assignmentRepository;

    @Getter
    private CourseAssignment newCourseAssignment = new CourseAssignment();

    public String onCreateNew() {
        String nextPage = "";
        try {
            assignmentRepository.add(newCourseAssignment);
            Messages.addFlashGlobalInfo("Create completed successfully.");
            nextPage = "index?faces-redirect=true";
        } catch (Exception e) {
            e.printStackTrace();
            Messages.addGlobalError("Create completed with the error: {0}", e.getMessage());
        }
        return nextPage;
    }

}
