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

import br.com.bm.dto.request.PedidoRequest;
import br.com.bm.service.PedidoService;

@RestController
@RequestMapping(value = "/v1/pedidos")
public class PedidoController {
	
	private final Logger logger = LoggerFactory.getLogger(PedidoController.class);
	
	
	@Autowired
	private PedidoService pedidoService;
	
	@PostMapping(value = "/newRequest")
	public ResponseEntity<?> newRequest(@RequestBody @Valid PedidoRequest request) {

		
		pedidoService.newRequest(request);
		
		return null;
	}


}
