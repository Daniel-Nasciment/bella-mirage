package br.com.bm.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bm.dto.request.SaleRequest;
import br.com.bm.dto.response.ClientResponse;
import br.com.bm.dto.response.ItemSaleResponse;
import br.com.bm.dto.response.SaleResponse;
import br.com.bm.entity.ClientEntity;
import br.com.bm.entity.ItemSaleEntity;
import br.com.bm.entity.SaleEntity;
import br.com.bm.repository.ClientRepository;
import br.com.bm.repository.ItemSaleRepository;
import br.com.bm.repository.SaleRepository;

@Service
public class SaleService {

	private final Logger logger = LoggerFactory.getLogger(SaleService.class);

	@Autowired
	private SaleRepository saleRepository;

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private ItemSaleRepository itemSaleRepository;

	public SaleResponse newSale(SaleRequest request) {

		logger.info("Entrando no serviço e método newSale");

		logger.info("Buscando cliente a partir do social security number...");
		Optional<ClientEntity> client = clientRepository.findBySocialSecNumber(request.getSocialSecNumber());

		if (!client.isPresent()) {
			logger.info("Cliente nao encontrado!!");
			return null;
		}

		logger.info("Cliente encontrado!!!");
		ClientEntity clientEntity = client.get();

		List<ItemSaleResponse> listItemsResponse = new ArrayList<ItemSaleResponse>();

		SaleEntity sale = new SaleEntity(clientEntity);

		saleRepository.save(sale);
		
		request.getItems().forEach(i -> {

			logger.info("Criando item " + i.getDescription() + " a partir da request");

			ItemSaleEntity item = new ItemSaleEntity(i.getUnitaryValue(), i.getQuantity(), i.getDescription(), sale);
			ItemSaleResponse itemResponse = new ItemSaleResponse(i.getUnitaryValue(), i.getQuantity(),
					i.getDescription());

			itemSaleRepository.save(item);


			sale.addItem(item);
			saleRepository.save(sale);
			listItemsResponse.add(itemResponse);

		});


		return new SaleResponse(sale.getId(), sale.getDate(), new ClientResponse().toResponse(clientEntity),
				sale.getObs(), sale.getTotal(), listItemsResponse);

	}

	public SaleResponse findSaleById(Long id) {

		Optional<SaleEntity> sale = saleRepository.findById(id);
		
		if(!sale.isPresent()) {
			return null;
		}
		
		return new SaleResponse().toResponse(sale.get());
		
	}

}
