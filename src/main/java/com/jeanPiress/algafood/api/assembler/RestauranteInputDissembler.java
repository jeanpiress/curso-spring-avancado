package com.jeanPiress.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jeanPiress.algafood.api.model.input.RestauranteInput;
import com.jeanPiress.algafood.domain.model.Cozinha;
import com.jeanPiress.algafood.domain.model.Restaurante;

@Component
public class RestauranteInputDissembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public Restaurante toDomainObject(RestauranteInput restauranteImput) {
		return modelMapper.map(restauranteImput, Restaurante.class);
	}
	
	public void copyToDomainObject(RestauranteInput restauranteImput, Restaurante restaurante) {
		//Para evitar erro do jpa achar que está tentando alterar o id 
		//de uma cozinha que já existe.
		restaurante.setCozinha(new Cozinha());
		
		modelMapper.map(restauranteImput, restaurante);
	}
}
