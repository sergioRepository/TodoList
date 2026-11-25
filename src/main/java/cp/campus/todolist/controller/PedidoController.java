package cp.campus.todolist.controller;

import cp.campus.todolist.dto.CategoriaRequest;
import cp.campus.todolist.dto.CategoriaResponse;
import cp.campus.todolist.dto.PedidoRequest;
import cp.campus.todolist.dto.PedidoResponse;
import cp.campus.todolist.service.PedidoService;
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
@RequestMapping("/api/pedidos")
@Tag(name = "Pedidos", description = "Creacion y consulta de pedidos")
public class PedidoController {
    private PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    @Operation(summary = "Crear pedido")
    public ResponseEntity<PedidoResponse> crear(@Valid @RequestBody PedidoRequest request){
        PedidoResponse resultado = pedidoService.crear(request);

        URI ubicacion = ServletUriComponentsBuilder
                .fromCurrentRequest() //URL de partida
                .path("/{id}") //añade "/{id}" a la ruta
                .buildAndExpand(resultado.id()) //Reemplaza "/{id}" por el id real
                .toUri(); //Construye un objeto URI

        return ResponseEntity.created(ubicacion).body(resultado);
    }

    @GetMapping
    @Operation(summary = "Listar pedidos")
    public ResponseEntity<List<PedidoResponse>> listar() {
        return ResponseEntity.ok(pedidoService.listar());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar pedido por ID")
    public ResponseEntity<PedidoResponse> buscarPorId(@PathVariable @Positive Long id) {
        return pedidoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
