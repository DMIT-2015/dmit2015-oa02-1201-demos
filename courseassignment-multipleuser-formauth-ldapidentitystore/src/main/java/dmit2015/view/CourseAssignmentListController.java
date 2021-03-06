package dmit2015.view;

import common.interceptor.LoggingInterceptor;
import dmit2015.model.CourseAssignment;
import dmit2015.service.CourseAssignmentRepository;
import org.omnifaces.util.Messages;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.interceptor.Interceptors;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

@Named("currentCourseAssignmentListController")
@ViewScoped
@Interceptors(LoggingInterceptor.class)
public class CourseAssignmentListController implements Serializable {

//    @Inject
//    private Logger logger;

    @Inject
    private CourseAssignmentRepository assignmentRepository;

    private List<CourseAssignment> assignments;

    @PostConstruct  // After @Inject is complete
    public void init() {
        // Fetch the list of assignments only once
        try {
            assignments = assignmentRepository.list();
        } catch (RuntimeException ex) {
            Messages.addGlobalError(ex.getMessage());
        }
    }

    public List<CourseAssignment> list() {
        return assignments;
    }

}
