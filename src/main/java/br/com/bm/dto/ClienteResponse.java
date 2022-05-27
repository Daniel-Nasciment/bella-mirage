package br.com.bm.dto;

import br.com.bm.entity.ClienteEntity;
import br.com.bm.entity.EnderecoEntity;

public class ClienteResponse {

	private String nome;

	private String socialSecNumber;

	private String phone1;

	private String phone2;

	private EnderecoResponse endereco;

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

	public EnderecoResponse getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoResponse endereco) {
		this.endereco = endereco;
	}

	public void toResponse(ClienteEntity cliente) {

		EnderecoEntity entity = cliente.getEndereco();

		this.nome = cliente.getNome();
		this.phone1 = cliente.getTelefones().getPhone1();
		this.phone2 = cliente.getTelefones().getPhone2();
		this.socialSecNumber = cliente.getSocialSecNumber();
		this.endereco = new EnderecoResponse(entity.getRua(), entity.getBairro(), entity.getNumero(),
				entity.getCidade(), entity.getEstado(), entity.getCep());

	}

}
