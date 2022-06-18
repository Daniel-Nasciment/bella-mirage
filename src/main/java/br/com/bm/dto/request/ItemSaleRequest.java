package br.com.bm.dto.request;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;

import org.springframework.lang.NonNull;

public class ItemSaleRequest {

	@NonNull
	private BigDecimal unitaryValue;

	@NonNull
	private int quantity;

	@NotBlank
	private String description;

	public BigDecimal getUnitaryValue() {
		return unitaryValue;
	}

	public void setUnitaryValue(BigDecimal unitaryValue) {
		this.unitaryValue = unitaryValue;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescricao(String description) {
		this.description = description;
	}

}
