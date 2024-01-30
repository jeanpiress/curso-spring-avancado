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

import com.jeanPiress.algafood.api.assembler.CozinhaInputDissembler;
import com.jeanPiress.algafood.api.assembler.CozinhaModelAssembler;
import com.jeanPiress.algafood.api.model.CozinhaModel;
import com.jeanPiress.algafood.api.model.input.CozinhaInput;
import com.jeanPiress.algafood.domain.model.Cozinha;
import com.jeanPiress.algafood.domain.repository.CozinhaRepository;
import com.jeanPiress.algafood.domain.service.CadastroCozinhaService;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@Autowired
	private CadastroCozinhaService cadastroCozinha;
	
	@Autowired
	private CozinhaModelAssembler cozinhaModelAssembler;
	
	@Autowired
	private CozinhaInputDissembler cozinhaInputDissembler;
	
	@GetMapping
	public List<CozinhaModel> listar(){
		 return cozinhaModelAssembler.toCollectionModel(cozinhaRepository.findAll());
	}
	
	@GetMapping("/{cozinhaId}")
	public CozinhaModel buscar(@PathVariable Long cozinhaId) {
		return cozinhaModelAssembler.toModel(cadastroCozinha.buscarPorId(cozinhaId));
				
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CozinhaModel adicionar(@RequestBody @Valid CozinhaInput cozinhaInput){
		Cozinha cozinha = cozinhaInputDissembler.toDomainObject(cozinhaInput);
		return cozinhaModelAssembler.toModel(cozinhaRepository.save(cozinha));
		
	}
	
	@PutMapping("/{cozinhaId}")
	public CozinhaModel atualizar(@PathVariable Long cozinhaId, @RequestBody @Valid CozinhaInput cozinhaInput){
		Cozinha cozinha = cadastroCozinha.buscarPorId(cozinhaId);
		
		cozinhaInputDissembler.copyToDomainObject(cozinhaInput, cozinha);
		
		return cozinhaModelAssembler.toModel(cadastroCozinha.adicionar(cozinha));
			
					
	}
	
	@DeleteMapping("/{cozinhaId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Long cozinhaId){
		cadastroCozinha.remover(cozinhaId);
				
	}
}
