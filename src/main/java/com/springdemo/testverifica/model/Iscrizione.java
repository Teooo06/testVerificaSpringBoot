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
@Table (name="iscrizioni")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Iscrizione{
    @Id

    private int idIscrizione;
    private String idCorso;
    private String username;

}
