package com.nyanmyohtet.studentregistrationservice.persistence.repository;

import com.nyanmyohtet.studentregistrationservice.persistence.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

}
