package cp.campus.todolist.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "detalles_pedido")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DetallePedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal precioUnitario;

    DetallePedido(Pedido pedido, Producto producto, Integer cantidad) {
        if(pedido == null || producto == null){
            throw new IllegalArgumentException("El pedido y producto son obligatorios");
        }

        if(cantidad<=0){
            throw new IllegalArgumentException("La cantidad debe ser mayor a cero");
        }

        this.pedido = pedido;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = producto.getPrecio();
    }

    public BigDecimal calcularImporte(){
        return  precioUnitario.multiply(BigDecimal.valueOf(cantidad));
    }
}
