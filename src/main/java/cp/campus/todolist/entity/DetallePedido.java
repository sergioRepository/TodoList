package cp.campus.todolist.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "detalle_pedido")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class DetallePedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "producto_id")
    private Producto producto;


    @Column(nullable = false,precision = 19,scale=2)
    private BigDecimal precioUnitario;




}
