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

import com.jeanPiress.algafood.api.assembler.CidadeInputDissembler;
import com.jeanPiress.algafood.api.assembler.CidadeModelAssembler;
import com.jeanPiress.algafood.api.model.CidadeModel;
import com.jeanPiress.algafood.api.model.input.CidadeInput;
import com.jeanPiress.algafood.domain.exception.EstadoNaoEncontradoException;
import com.jeanPiress.algafood.domain.exception.NegocioException;
import com.jeanPiress.algafood.domain.model.Cidade;
import com.jeanPiress.algafood.domain.repository.CidadeRepository;
import com.jeanPiress.algafood.domain.service.CadastroCidadeService;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private CadastroCidadeService cadastroCidade;
	
	@Autowired
	private CidadeModelAssembler cidadeModelAssembler;
	
	@Autowired
	private CidadeInputDissembler cidadeInputDissembler;
	
	@GetMapping
	public List<CidadeModel> listar(){
		 return cidadeModelAssembler.collectionToModel(cidadeRepository.findAll());
		
	}
	
	@GetMapping("/{cidadeId}")
	public CidadeModel buscar(@PathVariable Long cidadeId) {
		return cidadeModelAssembler.toModel(cadastroCidade.buscarPorId(cidadeId));
				
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CidadeModel adicionar(@RequestBody @Valid CidadeInput cidadeInput){
		try {
		Cidade cidade = cidadeInputDissembler.toDomainObject(cidadeInput);	
		cadastroCidade.adicionar(cidade);
		return cidadeModelAssembler.toModel(cidade);
		}catch(EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}
	
	@PutMapping("/{cidadeId}")
	public CidadeModel atualizar(@PathVariable Long cidadeId, @RequestBody @Valid CidadeInput cidadeInput){
		try {
		Cidade cidade = cadastroCidade.buscarPorId(cidadeId);
		
		cidadeInputDissembler.copyToDomainObject(cidadeInput, cidade);	
		
		return 	cidadeModelAssembler.toModel(cadastroCidade.adicionar(cidade));	
		}catch(EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}
	
	@DeleteMapping("/{cidadeId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Long cidadeId){
		cadastroCidade.remover(cidadeId);
		
	}
}
