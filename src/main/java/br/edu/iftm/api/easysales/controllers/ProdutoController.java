package br.edu.iftm.api.easysales.controllers;

import br.edu.iftm.api.easysales.DTO.ProdutoDTO;
import br.edu.iftm.api.easysales.services.ProdutoService;
import br.edu.iftm.api.easysales.utils.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/produto")
@RestController
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @GetMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
    public List<ProdutoDTO> findAll(){
        return service.findAll();
    }

    @GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
    public ProdutoDTO findById(@PathVariable Long id) throws Exception{
        return service.findById(id);
    }

    @PostMapping(consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
                produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    public ProdutoDTO save(@RequestBody ProdutoDTO categoriaDTO) throws Exception {
        return service.save(categoriaDTO);
    }

    @PutMapping(consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
                produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    public ProdutoDTO update(@RequestBody ProdutoDTO categoriaDTO) throws Exception {
        return service.update(categoriaDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) throws Exception {
        service.delete(id);
    }


}
