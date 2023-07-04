package br.edu.iftm.api.easysales.services;

import br.edu.iftm.api.easysales.DTO.ProdutoDTO;
import br.edu.iftm.api.easysales.controllers.ProdutoController;
import br.edu.iftm.api.easysales.exceptions.RequiredObjectIsNullException;
import br.edu.iftm.api.easysales.exceptions.ResourceNotFoundException;
import br.edu.iftm.api.easysales.mapper.DozerMapper;
import br.edu.iftm.api.easysales.models.Produto;
import br.edu.iftm.api.easysales.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository repository;

    public List<ProdutoDTO> findAll(){
        List<Produto> produtoDbList = repository.findAll();
        var produtos = DozerMapper.parseListObject(produtoDbList, ProdutoDTO.class);
        produtos.stream().forEach(produto -> {
            try {
                produto.add(linkTo(methodOn(ProdutoController.class).findById(produto.getIdProduto())).withSelfRel());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        return produtos;
    }

    public ProdutoDTO findById(Long id) throws Exception{
        Produto produtoDbList = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("produto não encontrada"));
        var produto = DozerMapper.parseObject(produtoDbList, ProdutoDTO.class);
        produto.add(linkTo(methodOn(ProdutoController.class).findById(id)).withSelfRel());
        return produto;
    }

    public ProdutoDTO save(ProdutoDTO produtoDTO) throws Exception {
        if(produtoDTO == null) throw new RequiredObjectIsNullException("Objeto ProdutoDTO está nulo");
        Produto produto = DozerMapper.parseObject(produtoDTO, Produto.class);
        var produtoDb = repository.save(produto);
        produtoDTO = DozerMapper.parseObject(produtoDb, ProdutoDTO.class);
        produtoDTO.add(linkTo(methodOn(ProdutoController.class).findById(produtoDTO.getIdProduto())).withSelfRel());
        return produtoDTO;
    }

    public ProdutoDTO update(ProdutoDTO produtoDTO) throws Exception {
        if(produtoDTO == null) throw new RequiredObjectIsNullException("Objeto ProdutoDTO está nulo");
        var produtoDb = repository.findById(produtoDTO.getIdProduto()).orElseThrow(() -> new ResourceNotFoundException("produto não encontrada"));
        Produto produto = DozerMapper.parseObject(produtoDb, Produto.class);
        var Db = repository.save(produto);
        produtoDTO = DozerMapper.parseObject(Db, ProdutoDTO.class);
        produtoDTO.add(linkTo(methodOn(ProdutoController.class).findById(produtoDTO.getIdProduto())).withSelfRel());
        return produtoDTO;
    }

    public void delete(Long id) throws Exception {
        Produto produtoDb = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("produto não encontrada"));
        repository.delete(produtoDb);
    }
}
