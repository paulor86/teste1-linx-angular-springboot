package com.linx.teste.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.linx.teste.entities.Produto;
import com.linx.teste.repositories.ProdutoRepository;
import com.linx.teste.services.exceptions.DatabaseException;
import com.linx.teste.services.exceptions.ResourceNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	public List<Produto> findAll() {
		return produtoRepository.findAll();
	}

	public Produto findById(Long id) {
		Optional<Produto> obj = produtoRepository.findById(id);
		return obj.get();
	}

	public Produto insert(Produto obj) {
		return produtoRepository.save(obj);
	}

	public Produto update(Long id, Produto obj) {
		try {
			Produto entity = produtoRepository.getOne(id);
			updateData(entity, obj);
			return produtoRepository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(Produto entity, Produto obj) {
		entity.setNome(obj.getNome());
		entity.setDescricao(obj.getDescricao());
		entity.setPrecoCompra(obj.getPrecoCompra());
		entity.setPrecoVenda(obj.getPrecoVenda());
		entity.setImagem(obj.getImagem());
	}

	public void delete(Long id) {
		try {
			produtoRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	// Método para somar a quantidade de todos o custo de compra de todos os
	// produtos
	public double somaCustoDeCompra() {
		List<Produto> produtoList = produtoRepository.findAll();
		long quantidadeDeProduto = 0;
		double despesasTotais = 0;
		double rateioDespesas = 0;
		for (Produto list : produtoList) {
			despesasTotais = despesasTotais + list.getPrecoCompra();
		}
		return rateioDespesas = despesasTotais / quantidadeDeProduto;
	}

	// Método para somar o preço de venda do produto conforme a regra de négocio do
	// método custo de compra
	public double somaPrecoDeVenda() {
		List<Produto> produtoList = produtoRepository.findAll();
		double margemLucro = 10;
		double somaPrecoVenda = 0;
		double atualizaPrecoVenda = 0;
		for (Produto list : produtoList) {
			somaPrecoVenda = list.getPrecoCompra() + somaCustoDeCompra();
			atualizaPrecoVenda = somaPrecoVenda * (1 + (margemLucro) / 100);
		}
		return atualizaPrecoVenda;
	}
}
