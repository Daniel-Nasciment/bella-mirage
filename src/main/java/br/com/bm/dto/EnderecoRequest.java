package br.com.bm.dto;

import javax.validation.constraints.NotBlank;

import com.sun.istack.NotNull;

import br.com.bm.entity.EnderecoEntity;

public class EnderecoRequest {

	@NotBlank
	private String rua;

	@NotBlank
	private String bairro;

	@NotNull
	private Integer numero;

	@NotBlank
	private String cidade;

	@NotBlank
	private String estado;

	@NotBlank
	private String cep;

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public EnderecoEntity toModel() {

		return new EnderecoEntity(this.rua, this.bairro, this.numero, this.cidade, this.estado, this.cep);

	}

	public EnderecoEntity updateEndereco(EnderecoEntity endereco) {

		endereco.setRua(this.rua);
		endereco.setBairro(this.bairro);
		endereco.setNumero(this.numero);
		endereco.setCidade(this.cidade);
		endereco.setEstado(this.estado);
		endereco.setCep(this.cep);

		return endereco;
	}

}
