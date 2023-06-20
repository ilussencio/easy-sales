package br.edu.iftm.api.easysales.controllers;

import br.edu.iftm.api.easysales.DTO.EnderecoDTO;
import br.edu.iftm.api.easysales.services.EnderecoService;
import br.edu.iftm.api.easysales.utils.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/endereco")
@RestController
public class EnderecoController {

    @Autowired
    private EnderecoService service;

    @GetMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
    public List<EnderecoDTO> findAll(){
        return service.findAll();
    }

    @GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
    public EnderecoDTO findById(@PathVariable Long id) throws Exception{
        return service.findById(id);
    }

    @PostMapping(consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
                produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    public EnderecoDTO save(@RequestBody EnderecoDTO categoriaDTO) throws Exception {
        return service.save(categoriaDTO);
    }

    @PutMapping(consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
                produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    public EnderecoDTO update(@RequestBody EnderecoDTO categoriaDTO) throws Exception {
        return service.update(categoriaDTO);
    }

    @DeleteMapping
    public void delete(@PathVariable Long id) throws Exception {
        service.delete(id);
    }


}
