package springboot.project.basic.projetospring.controllers;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springboot.project.basic.projetospring.dtos.RequestClientDTO;
import springboot.project.basic.projetospring.exception.ResourceNotFoundException;
import springboot.project.basic.projetospring.models.Cliente;
import springboot.project.basic.projetospring.repository.ClienteRepository;
import springboot.project.basic.projetospring.repository.ProdutoRepository;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClientController {
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping
    public ResponseEntity<List<RequestClientDTO>> getAllClients(){
        List<RequestClientDTO> clientes = clienteRepository
                .findAll()
                .stream()
                .map(cliente -> new RequestClientDTO(cliente.getNome(), cliente.getEmail()))
                .toList();
        if (clientes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity getClient(@PathVariable Long id){
        Cliente cliente = clienteRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("O CARA NEM EXISTE KKKKKKKKKK"));
        return ResponseEntity.status(HttpStatus.OK).body(cliente);
    }

    @GetMapping("/por-email/{email}")
    public ResponseEntity getClient(@PathVariable String email){
        Cliente cliente = clienteRepository.findByEmail(email).orElseThrow(()->new ResourceNotFoundException("O CARA NEM EXISTE KKKKKKKKKK"));
        return ResponseEntity.status(HttpStatus.OK).body(cliente);
    }


    @PostMapping
    @Transactional
    public ResponseEntity createClient(@RequestBody @Valid RequestClientDTO requestClientDTO){
        Cliente cliente = new Cliente();
        BeanUtils.copyProperties(requestClientDTO, cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteRepository.save(cliente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("O CARA NEM EXISTE KKKKKKKKKK"));
        clienteRepository.delete(cliente);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity updateClient(@PathVariable (value = "id") Long id, @RequestBody @Valid RequestClientDTO requestClientDTO){
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("O CARA NEM EXISTE KKKKKKKKKK"));

        if (requestClientDTO.nome() != null) {
            cliente.setNome(requestClientDTO.nome());
        }
        if (requestClientDTO.email() != null) {
            cliente.setEmail(requestClientDTO.email());
        }

        clienteRepository.save(cliente);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
