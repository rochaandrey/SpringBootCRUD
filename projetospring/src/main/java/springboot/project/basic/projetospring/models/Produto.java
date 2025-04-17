package springboot.project.basic.projetospring.models;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descricao;
    private BigDecimal preco;

    @OneToMany(mappedBy = "produto")
    private List<ItemPedido> itens = new ArrayList<>();
}
