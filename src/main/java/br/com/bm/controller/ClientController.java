package br.com.bm.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bm.dto.request.ClientRequest;
import br.com.bm.dto.response.ClientResponse;
import br.com.bm.dto.response.ListClientResponse;
import br.com.bm.service.ClientService;

@RestController
@RequestMapping(value = "/v1/client")
public class ClientController {
	
	private final Logger logger = LoggerFactory.getLogger(ClientController.class);
	
	@Autowired
	private ClientService clientService;
	
	
	@PostMapping(value = "/saveClient")
	public ResponseEntity<?> saveClient(@RequestBody @Valid ClientRequest request) {

		logger.info("Entrando no endpoint saveClient");
		
		clientService.saveClient(request);
		
		
		logger.info("Saindo do endpoint saveClient");
		return ResponseEntity.ok().build();
	}

	
	
	@GetMapping(value = "/findByNameOrSsn/{filter}")
	public ResponseEntity<ListClientResponse> findByNameOrSsn(@PathVariable String filter) {
		
		logger.info("Entrando no endpoint findByNameOrSsn");
		
		ListClientResponse clientFoud = clientService.findByNameOrSsn(filter);
		
		if (clientFoud.getClients().isEmpty()) {
			logger.info("Nenhum Cliente foi encontrado!");
			return ResponseEntity.notFound().build();
		}
		
		logger.info("Saindo do endpoint findByNameOrSsn");
		return ResponseEntity.ok(clientFoud);
	}
	
	@GetMapping(value = "/findAll")
	public ResponseEntity<ListClientResponse> findAll() {
		
		logger.info("Entrando no endpoint findAll");
		
		ListClientResponse response = clientService.findAll();
		
		if(response.isError()) {
			return ResponseEntity.notFound().build();
		}
		
		logger.info("Saindo do endpoint findAll");
		return ResponseEntity.ok(response);
	
	}

	
	@PutMapping(value = "/updateClient/{ssn}")
	public ResponseEntity<ClientResponse> updateClient(@PathVariable String ssn, @RequestBody ClientRequest request) {
		
		logger.info("Entrando no endpoint updateClient");
		
		ClientResponse updatedClient = clientService.updateClient(ssn, request);
		
		if(updatedClient == null) {
			return ResponseEntity.notFound().build();
		}
		
		logger.info("Saindo do endpoint updateClient");
		
		return ResponseEntity.ok(updatedClient);
	}
	
	@DeleteMapping(value = "deleteClient/{ssn}")
	public ResponseEntity<?> deleteClient(@PathVariable String ssn) {
		
		logger.info("Entrando no endpoint deleteClient");
		
		boolean deleted = clientService.deleteBySsn(ssn);
		
		if(deleted) {
			logger.info("Cliente deletado. Saindo do endpoint deleteClient");
			return ResponseEntity.ok().build();
		}
		
		logger.info("Cliente nao encontrado. Saindo do endpoint deleteClient");
		
		return ResponseEntity.notFound().build();
	}




}
