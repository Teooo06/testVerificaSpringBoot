package com.springdemo.testverifica.service;

import com.springdemo.testverifica.model.*;
import com.springdemo.testverifica.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ClienteService {
    private final ClienteRepository ClienteRepository;

    public void aggiungiCliente(Cliente Cliente) {
        ClienteRepository.save(Cliente);
    }
    public Cliente getClienteByUsernameAndPassword(String username, String password) {
        return ClienteRepository.findByUsernameAndPassword(username, password);
    }

    /**
     * Restiuisce l'elenco completo degli Clienti
     * @return
     */
    public Iterable<Cliente> getClienti() {
        return ClienteRepository.findAll();

    }

    public Cliente getClienteByUsername(String username) {
        return ClienteRepository.findByUsername(username);
    }

    public int aggiornaProfilo(Cliente Cliente,
                               String username,
                               String nome,
                               String cognome,
                               String citta,
                               String telefono,
                               String password, String vecchiaPassword) {

        // Trovo l'Cliente loggato con username passato:
        Cliente ClienteLoggato = ClienteRepository.findByUsername(username);
        if (ClienteLoggato == null) {
            return -1;
        }

        // Modifico l'Cliente loggato:
        ClienteLoggato.setNome(nome);
        ClienteLoggato.setCognome(cognome);
        ClienteLoggato.setTelefono(telefono);
        ClienteLoggato.setCitta(citta);
        //ClienteLoggato.setEmail(email);
        //ClienteLoggato.setDataNascita(dataNascita);

        // Controllo se c'è una nuova password da aggiornare:
        if (password != null && !password.isEmpty()) {
            // Controllo se la vecchia password è corretta:
            if (!ClienteLoggato.getPassword().equals(vecchiaPassword)) {
                return -2; // Se la vecchiaPassword è sbagliata
            }
            ClienteLoggato.setPassword(password);
        }
        // Aggiorno l'Cliente:
        ClienteRepository.save(ClienteLoggato);
        return 0;
    }
}
