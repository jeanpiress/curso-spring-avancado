package com.jeanPiress.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jeanPiress.algafood.api.assembler.RestauranteInputDissembler;
import com.jeanPiress.algafood.api.assembler.RestauranteModelAssembler;
import com.jeanPiress.algafood.api.model.RestauranteModel;
import com.jeanPiress.algafood.api.model.input.RestauranteInput;
import com.jeanPiress.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.jeanPiress.algafood.domain.exception.NegocioException;
import com.jeanPiress.algafood.domain.model.Restaurante;
import com.jeanPiress.algafood.domain.repository.RestauranteRepository;
import com.jeanPiress.algafood.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

	@Autowired
	private RestauranteRepository restauranteRepository;

	@Autowired
	private CadastroRestauranteService cadastroRestaurante;
	
	@Autowired
	private RestauranteModelAssembler restauranteModelAssembler;
	
	@Autowired
	private RestauranteInputDissembler restauranteInputDissembler;

	@GetMapping
	public List<RestauranteModel> listar() {
		return restauranteModelAssembler.toCollectionModel(restauranteRepository.findAll());
		
	}

	@GetMapping("/{restauranteId}")
	public RestauranteModel buscar(@PathVariable Long restauranteId) {
		return restauranteModelAssembler.toModel(cadastroRestaurante.buscarPorId(restauranteId));

	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public RestauranteModel cadastrar(@RequestBody @Valid RestauranteInput restauranteInput) {
		try {
			Restaurante restaurante = cadastroRestaurante.adicionar(restauranteInputDissembler.toDomainObject(restauranteInput));
			return restauranteModelAssembler.toModel(restaurante);
		}catch(EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}

			
		
	}
	
	@PutMapping("/{restauranteId}")
	public RestauranteModel atualizar(@PathVariable Long restauranteId, @RequestBody @Valid RestauranteInput restauranteInput){
				
		try {
			Restaurante restauranteAtual = cadastroRestaurante.buscarPorId(restauranteId);
			
			restauranteInputDissembler.copyToDomainObject(restauranteInput, restauranteAtual);
			
			return restauranteModelAssembler.toModel(cadastroRestaurante.adicionar(restauranteAtual)) ;
		}catch(EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
			
					
	}
	
	@DeleteMapping("/{restauranteId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Long restauranteId){
		cadastroRestaurante.remover(restauranteId);
				
	}
	
		
}
