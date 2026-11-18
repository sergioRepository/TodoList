package cp.campus.todolist.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Entity
@Table(name = "producto")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String nombre;

    @Column(nullable = false, precision = 19,scale =2)
    private BigDecimal precio;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "categoria_id",nullable = false)
    private Categorias categoria;


    public Producto(String nombre, BigDecimal precio, Categorias categoria) {
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
    }


}
