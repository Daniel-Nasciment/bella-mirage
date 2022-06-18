package br.com.bm.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ADDRESS")
public class AddressEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String road;

	private String district;

	private Integer number;

	private String city;

	private String state;

	private String postCode;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "address")
	private ClientEntity client;

	@Deprecated
	public AddressEntity() {
	}

	public AddressEntity(String road, String district, Integer number, String city, String state, String postCode) {
		this.road = road;
		this.district = district;
		this.number = number;
		this.city = city;
		this.state = state;
		this.postCode = postCode;
	}

	public String getRoad() {
		return road;
	}

	public void setRoad(String rua) {
		this.road = rua;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String bairro) {
		this.district = bairro;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer numero) {
		this.number = numero;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String cidade) {
		this.city = cidade;
	}

	public String getState() {
		return state;
	}

	public void setState(String estado) {
		this.state = estado;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String cep) {
		this.postCode = cep;
	}

	public Long getId() {
		return id;
	}

}
