package cp.campus.todolist.repository;

import cp.campus.todolist.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    /*
    save(entity)
    findById(id)
    findAll()
    findAll(Pageable)
     */


}
