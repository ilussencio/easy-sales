package br.edu.iftm.api.easysales.services;

import br.edu.iftm.api.easysales.DTO.EnderecoDTO;
import br.edu.iftm.api.easysales.controllers.EnderecoController;
import br.edu.iftm.api.easysales.exceptions.RequiredObjectIsNullException;
import br.edu.iftm.api.easysales.exceptions.ResourceNotFoundException;
import br.edu.iftm.api.easysales.mapper.DozerMapper;
import br.edu.iftm.api.easysales.models.Endereco;
import br.edu.iftm.api.easysales.repositories.EnderecoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class EnderecoService {
    @Autowired
    private EnderecoRepository repository;

    public List<EnderecoDTO> findAll(){
        List<Endereco> clientesDbList = repository.findAll();
        var enderecos = DozerMapper.parseListObject(clientesDbList, EnderecoDTO.class);
        enderecos.stream().forEach(endereco -> {
            try {
                endereco.add(linkTo(methodOn(EnderecoController.class).findById(endereco.getIdEndereco())).withSelfRel());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        return enderecos;
    }

    public EnderecoDTO findById(Long id) throws Exception{
        Endereco enderecoDb = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("endereco não encontrada"));
        var endereco = DozerMapper.parseObject(enderecoDb, EnderecoDTO.class);
        endereco.add(linkTo(methodOn(EnderecoController.class).findById(id)).withSelfRel());
        return endereco;
    }

    public EnderecoDTO save(EnderecoDTO enderecoDTO) throws Exception {
        if(enderecoDTO == null) throw new RequiredObjectIsNullException("Objeto EnderecoDTO está nulo");
        Endereco endereco = DozerMapper.parseObject(enderecoDTO, Endereco.class);
        var enderecoDb = repository.save(endereco);
        enderecoDTO = DozerMapper.parseObject(enderecoDb, EnderecoDTO.class);
        enderecoDTO.add(linkTo(methodOn(EnderecoController.class).findById(enderecoDTO.getIdEndereco())).withSelfRel());
        return enderecoDTO;
    }

    public EnderecoDTO update(EnderecoDTO enderecoDTO) throws Exception {
        if(enderecoDTO == null) throw new RequiredObjectIsNullException("Objeto EnderecoDTO está nulo");
        var enderecoDb = repository.findById(enderecoDTO.getIdEndereco()).orElseThrow(() -> new ResourceNotFoundException("endereco não encontrada"));
        Endereco endereco = DozerMapper.parseObject(enderecoDb, Endereco.class);
        var Db = repository.save(endereco);
        enderecoDTO = DozerMapper.parseObject(Db, EnderecoDTO.class);
        enderecoDTO.add(linkTo(methodOn(EnderecoController.class).findById(enderecoDTO.getIdEndereco())).withSelfRel());
        return enderecoDTO;
    }

    public void delete(Long id) throws Exception {
        Endereco enderecoDb = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("endereco não encontrada"));
        repository.delete(enderecoDb);
    }
}
