package br.com.bm.controller;

import java.net.URI;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.bm.dto.request.SaleRequest;
import br.com.bm.dto.response.SaleResponse;
import br.com.bm.service.SaleService;

@RestController
@RequestMapping(value = "/v1/sales")
public class SaleController {
	
	private final Logger logger = LoggerFactory.getLogger(SaleController.class);
	
	
	@Autowired
	private SaleService saleService;
	
	@PostMapping(value = "/newSale")
	public ResponseEntity<Void> newSale(@RequestBody @Valid SaleRequest request) {
		
		logger.info("Entrando no endpoint newSale...");

		SaleResponse newSaleResponse = saleService.newSale(request);
		
		if(newSaleResponse == null) {
			logger.info("Ocorreu algum problema durante o cliente para criar um novo pedido de venda!");
			return ResponseEntity.badRequest().build();
		}
		
		logger.info("Criando nova URI para acessar pedido de venda criado...");
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}").buildAndExpand(newSaleResponse.getId()).toUri();
		
		
		logger.info("Saindo do endpoint newSale...");
		return ResponseEntity.created(uri).build();
	}


}
