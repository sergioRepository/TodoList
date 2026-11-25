package cp.campus.todolist.controller;

import cp.campus.todolist.dto.CategoriaRequest;
import cp.campus.todolist.dto.CategoriaResponse;
import cp.campus.todolist.service.CategoriaService;
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
@RequestMapping("/api/categorias")
@Tag(name = "Categorias", description = "Creacion y consulta de categorias")
public class CategoriaController {
    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @PostMapping
    @Operation(summary = "Crear Categorias")
    public ResponseEntity<CategoriaResponse> crear(@Valid @RequestBody CategoriaRequest request){
        CategoriaResponse resultado = categoriaService.crear(request);

        URI ubicacion = ServletUriComponentsBuilder
                .fromCurrentRequest() //URL de partida
                .path("/{id}") //añade "/{id}" a la ruta
                .buildAndExpand(resultado.id()) //Reemplaza "/{id}" por el id real
                .toUri(); //Construye un objeto URI

        return ResponseEntity.created(ubicacion).body(resultado);
    }

    @GetMapping
    @Operation(summary = "Listar Categorias")
    public ResponseEntity<List<CategoriaResponse>> listar(){
        return ResponseEntity.ok(categoriaService.listar());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar Categorias Por ID")
    public ResponseEntity<CategoriaResponse> buscarPorId(@PathVariable @Positive Long id){
        return categoriaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());
    }
}
