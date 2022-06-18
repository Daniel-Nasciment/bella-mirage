package br.com.bm.embeddable;

import javax.persistence.Embeddable;

@Embeddable
public class PhoneDto {

	private String phone1;

	private String phone2;

	@Deprecated
	public PhoneDto() {
	}

	public PhoneDto(String phone1, String phone2) {
		this.phone1 = phone1;
		this.phone2 = phone2;
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

}
