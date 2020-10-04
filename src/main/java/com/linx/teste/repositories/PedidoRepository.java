package com.linx.teste.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.linx.teste.entities.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long>  {

}
