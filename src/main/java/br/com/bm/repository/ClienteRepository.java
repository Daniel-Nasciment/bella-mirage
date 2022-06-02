package br.com.bm.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import br.com.bm.entity.ClienteEntity;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Long>, JpaSpecificationExecutor<ClienteEntity> {

	public Optional<ClienteEntity> findBySocialSecNumber(String ssn);


}
