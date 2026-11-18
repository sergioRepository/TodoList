package cp.campus.todolist.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record PedidoRequest(
        @NotBlank
        @Size(max = 150)
        String Cliente,

        @NotNull
        @DecimalMin("0.00")
        @Digits(integer = 17, fraction = 2)
        BigDecimal subtotal,

        boolean clienteVip
) {
}
