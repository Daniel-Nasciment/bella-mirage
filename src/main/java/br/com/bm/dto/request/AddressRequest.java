package br.com.bm.dto.request;

import javax.validation.constraints.NotBlank;

import com.sun.istack.NotNull;

import br.com.bm.entity.AddressEntity;

public class AddressRequest {

	@NotBlank
	private String road;

	@NotBlank
	private String district;

	@NotNull
	private Integer number;

	@NotBlank
	private String city;

	@NotBlank
	private String state;

	@NotBlank
	private String postCode;

	public String getRoad() {
		return road;
	}

	public void setRoad(String road) {
		this.road = road;
	}

	public String getdistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public AddressEntity toModel() {

		return new AddressEntity(this.road, this.district, this.number, this.city, this.state, this.postCode);

	}

	public AddressEntity updateEndereco(AddressEntity address) {

		address.setRoad(this.road);
		address.setDistrict(this.district);
		address.setNumber(this.number);
		address.setCity(this.city);
		address.setState(this.state);
		address.setPostCode(this.postCode);

		return address;
	}

}
