package cp.campus.todolist.controller;

import cp.campus.todolist.dto.CategoriaResponse;
import cp.campus.todolist.dto.ProductoRequest;
import cp.campus.todolist.dto.ProductoResponse;
import cp.campus.todolist.service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/productos")
@Tag(name = "Productos", description = "Creacion y consulta de productos")
public class ProductoController {
    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @PostMapping
    @Operation(summary = "Crear Productos")
    public ResponseEntity<ProductoResponse> crear(@Valid @RequestBody ProductoRequest request){
        ProductoResponse resultado = productoService.crear(request);

        URI ubicacion = ServletUriComponentsBuilder
                .fromCurrentRequest() //URL de partida
                .path("/{id}") //añade "/{id}" a la ruta
                .buildAndExpand(resultado.id()) //Reemplaza "/{id}" por el id real
                .toUri(); //Construye un objeto URI

        return ResponseEntity.created(ubicacion).body(resultado);
    }

    @GetMapping
    @Operation(summary = "Listar Productos")
    public ResponseEntity<List<ProductoResponse>> listar(){
        return ResponseEntity.ok(productoService.listar());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar Productos Por ID")
    public ResponseEntity<ProductoResponse> buscarPorId(@PathVariable @Positive Long id){
        return productoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @GetMapping("/categoria/{categoriaId}")
    public ResponseEntity<List<ProductoResponse>> listarPorCategoria(@PathVariable("categoriaId") @Positive
                                                                         Long categoriaId){
        return ResponseEntity.ok(productoService.listarPorCategoria(categoriaId));
    }
}
