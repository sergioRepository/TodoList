package cp.campus.todolist.controller;

import cp.campus.todolist.dto.CategoriaRequest;
import cp.campus.todolist.dto.CategoriaResponse;
import cp.campus.todolist.dto.PedidoRequest;
import cp.campus.todolist.dto.PedidoResponse;
import cp.campus.todolist.service.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

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
}
