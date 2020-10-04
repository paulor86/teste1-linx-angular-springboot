package com.linx.teste.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.linx.teste.entities.Categoria;
import com.linx.teste.repositories.CategoriaRepository;
import com.linx.teste.services.exceptions.DatabaseException;
import com.linx.teste.services.exceptions.ResourceNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public List<Categoria> findAll() {
		return categoriaRepository.findAll();
	}
	
	public Categoria findById(Long id) {
		Optional<Categoria> obj = categoriaRepository.findById(id);
		return obj.get();
	}
	
	public Categoria insert(Categoria obj) {
		return categoriaRepository.save(obj);
	}

	public Categoria update(Long id, Categoria obj) {
		try {
			Categoria entity = categoriaRepository.getOne(id);
			updateData(entity, obj);
			return categoriaRepository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(Categoria entity, Categoria obj) {
		entity.setNome(obj.getNome());
	}

	public void delete(Long id) {
		try {
			categoriaRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
}
