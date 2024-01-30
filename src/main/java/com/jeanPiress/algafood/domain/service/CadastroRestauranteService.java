package com.jeanPiress.algafood.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.jeanPiress.algafood.domain.exception.EntidadeEmUsoException;
import com.jeanPiress.algafood.domain.exception.RestauranteNaoEncontradoException;
import com.jeanPiress.algafood.domain.model.Cozinha;
import com.jeanPiress.algafood.domain.model.Restaurante;
import com.jeanPiress.algafood.domain.repository.RestauranteRepository;

@Service
public class CadastroRestauranteService {

	private static final String MSG_RESTAURANTE_EM_USO = "Restaurante de código %d não pode ser removida, pois esta em uso";

	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CadastroCozinhaService cadastroCozinha;
	
	public Restaurante buscarPorId(Long id) {
		return restauranteRepository.findById(id).
				orElseThrow(() -> new RestauranteNaoEncontradoException(id));
	}
	
	@Transactional
	public Restaurante adicionar(Restaurante restaurante) {
		Long cozinhaId = restaurante.getCozinha().getId();
		Cozinha cozinha = cadastroCozinha.buscarPorId(cozinhaId);
			
		restaurante.setCozinha(cozinha);
		
		return restauranteRepository.save(restaurante);
	}
	
	@Transactional
	public void ativar(Long restauranteId) {
		Restaurante restaurante = buscarPorId(restauranteId);
		restaurante.ativar();
	}
	
	@Transactional
	public void inativar(Long restauranteId) {
		Restaurante restaurante = buscarPorId(restauranteId);
		restaurante.inativar();
	}
	
	@Transactional
	public void remover(Long id) {
		try {
			restauranteRepository.deleteById(id);
		}catch (EmptyResultDataAccessException e) {
			throw new RestauranteNaoEncontradoException(id);
		}catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format(MSG_RESTAURANTE_EM_USO, id));
		}
	}
}
