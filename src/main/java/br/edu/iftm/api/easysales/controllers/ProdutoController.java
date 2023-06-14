package br.edu.iftm.api.easysales.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/produtos")
@RestController
public class ProdutoController {

    @GetMapping
    public String listar() {
        return "Listando produtos";
    }
}
