package com.jeanPiress.algafood.domain.exception;

public class CozinhaNaoEncontradException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public CozinhaNaoEncontradException(String mensagem) {
		super(mensagem);
	}
	
	public CozinhaNaoEncontradException(Long estadoId) {
		this(String.format("Não existe um cadastro de cozinha com codigo %d", estadoId));
	}
}
