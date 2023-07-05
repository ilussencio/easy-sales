package br.edu.iftm.api.easysales.services;

import br.edu.iftm.api.easysales.DTO.VendedorDTO;
import br.edu.iftm.api.easysales.controllers.VendedorController;
import br.edu.iftm.api.easysales.exceptions.RequiredObjectIsNullException;
import br.edu.iftm.api.easysales.exceptions.ResourceNotFoundException;
import br.edu.iftm.api.easysales.mapper.DozerMapper;
import br.edu.iftm.api.easysales.models.Vendedor;
import br.edu.iftm.api.easysales.models.Vendedor;
import br.edu.iftm.api.easysales.repositories.VendedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class VendedorService {
    @Autowired
    private VendedorRepository repository;

    public List<VendedorDTO> findAll(){
        List<Vendedor> vendedorDbList = repository.findAll();
        var vendas = DozerMapper.parseListObject(vendedorDbList, VendedorDTO.class);
        vendas.stream().forEach( venda ->{
            try {
                venda.add(linkTo(methodOn(VendedorController.class).findById(venda.getIdVendedor())).withSelfRel());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        return vendas;
    }

    public VendedorDTO findById(Long id) throws Exception{
        Vendedor vendedorDbList = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Vendedor não encontrada"));
        var vendedor = DozerMapper.parseObject(vendedorDbList, VendedorDTO.class);
        vendedor.add(linkTo(methodOn(VendedorController.class).findAll()).withRel("Lista de Vendedores"));
        return vendedor;
    }

    public VendedorDTO save(VendedorDTO vendedorDTO) throws Exception {
        if(vendedorDTO == null) throw new RequiredObjectIsNullException("Objeto VendedorDTO está nulo");
        Vendedor vendedor = DozerMapper.parseObject(vendedorDTO, Vendedor.class);
        var vendedorDb = repository.save(vendedor);
        vendedorDTO = DozerMapper.parseObject(vendedorDb, VendedorDTO.class);
        vendedorDTO.add(linkTo(methodOn(VendedorController.class).findById(vendedorDTO.getIdVendedor())).withSelfRel());
        return vendedorDTO;
    }

    public VendedorDTO update(VendedorDTO vendedorDTO) throws Exception {
        if(vendedorDTO == null) throw new RequiredObjectIsNullException("Objeto VendedorDTO está nulo");
        var vendedorDb = repository.findById(vendedorDTO.getIdVendedor()).orElseThrow(() -> new ResourceNotFoundException("Vendedor não encontrada"));
        Vendedor vendedor = DozerMapper.parseObject(vendedorDTO, Vendedor.class);
        var Db = repository.save(vendedor);
        vendedorDTO = DozerMapper.parseObject(Db, VendedorDTO.class);
        vendedorDTO.add(linkTo(methodOn(VendedorController.class).findById(vendedorDTO.getIdVendedor())).withSelfRel());
        return vendedorDTO;
    }

    public void delete(Long id) throws Exception {
        Vendedor vendedorDb = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Vendedor não encontrada"));
        repository.delete(vendedorDb);
    }
}
