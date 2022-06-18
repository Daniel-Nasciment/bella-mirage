package br.com.bm.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bm.dto.request.SaleRequest;
import br.com.bm.service.SaleService;

@RestController
@RequestMapping(value = "/v1/sales")
public class SaleController {
	
	private final Logger logger = LoggerFactory.getLogger(SaleController.class);
	
	
	@Autowired
	private SaleService saleService;
	
	@PostMapping(value = "/newSale")
	public ResponseEntity<?> newRequest(@RequestBody @Valid SaleRequest request) {

		
		saleService.newSale(request);
		
		return null;
	}


}
