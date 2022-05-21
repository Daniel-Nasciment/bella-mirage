package br.com.bm.dto;

import javax.validation.constraints.NotBlank;

import br.com.bm.entity.ClienteEntity;

public class ClienteRequest {

	@NotBlank
	private String nome;

	@NotBlank
	private String socialSecNumber;

	private String tel;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSocialSecNumber() {
		return socialSecNumber;
	}

	public void setSocialSecNumber(String socialSecNumber) {
		this.socialSecNumber = socialSecNumber;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public ClienteEntity toModel() {

		ClienteEntity cliente = new ClienteEntity(this.nome, this.socialSecNumber);

		if (this.tel != null && !this.tel.isEmpty()) {
			
			cliente.setTel(this.tel);
			
		}

		return cliente;
		
	}

}
