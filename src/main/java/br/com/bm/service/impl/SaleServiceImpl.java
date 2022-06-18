package br.com.bm.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bm.dto.request.ItemSaleRequest;
import br.com.bm.dto.request.SaleRequest;
import br.com.bm.entity.ClientEntity;
import br.com.bm.entity.ItemSaleEntity;
import br.com.bm.entity.SaleEntity;
import br.com.bm.repository.ClientRepository;
import br.com.bm.repository.ItemSaleRepository;
import br.com.bm.repository.SaleRepository;
import br.com.bm.service.SaleService;

@Service
public class SaleServiceImpl implements SaleService{
	
	private final Logger logger = LoggerFactory.getLogger(SaleServiceImpl.class);
	
	@Autowired
	private SaleRepository saleRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private ItemSaleRepository itemSaleRepository;

	@Override
	public void newSale(SaleRequest request) {

		logger.info("Entrando no serviço e método newRequest");
		
		List<ItemSaleRequest> itemsRequest = request.getItens();
		
		logger.info("Buscando cliente a partir do social security number...");
		Optional<ClientEntity> client = clientRepository.findBySocialSecNumber(request.getSocialSecNumber());
		
		if (client.isPresent()) {
			
			logger.info("Cliente encontrado!!!");
			
			SaleEntity sale = new SaleEntity(client.get());
			
			itemsRequest.forEach(i -> {
				
				logger.info("Criando item " + i.getDescription() + " a partir da request");
				
				ItemSaleEntity item = new ItemSaleEntity(i.getUnitaryValue(), i.getQuantity(), i.getDescription(), sale);
				
				itemSaleRepository.save(item);
				
				sale.addItem(item);
				
			});
			
			saleRepository.save(sale);
			
		}
		
		
		
		
		
	}

}
