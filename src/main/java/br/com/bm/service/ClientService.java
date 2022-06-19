package br.com.bm.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.bm.dto.request.ClientSaveRequest;
import br.com.bm.dto.request.ClientUpdateRequest;
import br.com.bm.dto.response.ClientResponse;
import br.com.bm.dto.response.ListClientResponse;
import br.com.bm.entity.ClientEntity;
import br.com.bm.repository.ClientRepository;
import br.com.bm.specification.ClientSpecification;

@Service
public class ClientService {

	private final Logger logger = LoggerFactory.getLogger(ClientService.class);

	@Autowired
	private ClientRepository clientRepository;

	public void saveClient(ClientSaveRequest request) {

		logger.info("Entrando serviço saveClient...");

		ClientEntity client = request.toModel();

		clientRepository.save(client);

		logger.info("Saindo do serviço saveClient...");

	}

	public ListClientResponse findByNameOrSsn(String filter) {

		logger.info("Entrando serviço findByNameOrSsn...");

		ListClientResponse clientsFound = new ListClientResponse();

		try {
			Iterable<ClientEntity> clients = clientRepository
					.findAll(Specification.where(ClientSpecification.name(filter).or(ClientSpecification.ssn(filter))));

			logger.info("Buscando clientes a partir do nome ou social security number...");
			clients.forEach(c -> {

				clientsFound.add(new ClientResponse().toResponse(c));

			});

		} catch (Exception e) {

			logger.info("Ocorreu algum problema ao buscar os clientes na base...");
			clientsFound.setError(true);
			clientsFound.setMessage("Ocorreu algum problema ao buscar os clientes na base...");

		}

		logger.info("Saindo do serviço findByNameOrSsn...");
		return clientsFound;
	}

	public ListClientResponse findAll() {

		logger.info("Entrando no serviço findAll");

		ListClientResponse responseList = new ListClientResponse();

		try {
			
			logger.info("Recuperando clientes da base...");
			List<ClientEntity> clients = clientRepository.findAll();

			clients.forEach(c -> {
				responseList.add(new ClientResponse().toResponse(c));
			});
			
		} catch (Exception e) {

			responseList.setError(true);
			responseList.setMessage("Erro ao recuperar clientes da base...");
			logger.info("Erro ao recuperar clientes da base!!");

		}

		logger.info("Saindo do serviço findAll");

		return responseList;
	}

	public ClientResponse updateClient(String ssn, ClientUpdateRequest request) {

		logger.info("Entrando no serviço updateClient");

		Optional<ClientEntity> clientPossible = clientRepository.findBySocialSecNumber(ssn);

		logger.info("Buscando cliente na base....");

		if (clientPossible.isPresent()) {

			logger.info("Cliente encontrado na base....");

			ClientEntity client = request.toModel(clientPossible.get());

			clientRepository.save(client);

			return new ClientResponse().toResponse(client);

		}

		logger.info("Cliente nao encontrado na base....");
		logger.info("Saindo do serviço updateClient");

		return null;
	}

	public boolean deleteBySsn(String ssn) {

		logger.info("Entrando no serviço deleteBySsn");

		Optional<ClientEntity> client = clientRepository.findBySocialSecNumber(ssn);

		if (client.isPresent()) {

			logger.info("Cliente localizado!");

			clientRepository.deleteById(client.get().getId());
			return true;
		}

		logger.info("Saindo do serviço deleteBySsn");

		return false;
	}

}
