package br.com.bm.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.bm.dto.request.ClientRequest;
import br.com.bm.dto.request.AddressRequest;
import br.com.bm.dto.response.ClientResponse;
import br.com.bm.dto.response.AddressResponse;
import br.com.bm.dto.response.ListClientResponse;
import br.com.bm.embeddable.PhoneDto;
import br.com.bm.entity.ClientEntity;
import br.com.bm.entity.AddressEntity;
import br.com.bm.repository.ClientRepository;
import br.com.bm.service.ClientService;
import br.com.bm.specification.ClientSpecification;

@Service
public class ClientServiceImpl implements ClientService {

	private final Logger logger = LoggerFactory.getLogger(ClientServiceImpl.class);

	@Autowired
	private ClientRepository clientRepository;

	@Override
	public ListClientResponse findByNameOrSsn(String filter) {

		logger.info("Entrando serviço e método findByNameOrSsn");

		ListClientResponse clientsFound = new ListClientResponse();

		try {
			Iterable<ClientEntity> clients = clientRepository.findAll(
					Specification.where(ClientSpecification.name(filter).or(ClientSpecification.ssn(filter))));

			logger.info("Buscando clientes a partir do nome ou social security number");
			clients.forEach(c -> {

				ClientResponse client = new ClientResponse();

				if (c.getAddress() != null && (c.getName() != null || c.getSocialSecNumber() != null)) {

					AddressEntity address = c.getAddress();

					client.setName(c.getName());
					client.setSocialSecNumber(c.getSocialSecNumber());
					client.setPhone1(c.getPhones().getPhone1());
					client.setPhone2(c.getPhones().getPhone2());
					client.setAddress(new AddressResponse(address.getRoad(), address.getDistrict(),
							address.getNumber(), address.getCity(), address.getState(), address.getPostCode()));

					clientsFound.add(client);

				}

			});

		} catch (Exception e) {

			logger.info("Ocorreu algum problema ao buscar os clientes na base...");
			clientsFound.setError(true);
			clientsFound.setMessage("Ocorreu algum problema ao buscar os clientes na base...");

		}

		logger.info("Saindo do serviço e método findByNameOrSsn");
		return clientsFound;
	}

	@Override
	public void saveClient(ClientRequest request) {

		logger.info("Entrando serviço e método saveClient");

		ClientEntity client = request.toModel();

		clientRepository.save(client);

		logger.info("Saindo do serviço e método saveClient");

	}

	@Override
	public ClientResponse updateClient(String ssn, ClientRequest request) {

		logger.info("Entrando no serviço e método updateClient");

		ClientResponse response = new ClientResponse();
		Optional<ClientEntity> entity = clientRepository.findBySocialSecNumber(ssn);

		logger.info("Buscando cliente na base....");

		if (entity.isPresent()) {

			logger.info("Cliente encontrado na base....");

			ClientEntity client = entity.get();

			AddressEntity address = client.getAddress();

			AddressRequest addressRequest = request.getAddress();

			addressRequest.updateEndereco(address);

			client.setName(request.getName());
			client.setSocialSecNumber(request.getSocialSecNumber());
			client.setPhones(new PhoneDto(request.getPhone1(), request.getPhone2()));
			client.setAddress(address);

			clientRepository.save(client);

			response.toResponse(client);

		}

		logger.info("Cliente nao encontrado na base....");
		logger.info("Saindo do serviço e método updateClient");

		return null;
	}

	@Override
	public ListClientResponse findAll() {

		logger.info("Entrando no serviço e método findAll");

		ListClientResponse responseList = new ListClientResponse();

		try {
			logger.info("Recuperando clientes da base...");
			List<ClientEntity> clients = clientRepository.findAll();

			clients.forEach(c -> {
				ClientResponse responseClient = new ClientResponse();
				responseClient.toResponse(c);
				responseList.add(responseClient);
			});
		} catch (Exception e) {
			
			responseList.setError(true);
			responseList.setMessage("Erro ao recuperar clientes da base...");
			logger.info("Erro ao recuperar clientes da base!!");
			
		}

		logger.info("Saindo do serviço e método findAll");

		return responseList;
	}

	@Override
	public boolean deleteBySsn(String ssn) {

		logger.info("Entrando no serviço e método deleteBySsn");
		
		Optional<ClientEntity> client = clientRepository.findBySocialSecNumber(ssn);
		
		
		if(client.isPresent()) {
			
			logger.info("Cliente localizado!");
			
			clientRepository.deleteById(client.get().getId());
			return true;
		}
		
		logger.info("Saindo do serviço e método deleteBySsn");
		
		return false;
	}

}
