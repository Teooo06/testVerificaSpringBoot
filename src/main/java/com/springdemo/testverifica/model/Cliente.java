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
@Table (name="clienti")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
   @Id
   private String username;
   private String password;
   private String cognome;
   private String nome;
   private String citta;
   private String telefono;
}
