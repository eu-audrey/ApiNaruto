package dev.audrey.apinaruto.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "ninjas")
@Data
public class Ninja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String aldeia;
    private String elemento;
    private String imgUrl;

}
