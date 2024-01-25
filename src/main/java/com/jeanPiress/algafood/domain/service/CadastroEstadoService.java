package com.jeanPiress.algafood.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.jeanPiress.algafood.domain.exception.EntidadeEmUsoException;
import com.jeanPiress.algafood.domain.exception.EstadoNaoEncontradoException;
import com.jeanPiress.algafood.domain.model.Estado;
import com.jeanPiress.algafood.domain.repository.EstadoRepository;

@Service
public class CadastroEstadoService {

	private static final String MSG_ESTADO_EM_USO = "Estado de código %d não pode ser removida, pois esta em uso";
		
	@Autowired
	private EstadoRepository estadoRepository;
	
	public Estado buscarPorId(Long id) {
		return estadoRepository.findById(id)
				.orElseThrow(() -> new EstadoNaoEncontradoException(id));
	}
	
	@Transactional
	public Estado adicionar(Estado estado) {
		return estadoRepository.save(estado);
	}
	
	@Transactional
	public void remover(Long id) {
		try {
			estadoRepository.deleteById(id);
		
		} catch (EmptyResultDataAccessException e) {
			throw new EstadoNaoEncontradoException(id);
		
		}catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format(MSG_ESTADO_EM_USO, id));
		}
	}
}
