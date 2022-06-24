package br.com.bm.dto.request;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

public class SaleRequest {

	@Valid
	private List<ItemSaleRequest> items = new ArrayList<ItemSaleRequest>();
	
	@NotBlank
	private String socialSecNumber;

	public List<ItemSaleRequest> getItems() {
		return items;
	}

	public void setItems(List<ItemSaleRequest> items) {
		this.items = items;
	}

	public String getSocialSecNumber() {
		return socialSecNumber;
	}

	public void setSocialSecNumber(String socialSecNumber) {
		this.socialSecNumber = socialSecNumber;
	}
	
	

}
