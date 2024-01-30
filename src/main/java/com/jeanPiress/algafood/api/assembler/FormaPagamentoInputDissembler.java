package com.jeanPiress.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jeanPiress.algafood.api.model.input.FormaPagamentoInput;
import com.jeanPiress.algafood.domain.model.FormaPagamento;

@Component
public class FormaPagamentoInputDissembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public FormaPagamento toDomainObject(FormaPagamentoInput formaPagamentoInput) {
		return modelMapper.map(formaPagamentoInput, FormaPagamento.class);
	}
	
	public void copyToDomainObject(FormaPagamentoInput formaPagamentoImput, FormaPagamento formaPagamento) {
		modelMapper.map(formaPagamentoImput, formaPagamento);
	}
}
