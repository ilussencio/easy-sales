package br.edu.iftm.api.easysales.services;

import br.edu.iftm.api.easysales.DTO.FormaPagamentoDTO;
import br.edu.iftm.api.easysales.controllers.FormaPagamentoController;
import br.edu.iftm.api.easysales.exceptions.RequiredObjectIsNullException;
import br.edu.iftm.api.easysales.exceptions.ResourceNotFoundException;
import br.edu.iftm.api.easysales.mapper.DozerMapper;
import br.edu.iftm.api.easysales.models.FormaPagamento;
import br.edu.iftm.api.easysales.repositories.FormaPagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class FormaPagamentoService {
    @Autowired
    private FormaPagamentoRepository repository;

    public List<FormaPagamentoDTO> findAll(){
        List<FormaPagamento> clientesDbList = repository.findAll();
        var formaPagamentos = DozerMapper.parseListObject(clientesDbList, FormaPagamentoDTO.class);
        formaPagamentos.stream().forEach(formaPagamento -> {
            try {
                formaPagamento.add(linkTo(methodOn(FormaPagamentoController.class).findById(formaPagamento.getIdFormaPagamento())).withSelfRel());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        return formaPagamentos;
    }

    public FormaPagamentoDTO findById(Long id) throws Exception{
        FormaPagamento formaPagamentoDb = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("formaPagamento não encontrada"));
        var formaPagamento = DozerMapper.parseObject(formaPagamentoDb, FormaPagamentoDTO.class);
        return formaPagamento;
    }

    public FormaPagamentoDTO save(FormaPagamentoDTO formaPagamentoDTO) throws Exception {
        if(formaPagamentoDTO == null) throw new RequiredObjectIsNullException("Objeto FormaPagamentoDTO está nulo");
        FormaPagamento formaPagamento = DozerMapper.parseObject(formaPagamentoDTO, FormaPagamento.class);
        var formaPamentoDb = repository.save(formaPagamento);
        formaPagamentoDTO = DozerMapper.parseObject(formaPamentoDb, FormaPagamentoDTO.class);
        return formaPagamentoDTO;
    }

    public FormaPagamentoDTO update(FormaPagamentoDTO formaPagamentoDTO) throws Exception {
        if(formaPagamentoDTO == null) throw new RequiredObjectIsNullException("Objeto FormaPagamentoDTO está nulo");
        var formaPagamentoDb = repository.findById(formaPagamentoDTO.getIdFormaPagamento()).orElseThrow(() -> new ResourceNotFoundException("formaPagamento não encontrada"));
        FormaPagamento formaPagamento = DozerMapper.parseObject(formaPagamentoDb, FormaPagamento.class);
        var Db = repository.save(formaPagamento);
        formaPagamentoDTO = DozerMapper.parseObject(Db, FormaPagamentoDTO.class);
        return formaPagamentoDTO;
    }

    public void delete(Long id) throws Exception {
        FormaPagamento enderecoDb = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("formaPagamento não encontrada"));
        repository.delete(enderecoDb);
    }
}
