package dev.audrey.apinaruto.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record NinjaRequestDTO(
        @NotBlank(message = "O nome não pode ser vazio.")
        String nome,

        @NotBlank(message = "A aldeia não pode ser vazia.")
        String aldeia,

        @NotNull(message = "A idade é obrigatória.")
        @Min(value = 1, message = "A idade deve ser no mínimo 1.")
        Integer idade
) {}
