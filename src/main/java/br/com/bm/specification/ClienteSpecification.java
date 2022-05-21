package br.com.bm.specification;

import org.springframework.data.jpa.domain.Specification;

import br.com.bm.entity.ClienteEntity;

public class ClienteSpecification {

	
	public static Specification<ClienteEntity> nome(String nome){
		return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("nome"), "%" + nome + "%");
	}
	
	public static Specification<ClienteEntity> ssn(String ssn){
		return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("socialSecNumber"), "%" + ssn + "%");
	}
	
}
