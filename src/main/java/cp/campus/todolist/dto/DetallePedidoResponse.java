package cp.campus.todolist.dto;

import java.math.BigDecimal;

public record DetallePedidoResponse(
        Long id,
        Long productoId,
        String productoNombre,
        Integer cantidad,
        BigDecimal precioUnitario,
        BigDecimal importe
) {
}
