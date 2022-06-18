package br.com.bm.dto.response;

import br.com.bm.entity.ClientEntity;
import br.com.bm.entity.AddressEntity;

public class ClientResponse {

	private String name;

	private String socialSecNumber;

	private String phone1;

	private String phone2;

	private AddressResponse address;
	

	public ClientResponse() {
		
	}
	
	public ClientResponse(String name, String socialSecNumber, String phone1, String phone2, AddressResponse address) {
		this.name = name;
		this.socialSecNumber = socialSecNumber;
		this.phone1 = phone1;
		this.phone2 = phone2;
		this.address = address;
	}

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

	public AddressResponse getAddress() {
		return address;
	}

	public void setAddress(AddressResponse address) {
		this.address = address;
	}

	public ClientResponse toResponse(ClientEntity client) {

		AddressEntity entity = client.getAddress();

		this.name = client.getName();
		this.phone1 = client.getPhones().getPhone1();
		this.phone2 = client.getPhones().getPhone2();
		this.socialSecNumber = client.getSocialSecNumber();
		this.address = new AddressResponse(entity.getRoad(), entity.getDistrict(), entity.getNumber(),
				entity.getCity(), entity.getState(), entity.getPostCode());
		
		return this;

	}

}
