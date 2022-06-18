package br.com.bm.specification;

import org.springframework.data.jpa.domain.Specification;

import br.com.bm.entity.ClientEntity;

public class ClientSpecification {

	
	public static Specification<ClientEntity> name(String name){
		return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), "%" + name + "%");
	}
	
	public static Specification<ClientEntity> ssn(String ssn){
		return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("socialSecNumber"), "%" + ssn + "%");
	}
	
}
