package com.jeanPiress.algafood.domain.exception;

public class CidadeNaoEncontradException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public CidadeNaoEncontradException(String mensagem) {
		super(mensagem);
	}
	
	public CidadeNaoEncontradException(Long estadoId) {
		this(String.format("Não existe um cadastro de cidade com codigo %d", estadoId));
	}
}
