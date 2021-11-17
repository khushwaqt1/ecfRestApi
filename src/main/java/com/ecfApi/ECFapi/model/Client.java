package com.ecfApi.ECFapi.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthdate;

    public Client() {
    }





// todo just for testing i add below contructor
    public Client(String firstName, String lastName, LocalDate of) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
