package br.com.lowlevel.revisaospringsecurity.domain.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CategoryRequestDTO(
        @NotBlank
        String name,

        @NotNull
        Integer price
) {
}