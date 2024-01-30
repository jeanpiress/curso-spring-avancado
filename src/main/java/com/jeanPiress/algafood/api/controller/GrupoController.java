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

import com.jeanPiress.algafood.api.assembler.GrupoInputDissembler;
import com.jeanPiress.algafood.api.assembler.GrupoModelAssembler;
import com.jeanPiress.algafood.api.model.GrupoModel;
import com.jeanPiress.algafood.api.model.input.GrupoInput;
import com.jeanPiress.algafood.domain.model.Grupo;
import com.jeanPiress.algafood.domain.repository.GrupoRepository;
import com.jeanPiress.algafood.domain.service.CadastroGrupoService;

@RestController
@RequestMapping("/grupos")
public class GrupoController {

	@Autowired
	private GrupoRepository grupoRepository;
	
	@Autowired
	private CadastroGrupoService cadastroGrupo;
	
	@Autowired
	private GrupoModelAssembler grupoModelAssembler;
	
	@Autowired
	private GrupoInputDissembler grupoInputDissembler;
	
	@GetMapping
	public List<GrupoModel> listar(){
		 return grupoModelAssembler.collectionToModel(grupoRepository.findAll());
		 
	}
	
	@GetMapping("/{grupoId}")
	public GrupoModel buscar(@PathVariable Long grupoId) {
		return grupoModelAssembler.toModel(cadastroGrupo.buscarPorId(grupoId));
				
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public GrupoModel adicionar(@RequestBody @Valid GrupoInput grupoInput){
		Grupo grupo = grupoInputDissembler.toDomainObject(grupoInput);
		grupoRepository.save(grupo);
		
		return grupoModelAssembler.toModel(grupoRepository.save(grupo));
		
	}
	
	@PutMapping("/{grupoId}")
	public GrupoModel atualizar(@PathVariable Long grupoId, @RequestBody @Valid GrupoInput grupoInput){
		Grupo grupo = cadastroGrupo.buscarPorId(grupoId);
		grupoInputDissembler.copyToDomainObject(grupoInput, grupo);
		
		return grupoModelAssembler.toModel(cadastroGrupo.adicionar(grupo));
			
				
	}
	
	@DeleteMapping("/{grupoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Long grupoId){
			cadastroGrupo.remover(grupoId);
			
		
	}
}
