package br.com.bm.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.bm.entity.SaleEntity;

public class SaleResponse {

	private Long id;

	private LocalDateTime date;

	private ClientResponse client;

	private String obs;

	private BigDecimal total;

	private List<ItemSaleResponse> items = new ArrayList<>();

	public SaleResponse() {
	}

	public SaleResponse(Long id, LocalDateTime date, ClientResponse client, String obs, BigDecimal total,
			List<ItemSaleResponse> items) {
		this.id = id;
		this.date = date;
		this.client = client;
		this.obs = obs;
		this.total = total;
		this.items = items;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public ClientResponse getClient() {
		return client;
	}

	public void setClient(ClientResponse client) {
		this.client = client;
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

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public List<ItemSaleResponse> getItems() {
		return Collections.unmodifiableList(this.items);
	}

	public void setItems(List<ItemSaleResponse> items) {
		this.items = items;
	}

	public void addItem(ItemSaleResponse response) {
		this.items.add(response);
	}

	public SaleResponse toResponse(SaleEntity sale) {

		this.id = sale.getId();
		this.date = sale.getDate();

		sale.getItems().forEach(i -> {
			this.items.add(new ItemSaleResponse(i.getUnitityValue(), i.getQuantity(), i.getDescription()));
		});

		this.obs = sale.getObs();
		this.total = sale.getTotal();

		return this;
	}

}
