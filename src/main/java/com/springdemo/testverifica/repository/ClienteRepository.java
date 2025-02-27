package com.springdemo.testverifica.repository;

import com.springdemo.testverifica.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, String> {
    boolean existsByUsername(String username);
    Cliente findByUsernameAndPassword(String username, String password);

    Cliente findByUsername(String username);

}
