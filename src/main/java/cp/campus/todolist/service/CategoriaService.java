package cp.campus.todolist.service;

import cp.campus.todolist.dto.CategoriaRequest;
import cp.campus.todolist.dto.CategoriaResponse;
import cp.campus.todolist.entity.Categorias;
import cp.campus.todolist.repository.CategoriaRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Validated
@Service
@Transactional(readOnly = true)
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }


    public CategoriaResponse crear(@NotNull @Valid CategoriaRequest request){
        Categorias categoria = new Categorias(request.nombre().strip());
        Categorias guardada = categoriaRepository.save(categoria);
        return toResponse(categoria);
    }

    public List<CategoriaResponse> listar(){
        return categoriaRepository.findAll().stream().map(this::toResponse).toList();
    }

    public Optional<CategoriaResponse> buscarPorId(long id) {
        return categoriaRepository.findById(id).map(this::toResponse);
    }

    private CategoriaResponse toResponse(Categorias categoria) {
        return new CategoriaResponse(categoria.getId(), categoria.getNombre());
    }
}
