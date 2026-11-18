package cp.campus.todolist.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Entity
@Table(name = "pedidos")
//@AllArgsConstructor // Todos los elementos del constructor
@NoArgsConstructor // ningun elemento del constructor
@Getter

public class Pedido {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, length = 150)
    private String cliente;

    @Column(nullable = false, precision = 19,scale =2)
    private BigDecimal subtotal;

    @Column(nullable = false, precision = 19,scale =2)
    private BigDecimal descuento;

    @Column(nullable = false, precision = 19,scale =2)
    private BigDecimal total;

    public Pedido(String cliente, BigDecimal subtotal, BigDecimal descuento, BigDecimal total) {
        this.cliente = cliente;
        this.subtotal = subtotal;
        this.descuento = descuento;
        this.total = total;
    }
}


