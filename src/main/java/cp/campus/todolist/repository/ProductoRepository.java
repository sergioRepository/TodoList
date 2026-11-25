package cp.campus.todolist.repository;

import cp.campus.todolist.entity.Producto;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    @Override
    @EntityGraph(attributePaths = "categoria")
    List<Producto> findAll();

    @Override
    @EntityGraph(attributePaths = "categoria")
    Optional<Producto> findById(Long id);

    @EntityGraph(attributePaths = "categoria")
    List<Producto> findByCategoria_Id(Long CategoriaId);

    @EntityGraph(attributePaths = "categoria")
    Optional<Producto> findByNombre(String nombre);
}
