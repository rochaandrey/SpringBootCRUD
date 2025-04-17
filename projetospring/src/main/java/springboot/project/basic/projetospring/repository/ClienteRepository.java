package springboot.project.basic.projetospring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import springboot.project.basic.projetospring.models.Cliente;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Query(value = "SELECT * FROM cliente WHERE email = :email", nativeQuery = true)
    Optional<Cliente> findByEmail (String email);
}
