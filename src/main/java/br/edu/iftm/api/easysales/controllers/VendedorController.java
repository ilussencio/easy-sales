package br.edu.iftm.api.easysales.controllers;

import br.edu.iftm.api.easysales.DTO.VendedorDTO;
import br.edu.iftm.api.easysales.services.VendedorService;
import br.edu.iftm.api.easysales.utils.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/vendedor")
@RestController
public class VendedorController {

    @Autowired
    private VendedorService service;

    @GetMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
    public List<VendedorDTO> findAll(){
        return service.findAll();
    }

    @GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
    public VendedorDTO findById(@PathVariable Long id) throws Exception{
        return service.findById(id);
    }

    @PostMapping(consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
                produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    public VendedorDTO save(@RequestBody VendedorDTO categoriaDTO) throws Exception {
        return service.save(categoriaDTO);
    }

    @PutMapping(consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
                produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    public VendedorDTO update(@RequestBody VendedorDTO categoriaDTO) throws Exception {
        return service.update(categoriaDTO);
    }

    @DeleteMapping
    public void delete(@PathVariable Long id) throws Exception {
        service.delete(id);
    }


}
