package com.springdemo.testverifica.repository;

import com.springdemo.testverifica.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CorsoRepository extends JpaRepository<Corso, String>{
}