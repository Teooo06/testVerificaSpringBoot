package com.springdemo.testverifica.service;

import com.springdemo.testverifica.model.*;
import com.springdemo.testverifica.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CorsoService {
    private final com.springdemo.testverifica.repository.CorsoRepository corsoRepository;

    public Iterable<Corso> getCorsi() {
        return corsoRepository.findAll();
    }


}