package com.jeanPiress.algafood.domain.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ItemPedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	public Long id;
	
	public BigDecimal precoUnitario;
	public BigDecimal precoTotal;
	public String observacao;
	
	@ManyToOne
	@JoinColumn(name = "pedido", nullable = false)
	public Pedido pedido;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	public Produto produtos;
}
