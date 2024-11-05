package com.example.SistemaProdutos.Controller;

import com.example.SistemaProdutos.Model.Produto;
import com.example.SistemaProdutos.Service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public List <Produto> listarTodos () {
        return produtoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity <Produto> buscarPorId (@PathVariable Long id) {
        Optional<Produto>produto = produtoService.buscarPorId(id);
        return produto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Produto> salvar (@RequestBody Produto produto) {
        Produto produtoSalvo = produtoService.salvar(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizar (@PathVariable Long id, @RequestBody Produto produto) {
        if ( !produtoService.buscarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        produto.setId(id);
        Produto produtoAtualizado = produtoService.salvar(produto);
        return ResponseEntity.ok(produtoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar (@PathVariable Long id) {
        if (!produtoService.buscarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        produtoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
