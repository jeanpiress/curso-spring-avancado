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

import com.jeanPiress.algafood.api.assembler.FormaPagamentoInputDissembler;
import com.jeanPiress.algafood.api.assembler.FormaPagamentoModelAssembler;
import com.jeanPiress.algafood.api.model.FormaPagamentoModel;
import com.jeanPiress.algafood.api.model.input.FormaPagamentoInput;
import com.jeanPiress.algafood.domain.model.FormaPagamento;
import com.jeanPiress.algafood.domain.repository.FormaPagamentoRepository;
import com.jeanPiress.algafood.domain.service.CadastroFormaPagamentoService;

@RestController
@RequestMapping("/formaPagamentos")
public class FormaPagamentoControler {

	@Autowired
	private FormaPagamentoRepository formaPagamentoRespository;
	
	@Autowired
	private CadastroFormaPagamentoService cadastroFormaPagamentoService;
	
	@Autowired
	private FormaPagamentoModelAssembler formaPagamentoModelAssembler;
	
	@Autowired
	private FormaPagamentoInputDissembler formaPagamentoInputDissembler;
	
	
	@GetMapping
	public List<FormaPagamentoModel> buscar(){
		return formaPagamentoModelAssembler.toCollectionModel(formaPagamentoRespository.findAll());
	}
	
	@GetMapping("/{formaPagamentoId}")
	public FormaPagamentoModel buscarPorId(@PathVariable Long formaPagamentoId) {
		return formaPagamentoModelAssembler.toModel(cadastroFormaPagamentoService.buscarPorId(formaPagamentoId)) ;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public FormaPagamentoModel cadastrar(@RequestBody @Valid FormaPagamentoInput formaPagamentoInput) {
		FormaPagamento formaPagamento = formaPagamentoInputDissembler.toDomainObject(formaPagamentoInput);
		
		cadastroFormaPagamentoService.adicionar(formaPagamento);
		
		return formaPagamentoModelAssembler.toModel(formaPagamento);
	}
	
	@PutMapping("/{formaPagamentoId}")
	public FormaPagamentoModel atualizar(@PathVariable Long formaPagamentoId, @RequestBody @Valid FormaPagamentoInput formaPagamentoInput) {
		FormaPagamento formaPagamentoAtual = cadastroFormaPagamentoService.buscarPorId(formaPagamentoId);
		
		formaPagamentoInputDissembler.copyToDomainObject(formaPagamentoInput, formaPagamentoAtual);
		
		return formaPagamentoModelAssembler.toModel(cadastroFormaPagamentoService.adicionar(formaPagamentoAtual));
	}
	
	@DeleteMapping("/{formaPagamentoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Long formaPagamentoId) {
		cadastroFormaPagamentoService.excluir(formaPagamentoId);
	}
}
