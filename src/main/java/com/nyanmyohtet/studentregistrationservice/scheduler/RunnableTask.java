package com.nyanmyohtet.studentregistrationservice.scheduler;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RunnableTask implements Runnable {
    private String jobName;

    @Override
    public void run() {
        // Implement the task logic here
        System.out.println(">>> Executing job: " + jobName);
    }
}
