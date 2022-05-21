package br.com.bm.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bm.dto.ListClientResponse;
import br.com.bm.service.ClienteService;

@RestController
@RequestMapping(value = "/v1/cliente")
public class ClienteController {
	
	private final Logger logger = LoggerFactory.getLogger(ClienteController.class);
	
	@Autowired
	private ClienteService clienteService;
	
	@GetMapping(value = "/findByNameOrSsn/{filter}")
	public ResponseEntity<ListClientResponse> findByNameOrSsn(@PathVariable String filter) {
		
		logger.info("Entrando no endpoint findByNameOrSsn");
		
		ListClientResponse clienteEncontrados = clienteService.findByNameOrSsn(filter);
		
		if (clienteEncontrados.getClientes().isEmpty()) {
			logger.info("Nenhum Cliente foi encontrado!");
			return ResponseEntity.notFound().build();
		}
		
		logger.info("Saindo do endpoint findByNameOrSsn");
		return ResponseEntity.ok(clienteEncontrados);
	}


}
