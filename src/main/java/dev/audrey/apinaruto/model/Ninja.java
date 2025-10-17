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
    @Column(length = 1000) // Aumentado para 1000 caracteres para acomodar URLs mais longas
    private String imgUrl;

}
