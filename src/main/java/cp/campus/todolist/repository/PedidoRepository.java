package cp.campus.todolist.repository;

import cp.campus.todolist.entity.Pedido;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    @Override
    @EntityGraph(attributePaths = {"detalles"})
    Optional<Pedido> findById(Long id);


    @Override
    @EntityGraph(attributePaths = {"detalles"})
    List<Pedido>findAll();



}