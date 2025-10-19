package dev.audrey.apinaruto.model.dto;

import jakarta.validation.constraints.NotBlank;

public record NinjaDTO(
        @NotBlank String nome,
        String aldeia
){}

