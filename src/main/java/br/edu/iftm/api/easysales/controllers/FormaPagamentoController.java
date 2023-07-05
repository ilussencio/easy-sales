package br.edu.iftm.api.easysales.controllers;

import br.edu.iftm.api.easysales.DTO.FormaPagamentoDTO;
import br.edu.iftm.api.easysales.services.FormaPagamentoService;
import br.edu.iftm.api.easysales.utils.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/forma-pagamento")
@RestController
public class FormaPagamentoController {

    @Autowired
    private FormaPagamentoService service;

    @GetMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
    public List<FormaPagamentoDTO> findAll(){
        return service.findAll();
    }

    @GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
    public FormaPagamentoDTO findById(@PathVariable Long id) throws Exception{
        return service.findById(id);
    }

    @PostMapping(consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
                produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    public FormaPagamentoDTO save(@RequestBody FormaPagamentoDTO categoriaDTO) throws Exception {
        return service.save(categoriaDTO);
    }

    @PutMapping(consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
                produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    public FormaPagamentoDTO update(@RequestBody FormaPagamentoDTO categoriaDTO) throws Exception {
        return service.update(categoriaDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) throws Exception {
        service.delete(id);
    }


}
