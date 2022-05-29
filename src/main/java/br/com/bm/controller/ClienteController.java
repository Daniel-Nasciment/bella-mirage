package br.com.bm.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bm.dto.request.ClienteRequest;
import br.com.bm.dto.response.ClienteResponse;
import br.com.bm.dto.response.ListClientResponse;
import br.com.bm.service.ClienteService;

@RestController
@RequestMapping(value = "/v1/cliente")
public class ClienteController {
	
	private final Logger logger = LoggerFactory.getLogger(ClienteController.class);
	
	@Autowired
	private ClienteService clienteService;
	
	
	@PostMapping(value = "/saveClient")
	public ResponseEntity<?> saveClient(@RequestBody @Valid ClienteRequest request) {

		logger.info("Entrando no endpoint saveClient");
		
		clienteService.saveClient(request);
		
		
		logger.info("Saindo do endpoint saveClient");
		return ResponseEntity.ok().build();
	}

	
	
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
	
	@GetMapping(value = "/findAll")
	public ResponseEntity<ListClientResponse> findAll() {
		
		ListClientResponse response = clienteService.findAll();
		
		if(response.isError()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(response);
	
	}

	
	@PutMapping(value = "/updateClient/{ssn}")
	public ResponseEntity<ClienteResponse> updateClient(@PathVariable String ssn, @RequestBody ClienteRequest request) {
		
		logger.info("Entrando no endpoint updateClient");
		
		ClienteResponse updatedClient = clienteService.updateClient(ssn, request);
		
		if(updatedClient == null) {
			return ResponseEntity.notFound().build();
		}
		
		logger.info("Saindo do endpoint updateClient");
		
		return ResponseEntity.ok(updatedClient);
	}



}
