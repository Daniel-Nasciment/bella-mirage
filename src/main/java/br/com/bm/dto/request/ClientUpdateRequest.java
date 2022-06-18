package br.com.bm.dto.request;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.bm.embeddable.PhoneDto;
import br.com.bm.entity.AddressEntity;
import br.com.bm.entity.ClientEntity;

public class ClientUpdateRequest {

	private final Logger logger = LoggerFactory.getLogger(ClientUpdateRequest.class);

	@NotBlank
	private String name;

	@NotBlank
	private String phone1;

	@NotBlank
	private String phone2;

	@Valid
	private AddressRequest address;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public ClientEntity toModel(ClientEntity client) {
		
		logger.info("Entrando no método toModel e atualizando atributos da entidade Cliente...");

		PhoneDto phones = new PhoneDto(this.phone1, this.phone2);

		client.setPhones(phones);

		AddressEntity addressUpdated = this.address.updateAddress(client.getAddress());

		client.setAddress(addressUpdated);

		return client;

	}

}
