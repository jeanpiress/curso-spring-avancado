package com.jeanPiress.algafood.api.exceptionHandler;

import lombok.Getter;

@Getter
public enum ProblemType {

	DADOS_INVALIDOS("dados-invalidos", "Dados invalidos"),
	MENSAGEM_IMCOMPEENSIVEL("/menssagem-incompreensivel", "Menssagem imcompreensivel"),
	PARAMETRO_INVALIDO("/paramentro-invalido", "O parametro esta incorreto"),
	RECURSO_NAO_ENCONTRADO("/recurso-nao-contrado", "Recurso não encontrado"),
	ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"),
	ERRO_DE_SISTEMA("/erro-de-sistema", "Erro interno inesperado"),
	ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negocio");
	
	
	private String title;
	private String uri;
	
	ProblemType(String path, String title){
		this.uri = "https://algafood.com.br" + path;
		this.title = title;
	}
}
