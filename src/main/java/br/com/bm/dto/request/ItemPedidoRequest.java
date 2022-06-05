package br.com.bm.dto.request;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;

import org.springframework.lang.NonNull;

public class ItemPedidoRequest {

	@NonNull
	private BigDecimal valorUnitario;

	@NonNull
	private int quantidade;

	@NotBlank
	private String descricao;

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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
