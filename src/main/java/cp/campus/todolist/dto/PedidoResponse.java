package cp.campus.todolist.dto;

import java.math.BigDecimal;
import java.util.List;

public record PedidoResponse(
        Long id,
        String cliente,
        BigDecimal subtotal,
        BigDecimal descuento,
        BigDecimal total,
        List<DetallePedidoResponse> detalles
) {
}
