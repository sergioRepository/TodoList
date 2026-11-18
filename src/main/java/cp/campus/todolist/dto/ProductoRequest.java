package cp.campus.todolist.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record  ProductoRequest(
        @NotBlank
        @Size(max = 150)
        String nombre,

        @NotNull
        @DecimalMin("0.01")
        @Digits(integer = 17,fraction = 2)
        BigDecimal precio,


        @NotNull
        @Positive
        Long categoriaId
) {
}
