package com.linx.teste.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.linx.teste.entities.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>  {

}
