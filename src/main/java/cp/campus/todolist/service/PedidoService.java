package cp.campus.todolist.service;

import cp.campus.todolist.dto.DetallePedidoRequest;
import cp.campus.todolist.dto.DetallePedidoResponse;
import cp.campus.todolist.dto.PedidoRequest;
import cp.campus.todolist.dto.PedidoResponse;
import cp.campus.todolist.entity.DetallePedido;
import cp.campus.todolist.entity.Pedido;
import cp.campus.todolist.entity.Producto;
import cp.campus.todolist.repository.PedidoRepository;
import cp.campus.todolist.repository.ProductoRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Validated
@Transactional(readOnly = true)
public class PedidoService {
    private static final BigDecimal TASA_DESCUENTO_VIP = new BigDecimal("0.10");

    private final PedidoRepository pedidoRepository;
    private final ProductoRepository productoRepository;

    public PedidoService(PedidoRepository pedidoRepository, ProductoRepository productoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.productoRepository = productoRepository;
    }

    @Transactional
    public PedidoResponse crear(@NotNull @Valid PedidoRequest request){
        Pedido pedido = new Pedido(request.cliente().strip());

        Set<Long> productosIncluidos = new HashSet<>();

        for (DetallePedidoRequest detalle : request.detalles()){
            if(!productosIncluidos.add(detalle.productoId())){
                throw new IllegalArgumentException("El producto está repetido: "
                        + detalle.productoId());
            }

            Producto producto = productoRepository.findById(detalle.productoId())
                    .orElseThrow(()-> new IllegalArgumentException("No existe el producto con el ID"
                            +detalle.productoId()));

            pedido.agregarDetalle(
                    producto,
                    detalle.cantidad()
            );
        }

        BigDecimal tasa = request.clienteVip() ? TASA_DESCUENTO_VIP : BigDecimal.ZERO;

        pedido.calcularTotales(tasa);

        Pedido guardado = pedidoRepository.save(pedido);

        return toResponse(guardado);
    }

    private PedidoResponse toResponse(Pedido pedido){
        List<DetallePedidoResponse> detalles = pedido.getDetalles()
                .stream()
                .map(this::toDetalleResponse)
                .toList();

        return new PedidoResponse(
                pedido.getId(),
                pedido.getCliente(),
                pedido.getSubtotal(),
                pedido.getDescuento(),
                pedido.getTotal(),
                detalles
        );
    }

    private DetallePedidoResponse toDetalleResponse(DetallePedido detallePedido){
        return new DetallePedidoResponse(
                detallePedido.getId(),
                detallePedido.getProducto().getId(),
                detallePedido.getProducto().getNombre(),
                detallePedido.getCantidad(),
                detallePedido.getPrecioUnitario(),
                detallePedido.calcularImporte()
        );
    }
}
