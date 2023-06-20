package br.edu.iftm.api.easysales.controllers;

import br.edu.iftm.api.easysales.DTO.CategoriaDTO;
import br.edu.iftm.api.easysales.services.CategoriaService;
import br.edu.iftm.api.easysales.utils.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/categoria")
@RestController
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    @GetMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
    public List<CategoriaDTO> findAll(){
        return service.findAll();
    }

    @GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
    public CategoriaDTO findById(@PathVariable Long id) throws Exception{
        return service.findById(id);
    }

    @PostMapping(consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
                produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    public CategoriaDTO save(@RequestBody CategoriaDTO categoriaDTO) throws Exception {
        return service.save(categoriaDTO);
    }

    @PutMapping(consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
                produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    public CategoriaDTO update(@RequestBody CategoriaDTO categoriaDTO) throws Exception {
        return service.update(categoriaDTO);
    }

    @DeleteMapping
    public void delete(@PathVariable Long id) throws Exception {
        service.delete(id);
    }


}
