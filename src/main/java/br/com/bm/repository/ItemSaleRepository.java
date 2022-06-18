package br.com.bm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.bm.entity.ItemSaleEntity;

@Repository
public interface ItemSaleRepository extends JpaRepository<ItemSaleEntity, Long> {



}
