package br.com.bm.dto.request;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import br.com.bm.embeddable.PhoneDto;
import br.com.bm.entity.ClientEntity;
import br.com.bm.validator.CPForCNPJ;
import br.com.bm.validator.UniqueValue;

public class ClientRequest {

	@NotBlank
	private String name;

	@NotBlank
	@UniqueValue(domainClass = ClientEntity.class, fieldName = "socialSecNumber")
	@CPForCNPJ
	private String socialSecNumber;

	private String phone1;

	private String phone2;

	@Valid
	private AddressRequest address;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSocialSecNumber() {
		return socialSecNumber;
	}

	public void setSocialSecNumber(String socialSecNumber) {
		this.socialSecNumber = socialSecNumber;
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public AddressRequest getAddress() {
		return address;
	}

	public void setAddress(AddressRequest address) {
		this.address = address;
	}

	
	// TRANSFORMANDO OBJETO DE REQUEST EM UM OBJETO DA ENTIDADE CLIENTE
	public ClientEntity toModel() {

		ClientEntity client = new ClientEntity(this.name, this.socialSecNumber);

		if (this.phone1 != null || this.phone2 != null) {

			PhoneDto phones = new PhoneDto(this.phone1, this.phone2);

			client.setPhones(phones);

		}
		
		AddressRequest address = this.address;
		
		client.setAddress(address.toModel());

		return client;

	}

}
