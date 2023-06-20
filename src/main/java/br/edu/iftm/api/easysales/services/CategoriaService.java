package br.edu.iftm.api.easysales.services;

import br.edu.iftm.api.easysales.DTO.CategoriaDTO;
import br.edu.iftm.api.easysales.controllers.CategoriaController;
import br.edu.iftm.api.easysales.exceptions.RequiredObjectIsNullException;
import br.edu.iftm.api.easysales.exceptions.ResourceNotFoundException;
import br.edu.iftm.api.easysales.mapper.DozerMapper;
import br.edu.iftm.api.easysales.models.Categoria;
import br.edu.iftm.api.easysales.repositories.CategoriaRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    public List<CategoriaDTO> findAll(){
        List<Categoria> categoriasDbList = repository.findAll();
        var categorias = DozerMapper.parseListObject(categoriasDbList, CategoriaDTO.class);
        categorias.stream().forEach(categoria -> {
            try {
                categoria.add(linkTo(methodOn(CategoriaController.class).findById(categoria.getIdCategoria())).withSelfRel());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        return categorias;
    }

    public CategoriaDTO findById(Long id) throws Exception{
        Categoria categoriaDb = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada"));
        var categoria = DozerMapper.parseObject(categoriaDb, CategoriaDTO.class);
        categoria.add(linkTo(methodOn(CategoriaController.class).findById(id)).withSelfRel());
        return categoria;
    }

    public CategoriaDTO save(CategoriaDTO categoriaDTO) throws Exception {
        if(categoriaDTO == null) throw new RequiredObjectIsNullException("Objeto categoriaDTO está nulo");
        Categoria categoria = DozerMapper.parseObject(categoriaDTO, Categoria.class);
        var userDB = repository.save(categoria);
        categoriaDTO = DozerMapper.parseObject(userDB, CategoriaDTO.class);
        categoriaDTO.add(linkTo(methodOn(CategoriaController.class).findById(categoriaDTO.getIdCategoria())).withSelfRel());
        return categoriaDTO;
    }

    public CategoriaDTO update(CategoriaDTO categoriaDTO) throws Exception {
        if(categoriaDTO == null) throw new RequiredObjectIsNullException("Objeto categoriaDTO está nulo");
        var categoriaDb = repository.findById(categoriaDTO.getIdCategoria()).orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada"));
        Categoria categoria = DozerMapper.parseObject(categoriaDTO, Categoria.class);
        var Db = repository.save(categoria);
        categoriaDTO = DozerMapper.parseObject(Db, CategoriaDTO.class);
        categoriaDTO.add(linkTo(methodOn(CategoriaController.class).findById(categoriaDTO.getIdCategoria())).withSelfRel());
        return categoriaDTO;
    }

    public void delete(Long id) throws Exception {
        Categoria categoriaDb = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada"));
        repository.delete(categoriaDb);
    }
}
