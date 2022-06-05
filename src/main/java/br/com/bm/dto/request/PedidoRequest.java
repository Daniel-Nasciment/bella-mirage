package br.com.bm.dto.request;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

public class PedidoRequest {

	@Valid
	private List<ItemPedidoRequest> itens = new ArrayList<ItemPedidoRequest>();
	
	@NotBlank
	private String socialSecNumber;

	public List<ItemPedidoRequest> getItens() {
		return itens;
	}

	public void setItens(List<ItemPedidoRequest> itens) {
		this.itens = itens;
	}

	public String getSocialSecNumber() {
		return socialSecNumber;
	}

	public void setSocialSecNumber(String socialSecNumber) {
		this.socialSecNumber = socialSecNumber;
	}
	
	

}
