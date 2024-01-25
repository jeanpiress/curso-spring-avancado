package com.jeanPiress.algafood.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	public Long id;
	
	public BigDecimal taxaFrete;
	public BigDecimal valorTotal;
	
	public StatusPedido status;
	
	@Embedded
	public Endereco endereco;
	
	@CreationTimestamp
	public OffsetDateTime dataCriacao;
	
	public OffsetDateTime dataConfirmacao;
	
	public OffsetDateTime dataCancelamento;
	
	public OffsetDateTime dataEntrega;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	public FormaPagamento formaPagamento;
    
	@ManyToOne
	@JoinColumn(nullable = false)
	public Restaurante restaurante;
	
	@ManyToOne
	@JoinColumn(name = "usuario_cliente_id", nullable = false)
	public Usuario cliente;
	    	
	@OneToMany(mappedBy = "pedido")
	public List<ItemPedido> itens = new ArrayList<>();

}

