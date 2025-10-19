package dev.audrey.apinaruto.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "ninjas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Ninja {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ninja_id_bananinha", nullable = false, updatable = false)
    @EqualsAndHashCode.Include
    private UUID ninjaId;

    @NotBlank(message = "Nome é obrigatório")
    @Column(name = "nome_ninja")
    private String nome;

    private String aldeia;

    private Integer idade;

//    @Column(length = 1000) // Aumentado para 1000 caracteres para acomodar URLs mais longas
//    private String imgUrl;

}
