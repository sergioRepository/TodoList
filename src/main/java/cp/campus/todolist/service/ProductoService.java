package cp.campus.todolist.service;

import cp.campus.todolist.dto.CategoriaResponse;
import cp.campus.todolist.dto.ProductoRequest;
import cp.campus.todolist.dto.ProductoResponse;
import cp.campus.todolist.entity.Categoria;
import cp.campus.todolist.entity.Producto;
import cp.campus.todolist.repository.CategoriaRepository;
import cp.campus.todolist.repository.ProductoRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

@Service
@Validated
@Transactional(readOnly = true)
public class ProductoService {
    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;

    public ProductoService(ProductoRepository productoRepository, CategoriaRepository categoriaRepository) {
        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @Transactional
    public ProductoResponse crear(@NotNull @Valid ProductoRequest request) {
        Categoria categoria = categoriaRepository
                .findById(request.categoriaId())
                .orElseThrow(() -> new IllegalArgumentException("No existe la categoria con el ID "
                        + request.categoriaId()));

        BigDecimal precio = request.precio().setScale(2, RoundingMode.UNNECESSARY);

        Producto producto = new Producto(
                request.nombre().strip(),
                precio,
                categoria
        );

        Producto guardado = productoRepository.save(producto);

        return toResponse(guardado);
    }

    public List<ProductoResponse> listar() {
        return productoRepository
                .findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public Optional<ProductoResponse> buscarPorId(@NotNull @Positive Long id) {
        return productoRepository.findById(id).map(this::toResponse);
    }

    public List<ProductoResponse> listarPorCategoria(@NotNull @Positive Long categoriaId){
        return productoRepository
                .findByCategoria_Id(categoriaId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private ProductoResponse toResponse(Producto producto){

        return new ProductoResponse(
                producto.getId(),
                producto.getNombre(),
                producto.getPrecio(),
                producto.getCategoria().getId(),
                producto.getCategoria().getNombre()
        );
    }
}
