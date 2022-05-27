package br.com.bm.entity;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.bm.dto.TelefoneDTO;

@Entity
@Table(name = "CLIENTES")
public class ClienteEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;

	private String socialSecNumber;

	@Embedded
	private TelefoneDTO telefones;

	@OneToOne(cascade = CascadeType.ALL)
	private EnderecoEntity endereco;

	@Deprecated
	public ClienteEntity() {
	}

	public ClienteEntity(String nome, String socialSecNumber) {
		this.nome = nome;
		this.socialSecNumber = socialSecNumber;
	}

	public Long getId() {
		return id;
	}

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

	public TelefoneDTO getTelefones() {
		return telefones;
	}

	public void setTelefones(TelefoneDTO telefones) {
		this.telefones = telefones;
	}

	public EnderecoEntity getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoEntity endereco) {
		this.endereco = endereco;
	}

	@Override
	public String toString() {
		return "ClienteEntity [nome=" + nome + ", socialSecNumber=" + socialSecNumber + ", telefones=" + telefones
				+ "]";
	}

}
