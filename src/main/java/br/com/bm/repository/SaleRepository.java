package br.com.bm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.bm.entity.SaleEntity;

@Repository
public interface SaleRepository extends JpaRepository<SaleEntity, Long> {



}
