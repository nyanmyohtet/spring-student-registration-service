package com.nyanmyohtet.studentregistrationservice.persistence.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "scheduled_jobs", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class ScheduledJob {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "job_name")
    private String jobName;

    @Column(name = "cron_expression")
    private String cronExpression;

    private boolean enabled;
}
