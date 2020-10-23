package ca.nait.dmit.service;

import ca.nait.dmit.model.Job;
import lombok.Getter;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class JobService {

    @Getter
    private List<Job> jobs = new ArrayList<>();

    public void add(Job newJob) {
        jobs.add(newJob);
    }
}
