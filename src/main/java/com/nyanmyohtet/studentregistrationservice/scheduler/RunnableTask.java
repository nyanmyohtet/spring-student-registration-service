package com.nyanmyohtet.studentregistrationservice.scheduler;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Data
@AllArgsConstructor
public class RunnableTask implements Runnable {

    private String jobName;

    private static final Logger logger = LogManager.getLogger(RunnableTask.class);

    @Override
    public void run() {
        logger.info("Executing job: {}", jobName);
    }
}
