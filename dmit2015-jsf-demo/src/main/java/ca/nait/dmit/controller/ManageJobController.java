package ca.nait.dmit.controller;

import ca.nait.dmit.model.Job;
import ca.nait.dmit.service.JobService;
import lombok.Getter;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class ManageJobController implements Serializable {

    @Getter
    private Job currentJob = new Job();

    @Inject
    private JobService currentJobService;

    public String addJob() {
        // Add the currentJob to the system
        currentJobService.add(currentJob);
        // Create another Job to add
        currentJob = new Job();
        return "";
    }

    public List<Job> getJobs() {
        return currentJobService.getJobs();
    }
}
