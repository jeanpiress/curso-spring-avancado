package com.jeanPiress.algafood.core.jackson;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.jeanPiress.algafood.domain.model.Cidade;
import com.jeanPiress.algafood.domain.model.Cozinha;
import com.jeanPiress.algafood.model.mixin.CidadeMixin;
import com.jeanPiress.algafood.model.mixin.CozinhaMixin;

@Component
public class JacksonMixinModule extends SimpleModule{

	private static final long serialVersionUID = 1L;

	public JacksonMixinModule() {
		setMixInAnnotation(Cidade.class, CidadeMixin.class);
		setMixInAnnotation(Cozinha.class, CozinhaMixin.class);
	}
}
