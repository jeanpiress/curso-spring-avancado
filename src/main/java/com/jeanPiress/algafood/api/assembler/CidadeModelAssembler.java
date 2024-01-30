package com.jeanPiress.algafood.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jeanPiress.algafood.api.model.CidadeModel;
import com.jeanPiress.algafood.domain.model.Cidade;

@Component
public class CidadeModelAssembler {

	@Autowired
	private ModelMapper modelMaper;
	
	public CidadeModel toModel (Cidade cidade) {
		return modelMaper.map(cidade, CidadeModel.class);
	}
	
	public List<CidadeModel> collectionToModel(List<Cidade> cidades){
		List<CidadeModel> cidadesModel = cidades.stream()
				.map(cidade -> toModel(cidade))
				.collect(Collectors.toList());
		
		return cidadesModel;
	}
}
