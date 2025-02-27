package com.springdemo.testverifica.service;

import com.springdemo.testverifica.model.*;
import com.springdemo.testverifica.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public void aggiungiCliente(Cliente cliente) {
        clienteRepository.save(cliente);
    }
    public Cliente getClienteByUsernameAndPassword(String username, String password) {
        return clienteRepository.findByUsernameAndPassword(username, password);
    }

    /**
     * Restiuisce l'elenco completo degli Clienti
     * @return
     */
    public Iterable<Cliente> getClienti() {
        return clienteRepository.findAll();

    }

    public Cliente getClienteByUsername(String username) {
        return clienteRepository.findByUsername(username);
    }

    public int aggiornaProfilo(Cliente cliente,
                               String username,
                               String nome,
                               String cognome,
                               String citta,
                               String telefono,
                               String password, String vecchiaPassword) {

        // Trovo l'Cliente loggato con username passato:
        Cliente clienteLoggato = clienteRepository.findByUsername(username);
        if (clienteLoggato == null) {
            return -1;
        }

        // Modifico l'Cliente loggato:
        clienteLoggato.setNome(nome);
        clienteLoggato.setCognome(cognome);
        clienteLoggato.setTelefono(telefono);
        clienteLoggato.setCitta(citta);
        //ClienteLoggato.setEmail(email);
        //ClienteLoggato.setDataNascita(dataNascita);

        // Controllo se c'è una nuova password da aggiornare:
        if (password != null && !password.isEmpty()) {
            // Controllo se la vecchia password è corretta:
            if (!clienteLoggato.getPassword().equals(vecchiaPassword)) {
                return -2; // Se la vecchiaPassword è sbagliata
            }
            clienteLoggato.setPassword(password);
        }
        // Aggiorno l'Cliente:
        clienteRepository.save(clienteLoggato);
        return 0;
    }
}
