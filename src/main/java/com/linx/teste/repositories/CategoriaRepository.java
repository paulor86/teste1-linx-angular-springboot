package com.linx.teste.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.linx.teste.entities.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>  {

}
