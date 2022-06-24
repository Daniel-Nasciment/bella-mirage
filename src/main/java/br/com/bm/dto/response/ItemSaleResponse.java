package br.com.bm.dto.response;

import java.math.BigDecimal;

public class ItemSaleResponse {

	private BigDecimal unitityValue;

	private int quantity;

	private String descripcion;

	public ItemSaleResponse() {
	}

	public ItemSaleResponse(BigDecimal unitityValue, int quantity, String descripcion) {
		this.unitityValue = unitityValue;
		this.quantity = quantity;
		this.descripcion = descripcion;
	}

	public BigDecimal getUnitityValue() {
		return unitityValue;
	}

	public void setUnitityValue(BigDecimal unitityValue) {
		this.unitityValue = unitityValue;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
