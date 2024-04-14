package com.nyanmyohtet.studentregistrationservice.scheduler;

import com.nyanmyohtet.studentregistrationservice.persistence.repository.ScheduledJobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Service
public class DynamicSchedulerService {

    @Autowired
    private ScheduledJobRepository scheduledJobRepository;

    @Autowired
    private TaskScheduler taskScheduler;

    @PostConstruct
    public void scheduleJobs() {
        Optional.ofNullable(scheduledJobRepository.findByEnabled(true))
            .filter(jobs -> !jobs.isEmpty())
            .ifPresentOrElse(
                jobs -> {
                    System.out.println(">>> Number of enabled jobs found: " + jobs.size());
                    jobs.forEach(job -> taskScheduler.schedule(new RunnableTask(job.getJobName()), new CronTrigger(job.getCronExpression())));
                },
                () -> System.out.println(">>> No enabled jobs found to schedule.")
            );
    }
}
