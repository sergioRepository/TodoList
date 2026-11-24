package cp.campus.todolist.repository;

import cp.campus.todolist.entity.Producto;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductoRepository extends JpaRepository<Producto, Long> {



    @EntityGraph(attributePaths = "categoria")
    List<Producto> findByCategoriaId(Long categoriaId);

    @EntityGraph(attributePaths = "categoria")
    Optional<Producto> findByNombre(String nombre);
}
