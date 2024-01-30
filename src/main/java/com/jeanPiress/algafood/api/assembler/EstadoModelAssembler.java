package com.jeanPiress.algafood.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jeanPiress.algafood.api.model.EstadoModel;
import com.jeanPiress.algafood.domain.model.Estado;

@Component
public class EstadoModelAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public EstadoModel toModel(Estado estado) {
		return modelMapper.map(estado, EstadoModel.class);
	}
	
	public List<EstadoModel> collectionToModel(List<Estado> estados){
		List<EstadoModel> estadosModel = estados.stream()
				.map(estado -> toModel(estado))
				.collect(Collectors.toList());
		
		return estadosModel;
	}
}
