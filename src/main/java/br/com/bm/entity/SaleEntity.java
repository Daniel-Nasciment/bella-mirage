package br.com.bm.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "SALES")
public class SaleEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private LocalDateTime date;

	@ManyToOne(fetch = FetchType.LAZY)
	private ClientEntity client;

	@OneToMany(mappedBy = "sale", cascade = CascadeType.ALL)
	private List<ItemSaleEntity> items = new ArrayList<>();

	private String obs;

	private BigDecimal total;

	@Deprecated
	public SaleEntity() {

	}
	
	public void addItem(ItemSaleEntity item) {
		item.setSale(this);
		this.items.add(item);
		this.total = new BigDecimal(item.getQuantity()).multiply(item.getUnitityValue());
	}

	public SaleEntity(ClientEntity client) {
		this.client = client;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public ClientEntity getClient() {
		return client;
	}

	public void setClient(ClientEntity client) {
		this.client = client;
	}

	public List<ItemSaleEntity> getItems() {
		return items;
	}

	public void setItems(List<ItemSaleEntity> items) {
		this.items = items;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal unitiyValue) {
		this.total = unitiyValue;
	}

}