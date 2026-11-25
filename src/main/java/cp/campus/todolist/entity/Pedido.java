package cp.campus.todolist.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "pedidos")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String cliente;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal subtotal;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal descuento;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal total;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetallePedido> detalles = new ArrayList<>();

    public Pedido(String cliente) {
        this.cliente = cliente;
        this.subtotal = new BigDecimal("0.00");
        this.descuento = new BigDecimal("0.00");
        this.total = new BigDecimal("0.00");
    }

    public List<DetallePedido> getDetalles(){
        return Collections.unmodifiableList(detalles);
    }

    public void agregarDetalle(Producto producto, int cantidad){
        DetallePedido detalle = new DetallePedido(this, producto, cantidad);

        detalles.add(detalle);
    }

    public void calcularTotales(BigDecimal tasaDescuento){
        if(detalles.isEmpty()){
            throw new IllegalStateException("El pedido debe tener al menos un detalle");
        }

        if(tasaDescuento == null ||
                tasaDescuento.signum()<0 ||
                tasaDescuento.compareTo(BigDecimal.ONE)>0){
            throw new IllegalArgumentException("La tasa debe estar entre 0 y 1");
        }

        subtotal=detalles.stream()
                .map(DetallePedido::calcularImporte)
                .reduce(new BigDecimal("0.00"),BigDecimal::add)
                .setScale(2, RoundingMode.UNNECESSARY);

        descuento = subtotal.multiply(tasaDescuento).setScale(2,RoundingMode.HALF_UP);

        total=subtotal.subtract(descuento);
    }
}
