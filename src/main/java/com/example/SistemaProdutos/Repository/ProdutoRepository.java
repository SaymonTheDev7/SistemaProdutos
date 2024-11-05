package com.example.SistemaProdutos.Repository;
import com.example.SistemaProdutos.Model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
