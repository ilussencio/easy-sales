package br.edu.iftm.api.easysales.services;

import br.edu.iftm.api.easysales.DTO.ClienteDTO;
import br.edu.iftm.api.easysales.controllers.ClienteController;
import br.edu.iftm.api.easysales.exceptions.RequiredObjectIsNullException;
import br.edu.iftm.api.easysales.exceptions.ResourceNotFoundException;
import br.edu.iftm.api.easysales.mapper.DozerMapper;
import br.edu.iftm.api.easysales.models.Cliente;
import br.edu.iftm.api.easysales.repositories.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository repository;

    public List<ClienteDTO> findAll(){
        List<Cliente> clientesDbList = repository.findAll();
        var clientes = DozerMapper.parseListObject(clientesDbList, ClienteDTO.class);
        clientes.stream().forEach(cliente -> {
            try {
                cliente.add(linkTo(methodOn(ClienteController.class).findById(cliente.getIdCliente())).withSelfRel());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        return clientes;
    }

    public ClienteDTO findById(Long id) throws Exception{
        Cliente clienteDb = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("cliente não encontrada"));
        var cliente = DozerMapper.parseObject(clienteDb, ClienteDTO.class);
        cliente.add(linkTo(methodOn(ClienteController.class).findAll()).withRel("Lista de clientes"));
        return cliente;
    }

    public ClienteDTO save(ClienteDTO clienteDTO) throws Exception {
        if(clienteDTO == null) throw new RequiredObjectIsNullException("Objeto ClienteDTO está nulo");
        Cliente cliente = DozerMapper.parseObject(clienteDTO, Cliente.class);
        var clienteDb = repository.save(cliente);
        clienteDTO = DozerMapper.parseObject(clienteDb, ClienteDTO.class);
        clienteDTO.add(linkTo(methodOn(ClienteController.class).findById(clienteDTO.getIdCliente())).withSelfRel());
        return clienteDTO;
    }

    public ClienteDTO update(ClienteDTO clienteDTO) throws Exception {
        if(clienteDTO == null) throw new RequiredObjectIsNullException("Objeto ClienteDTO está nulo");
        var clienteDb = repository.findById(clienteDTO.getIdCliente()).orElseThrow(() -> new ResourceNotFoundException("cliente não encontrada"));
        Cliente cliente = DozerMapper.parseObject(clienteDTO, Cliente.class);
        var Db = repository.save(cliente);
        clienteDTO = DozerMapper.parseObject(Db, ClienteDTO.class);
        clienteDTO.add(linkTo(methodOn(ClienteController.class).findById(clienteDTO.getIdCliente())).withSelfRel());
        return clienteDTO;
    }

    public void delete(Long id) throws Exception {
        Cliente clienteDb = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("cliente não encontrada"));
        repository.delete(clienteDb);
    }
}
