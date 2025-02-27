package com.springdemo.testverifica.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Table (name="corsi")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Corso {
    @Id
    private String idCorso;
    private String descrizione;
    private String giornoSettimana;
    private String ora;
    private double costo;

    public String getPrezzoStr(){
        return String.format("%.2f", costo);
    }
}
