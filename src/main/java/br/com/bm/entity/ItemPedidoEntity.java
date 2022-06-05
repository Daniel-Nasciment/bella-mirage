package br.com.bm.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ITENS_PEDIDO")
public class ItemPedidoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "valor_unitario")
	private BigDecimal valorUnitario;

	private int quantidade;

	private String descricao;

	@ManyToOne(fetch = FetchType.LAZY)
	private PedidoEntity pedido;

	@Deprecated
	public ItemPedidoEntity() {

	}
	
	
	public ItemPedidoEntity(BigDecimal valorUnitario, int quantidade, String descricao, PedidoEntity pedido) {
		this.valorUnitario = valorUnitario;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.pedido = pedido;
	}



	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public PedidoEntity getPedido() {
		return pedido;
	}

	public void setPedido(PedidoEntity pedido) {
		this.pedido = pedido;
	}

	public Long getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
