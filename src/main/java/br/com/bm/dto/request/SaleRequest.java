package br.com.bm.dto.request;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

public class SaleRequest {

	@Valid
	private List<ItemSaleRequest> itens = new ArrayList<ItemSaleRequest>();
	
	@NotBlank
	private String socialSecNumber;

	public List<ItemSaleRequest> getItens() {
		return itens;
	}

	public void setItens(List<ItemSaleRequest> itens) {
		this.itens = itens;
	}

	public String getSocialSecNumber() {
		return socialSecNumber;
	}

	public void setSocialSecNumber(String socialSecNumber) {
		this.socialSecNumber = socialSecNumber;
	}
	
	

}
