package br.com.bm.dto.request;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import br.com.bm.embeddable.TelefoneDTO;
import br.com.bm.entity.ClienteEntity;
import br.com.bm.validator.CPForCNPJ;
import br.com.bm.validator.UniqueValue;

public class ClienteRequest {

	@NotBlank
	private String nome;

	@NotBlank
	@UniqueValue(domainClass = ClienteEntity.class, fieldName = "socialSecNumber")
	@CPForCNPJ
	private String socialSecNumber;

	private String phone1;

	private String phone2;

	@Valid
	private EnderecoRequest endereco;

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

	public EnderecoRequest getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoRequest endereco) {
		this.endereco = endereco;
	}

	
	// TRANSFORMANDO OBJETO DE REQUEST EM UM OBJETO DA ENTIDADE CLIENTE
	public ClienteEntity toModel() {

		ClienteEntity cliente = new ClienteEntity(this.nome, this.socialSecNumber);

		if (this.phone1 != null || this.phone2 != null) {

			TelefoneDTO phones = new TelefoneDTO(this.phone1, this.phone2);

			cliente.setTelefones(phones);

		}
		
		EnderecoRequest endereco = this.endereco;
		
		cliente.setEndereco(endereco.toModel());

		return cliente;

	}

}
