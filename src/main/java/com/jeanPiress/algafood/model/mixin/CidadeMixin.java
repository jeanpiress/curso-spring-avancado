package com.jeanPiress.algafood.model.mixin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jeanPiress.algafood.domain.model.Estado;

public class CidadeMixin {

	@JsonIgnoreProperties(value = "nome", allowGetters = true)
	public Estado estado;
}
