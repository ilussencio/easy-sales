package br.edu.iftm.api.easysales.services;

import br.edu.iftm.api.easysales.DTO.VendaDTO;
import br.edu.iftm.api.easysales.controllers.ProdutoController;
import br.edu.iftm.api.easysales.controllers.VendaController;
import br.edu.iftm.api.easysales.exceptions.RequiredObjectIsNullException;
import br.edu.iftm.api.easysales.exceptions.ResourceNotFoundException;
import br.edu.iftm.api.easysales.mapper.DozerMapper;
import br.edu.iftm.api.easysales.models.Venda;
import br.edu.iftm.api.easysales.repositories.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class VendaService {
    @Autowired
    private VendaRepository repository;

    public List<VendaDTO> findAll(){
        List<Venda> vendaDbList = repository.findAll();
        var vendas = DozerMapper.parseListObject(vendaDbList, VendaDTO.class);
        vendas.stream().forEach( venda ->{
            try {
                venda.add(linkTo(methodOn(VendaController.class).findById(venda.getIdVenda())).withSelfRel());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        return vendas;
    }

    public VendaDTO findById(Long id) throws Exception{
        Venda vendaDbList = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Venda não encontrada"));
        var venda = DozerMapper.parseObject(vendaDbList, VendaDTO.class);
        venda.add(linkTo(methodOn(VendaController.class).findById(id)).withSelfRel());
        return venda;
    }

    public VendaDTO save(VendaDTO vendaDTO) throws Exception {
        if(vendaDTO == null) throw new RequiredObjectIsNullException("Objeto VendaDTO está nulo");
        Venda venda = DozerMapper.parseObject(vendaDTO, Venda.class);
        var produtoDb = repository.save(venda);
        vendaDTO = DozerMapper.parseObject(produtoDb, VendaDTO.class);
        vendaDTO.add(linkTo(methodOn(VendaController.class).findById(vendaDTO.getIdVenda())).withSelfRel());
        return vendaDTO;
    }

    public VendaDTO update(VendaDTO vendaDTO) throws Exception {
        if(vendaDTO == null) throw new RequiredObjectIsNullException("Objeto VendaDTO está nulo");
        var vendaDb = repository.findById(vendaDTO.getIdVenda()).orElseThrow(() -> new ResourceNotFoundException("Venda não encontrada"));
        Venda venda = DozerMapper.parseObject(vendaDTO, Venda.class);
        var Db = repository.save(venda);
        vendaDTO = DozerMapper.parseObject(Db, VendaDTO.class);
        vendaDTO.add(linkTo(methodOn(VendaController.class).findById(vendaDTO.getIdVenda())).withSelfRel());
        return vendaDTO;
    }

    public void delete(Long id) throws Exception {
        Venda vendaDb = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Venda não encontrada"));
        repository.delete(vendaDb);
    }
}
