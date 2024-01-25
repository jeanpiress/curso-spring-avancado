package com.jeanPiress.algafood.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jeanPiress.algafood.api.model.RestauranteModel;
import com.jeanPiress.algafood.domain.model.Restaurante;

@Component
public class RestauranteModelAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public RestauranteModel toModel(Restaurante restaurante) {
		return modelMapper.map(restaurante, RestauranteModel.class);
	}
	
	public List<RestauranteModel> toCollectionModel(List<Restaurante> restaurantes){
		List<RestauranteModel> restaurantesModel = restaurantes.stream()
				.map(restaurante -> toModel(restaurante))
				.collect(Collectors.toList());
		
		return restaurantesModel;
	}
}
