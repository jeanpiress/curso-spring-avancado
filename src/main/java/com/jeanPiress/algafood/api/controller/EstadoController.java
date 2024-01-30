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

import com.jeanPiress.algafood.api.assembler.EstadoInputDissembler;
import com.jeanPiress.algafood.api.assembler.EstadoModelAssembler;
import com.jeanPiress.algafood.api.model.EstadoModel;
import com.jeanPiress.algafood.api.model.input.EstadoInput;
import com.jeanPiress.algafood.domain.model.Estado;
import com.jeanPiress.algafood.domain.repository.EstadoRepository;
import com.jeanPiress.algafood.domain.service.CadastroEstadoService;

@RestController
@RequestMapping("/estados")
public class EstadoController {

	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CadastroEstadoService cadastroEstado;
	
	@Autowired
	private EstadoModelAssembler estadoModelAssembler;
	
	@Autowired
	private EstadoInputDissembler estadoInputDissembler;
	
	@GetMapping
	public List<EstadoModel> listar(){
		 return estadoModelAssembler.collectionToModel(estadoRepository.findAll());
		 
	}
	
	@GetMapping("/{estadoId}")
	public EstadoModel buscar(@PathVariable Long estadoId) {
		return estadoModelAssembler.toModel(cadastroEstado.buscarPorId(estadoId));
				
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EstadoModel adicionar(@RequestBody @Valid EstadoInput estadoInput){
		Estado estado = estadoInputDissembler.toDomainObject(estadoInput);
		estadoRepository.save(estado);
		
		return estadoModelAssembler.toModel(estadoRepository.save(estado));
		
	}
	
	@PutMapping("/{estadoId}")
	public EstadoModel atualizar(@PathVariable Long estadoId, @RequestBody @Valid EstadoInput estadoInput){
		Estado estado = cadastroEstado.buscarPorId(estadoId);
		estadoInputDissembler.copyToDomainObject(estadoInput, estado);
		
		return estadoModelAssembler.toModel(cadastroEstado.adicionar(estado));
			
				
	}
	
	@DeleteMapping("/{estadoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Long estadoId){
			cadastroEstado.remover(estadoId);
			
		
	}
}
