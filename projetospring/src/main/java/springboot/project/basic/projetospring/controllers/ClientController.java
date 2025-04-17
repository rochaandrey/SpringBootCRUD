package springboot.project.basic.projetospring.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springboot.project.basic.projetospring.dtos.RequestClientDTO;
import springboot.project.basic.projetospring.models.Cliente;
import springboot.project.basic.projetospring.repository.ClienteRepository;
import springboot.project.basic.projetospring.repository.ProdutoRepository;

import java.util.List;

@RestController
@RequestMapping("/map")
public class ClientController {
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping("/getall")
    public ResponseEntity getAllClients(){
        List<Cliente> clientes = clienteRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity getClient(@PathVariable Long id){
        Cliente cliente = clienteRepository.findById(id).orElseThrow(()->new RuntimeException("cliente não encontrado"));
        return ResponseEntity.status(HttpStatus.OK).body(cliente);
    }

    @GetMapping("/{email}")
    public ResponseEntity getClient(@PathVariable String email){
        Cliente cliente = clienteRepository.findByEmail(email).orElseThrow(()->new RuntimeException("cliente não encontrado"));
        return ResponseEntity.status(HttpStatus.OK).body(cliente);
    }

    @PostMapping
    public ResponseEntity createClient(@RequestBody RequestClientDTO requestClientDTO){
        Cliente cliente = new Cliente();
        BeanUtils.copyProperties(requestClientDTO, cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteRepository.save(cliente));
    }
}
