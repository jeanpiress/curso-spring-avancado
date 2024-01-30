package com.jeanPiress.algafood.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.jeanPiress.algafood.domain.exception.CozinhaNaoEncontradException;
import com.jeanPiress.algafood.domain.exception.EntidadeEmUsoException;
import com.jeanPiress.algafood.domain.model.Cozinha;
import com.jeanPiress.algafood.domain.repository.CozinhaRepository;

@Service
public class CadastroCozinhaService {

	private static final String MSG_COZINHA_EM_USO = "Cozinha de código %d não pode ser removida, pois esta em uso";
		
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	public Cozinha buscarPorId(Long id) {
		return cozinhaRepository.findById(id).
				orElseThrow(() -> new CozinhaNaoEncontradException(id));
	}
	@Transactional
	public Cozinha adicionar(Cozinha cozinha) {
		return cozinhaRepository.save(cozinha);
		}
	
	@Transactional
	public void remover(Long id) {
		try {
			cozinhaRepository.deleteById(id);
			cozinhaRepository.flush();
		}catch (EmptyResultDataAccessException e) {
			throw new CozinhaNaoEncontradException(id);
		}catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format(MSG_COZINHA_EM_USO, id));
		}
	}
}
