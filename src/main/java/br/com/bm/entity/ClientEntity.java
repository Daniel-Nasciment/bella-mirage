package br.com.bm.entity;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.bm.embeddable.PhoneDto;

@Entity
@Table(name = "CLIENTS")
public class ClientEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String socialSecNumber;

	@Embedded
	private PhoneDto phones;

	@OneToOne(cascade = CascadeType.ALL)
	private AddressEntity address;

	@Deprecated
	public ClientEntity() {
	}

	public ClientEntity(String name, String socialSecNumber) {
		this.name = name;
		this.socialSecNumber = socialSecNumber;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String nome) {
		this.name = nome;
	}

	public String getSocialSecNumber() {
		return socialSecNumber;
	}

	public void setSocialSecNumber(String socialSecNumber) {
		this.socialSecNumber = socialSecNumber;
	}

	public PhoneDto getPhones() {
		return phones;
	}

	public void setPhones(PhoneDto telefones) {
		this.phones = telefones;
	}

	public AddressEntity getAddress() {
		return address;
	}

	public void setAddress(AddressEntity address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "ClienteEntity [nome=" + name + ", socialSecNumber=" + socialSecNumber + ", telefones=" + phones
				+ "]";
	}

}
