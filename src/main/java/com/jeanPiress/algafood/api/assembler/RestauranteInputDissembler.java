package com.jeanPiress.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jeanPiress.algafood.api.model.input.RestauranteInput;
import com.jeanPiress.algafood.domain.model.Cidade;
import com.jeanPiress.algafood.domain.model.Cozinha;
import com.jeanPiress.algafood.domain.model.Restaurante;

@Component
public class RestauranteInputDissembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public Restaurante toDomainObject(RestauranteInput restauranteInput) {
		return modelMapper.map(restauranteInput, Restaurante.class);
	}
	
	public void copyToDomainObject(RestauranteInput restauranteImput, Restaurante restaurante) {
		//Para evitar erro do jpa achar que está tentando alterar o id 
		//de uma cozinha que já existe.
		restaurante.setCozinha(new Cozinha());
		
		if(restaurante.getEndereco() != null) {
			restaurante.getEndereco().setCidade(new Cidade());
		}
		
		modelMapper.map(restauranteImput, restaurante);
	}
}
