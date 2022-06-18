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
@Table(name = "ITEMS_SALE")
public class ItemSaleEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "unitity_value")
	private BigDecimal unitityValue;

	private int quantity;

	private String description;

	@ManyToOne(fetch = FetchType.LAZY)
	private SaleEntity sale;

	@Deprecated
	public ItemSaleEntity() {

	}
	
	
	public ItemSaleEntity(BigDecimal unitityValue, int quantity, String description, SaleEntity sale) {
		this.unitityValue = unitityValue;
		this.quantity = quantity;
		this.description = description;
		this.sale = sale;
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

	public SaleEntity getSale() {
		return sale;
	}

	public void setSale(SaleEntity sale) {
		this.sale = sale;
	}

	public Long getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
