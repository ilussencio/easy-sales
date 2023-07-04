package br.edu.iftm.api.easysales.services;

import br.edu.iftm.api.easysales.DTO.PedidoVendaDTO;
import br.edu.iftm.api.easysales.controllers.PedidoVendaController;
import br.edu.iftm.api.easysales.exceptions.RequiredObjectIsNullException;
import br.edu.iftm.api.easysales.exceptions.ResourceNotFoundException;
import br.edu.iftm.api.easysales.mapper.DozerMapper;
import br.edu.iftm.api.easysales.models.PedidoVenda;
import br.edu.iftm.api.easysales.repositories.PedidoVendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class PedidoVendaService {
    @Autowired
    private PedidoVendaRepository repository;

    public List<PedidoVendaDTO> findAll(){
        List<PedidoVenda> PedidoVendaDbList = repository.findAll();
        var pedidoVendas = DozerMapper.parseListObject(PedidoVendaDbList, PedidoVendaDTO.class);
        pedidoVendas.stream().forEach(pedidoVenda -> {
            try {
                pedidoVenda.add(linkTo(methodOn(PedidoVendaController.class).findById(pedidoVenda.getIdPedido())).withSelfRel());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        return pedidoVendas;
    }

    public PedidoVendaDTO findById(Long id) throws Exception{
        PedidoVenda pedidoVendaDb = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pedido Venda não encontrada"));
        var pedidoVendaDTO = DozerMapper.parseObject(pedidoVendaDb, PedidoVendaDTO.class);
        pedidoVendaDTO.add(linkTo(methodOn(PedidoVendaController.class).findAll()).withRel("Lista de Pedidos Venda"));
        return pedidoVendaDTO;
    }

    public PedidoVendaDTO save(PedidoVendaDTO pedidoVendaDTO) throws Exception {
        if(pedidoVendaDTO == null) throw new RequiredObjectIsNullException("Objeto PedidoVendaDTO está nulo");
        PedidoVenda pedidoVenda = DozerMapper.parseObject(pedidoVendaDTO, PedidoVenda.class);
        var PedidoVendaDb = repository.save(pedidoVenda);
        pedidoVendaDTO = DozerMapper.parseObject(PedidoVendaDb, PedidoVendaDTO.class);
        pedidoVendaDTO.add(linkTo(methodOn(PedidoVendaController.class).findById(pedidoVendaDTO.getIdPedido())).withSelfRel());
        return pedidoVendaDTO;
    }

    public PedidoVendaDTO update(PedidoVendaDTO pedidoVendaDTO) throws Exception {
        if(pedidoVendaDTO == null) throw new RequiredObjectIsNullException("Objeto PedidoVendaDTO está nulo");
        var PedidoVendaDb = repository.findById(pedidoVendaDTO.getIdPedido()).orElseThrow(() -> new ResourceNotFoundException("Pedido Venda não encontrada"));
        PedidoVenda pedidoVenda = DozerMapper.parseObject(PedidoVendaDb, PedidoVenda.class);
        var Db = repository.save(pedidoVenda);
        pedidoVendaDTO = DozerMapper.parseObject(Db, PedidoVendaDTO.class);
        pedidoVendaDTO.add(linkTo(methodOn(PedidoVendaController.class).findById(pedidoVendaDTO.getIdPedido())).withSelfRel());
        return pedidoVendaDTO;
    }

    public void delete(Long id) throws Exception {
        PedidoVenda PedidoVendaDb = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pedido Venda não encontrada"));
        repository.delete(PedidoVendaDb);
    }
}
