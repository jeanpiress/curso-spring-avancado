package com.jeanPiress.algafood.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.jeanPiress.algafood.domain.exception.EntidadeEmUsoException;
import com.jeanPiress.algafood.domain.exception.GrupoNaoEncontradoException;
import com.jeanPiress.algafood.domain.model.Grupo;
import com.jeanPiress.algafood.domain.repository.GrupoRepository;

@Service
public class CadastroGrupoService {

	private static final String MSG_GRUPO_EM_USO = "Grupo de código %d não pode ser removida, pois esta em uso";
		
	@Autowired
	private GrupoRepository grupoRepository;
	
	public Grupo buscarPorId(Long id) {
		return grupoRepository.findById(id)
				.orElseThrow(() -> new GrupoNaoEncontradoException(id));
	}
	
	@Transactional
	public Grupo adicionar(Grupo grupo) {
		return grupoRepository.save(grupo);
	}
	
	@Transactional
	public void remover(Long id) {
		try {
			grupoRepository.deleteById(id);
		
		} catch (EmptyResultDataAccessException e) {
			throw new GrupoNaoEncontradoException(id);
		
		}catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format(MSG_GRUPO_EM_USO, id));
		}
	}
}
