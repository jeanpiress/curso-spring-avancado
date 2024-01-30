package com.jeanPiress.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jeanPiress.algafood.api.model.input.CidadeInput;
import com.jeanPiress.algafood.domain.model.Cidade;
import com.jeanPiress.algafood.domain.model.Estado;

@Component
public class CidadeInputDissembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public Cidade toDomainObject (CidadeInput cidadeInput) {
		return modelMapper.map(cidadeInput, Cidade.class);
	}
	
	public void copyToDomainObject(CidadeInput cidadeInput, Cidade cidade) {
		//Para evitar erro do jpa achar que está tentando alterar o id 
		//de um estado que já existe.
		cidade.setEstado(new Estado());
		
		modelMapper.map(cidadeInput, cidade);
	}
}
