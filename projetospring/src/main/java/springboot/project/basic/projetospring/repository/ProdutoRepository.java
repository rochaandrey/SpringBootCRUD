package springboot.project.basic.projetospring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springboot.project.basic.projetospring.models.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
