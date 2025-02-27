package com.springdemo.testverifica.service;
import com.springdemo.testverifica.model.*;
import com.springdemo.testverifica.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class IscrizioneService {
    private final com.springdemo.testverifica.repository.IscrizioneRepository iscrizioneRepository;
    private final com.springdemo.testverifica.repository.CorsoRepository corsoRepository;
    private final com.springdemo.testverifica.repository.ClienteRepository clienteRepository;

    public Iterable<Iscrizione> getIscrizioni() {
        return iscrizioneRepository.findAll();
    }

    public String addIscrizione(String username, String idCorso) {
        // Recupera il prodotto dal database
        Corso corso = corsoRepository.findById(idCorso).orElse(null);
        if (corso == null) {
            return "Errore nel recupero del prodotto.";
        }

        // Crea un nuova iscrizione
        Iscrizione iscrizione = new Iscrizione();
        iscrizione.setUsername(clienteRepository.findByUsername(username).getUsername());
        iscrizione.setIdCorso(corso.getIdCorso());

        // Salva l'iscrizione
        iscrizioneRepository.save(iscrizione);

        return corso.getDescrizione() + " aggiunto!";
    }
}
