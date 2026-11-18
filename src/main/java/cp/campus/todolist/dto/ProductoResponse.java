package cp.campus.todolist.dto;

import java.math.BigDecimal;

public record ProductoResponse(
       Long id,
       String nombre,
       BigDecimal precio,
       Long categoriaId,
       String nombreCategoria
) {
}
