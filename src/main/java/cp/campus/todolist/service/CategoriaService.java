package cp.campus.todolist.service;

import cp.campus.todolist.dto.CategoriaRequest;
import cp.campus.todolist.dto.CategoriaResponse;
import cp.campus.todolist.entity.Categoria;
import cp.campus.todolist.repository.CategoriaRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Service
@Validated
@Transactional(readOnly = true)
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Transactional
    public CategoriaResponse crear(@NotNull @Valid CategoriaRequest request){
        Categoria categoria = new Categoria(request.nombre().strip());

        Categoria guardada = categoriaRepository.save(categoria);

        return toResponse(guardada);
    }

    public List<CategoriaResponse> listar(){
        return categoriaRepository
                .findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public Optional<CategoriaResponse> buscarPorId(@NotNull @Positive Long id){
        return categoriaRepository.findById(id).map(this::toResponse);
    }

    private CategoriaResponse toResponse(Categoria categoria){
        return new CategoriaResponse(
                categoria.getId(),
                categoria.getNombre()
        );
    }
}
