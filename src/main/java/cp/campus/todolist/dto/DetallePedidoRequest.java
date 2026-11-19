package cp.campus.todolist.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record DetallePedidoRequest(
        @NotNull
        @Positive
        Long productoId,
        @NotNull
        @Positive
        @Max(10000)
        Integer cantidad








) {


}
