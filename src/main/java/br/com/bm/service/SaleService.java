package br.com.bm.service;

import br.com.bm.dto.request.SaleRequest;
import br.com.bm.dto.response.SaleResponse;

public interface SaleService {
	
	public SaleResponse newSale(SaleRequest request);

}
