package cp.campus.todolist.dto;

import java.math.BigDecimal;

public record PedidoResponse(
        Long id,
        String cliente,
        BigDecimal subtotal,
        BigDecimal descuento,
        BigDecimal total
) {
}
