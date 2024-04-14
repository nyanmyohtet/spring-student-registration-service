package com.nyanmyohtet.studentregistrationservice.persistence.repository;

import com.nyanmyohtet.studentregistrationservice.persistence.model.ScheduledJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduledJobRepository extends JpaRepository<ScheduledJob, Long> {

    List<ScheduledJob> findByEnabled(Boolean enabled);

}
