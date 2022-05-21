package br.com.bm.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.bm.dto.ClienteResponse;
import br.com.bm.dto.ListClientResponse;
import br.com.bm.entity.ClienteEntity;
import br.com.bm.repository.ClienteRepository;
import br.com.bm.service.ClienteService;
import br.com.bm.specification.ClienteSpecification;

@Service
public class ClienteServiceImpl implements ClienteService {

	private final Logger logger = LoggerFactory.getLogger(ClienteServiceImpl.class);

	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public ListClientResponse findByNameOrSsn(String filter) {

		logger.info("Entrando serviço e método findByNameOrSsn");

		ListClientResponse clientesEncontrados = new ListClientResponse();

		try {
			Iterable<ClienteEntity> clientes = clienteRepository.findAll(
					Specification.where(ClienteSpecification.nome(filter).or(ClienteSpecification.ssn(filter))));

			clientes.forEach(c -> {

				ClienteResponse cliente = new ClienteResponse();

				if (c.getNome() != null || c.getSocialSecNumber() != null) {

					cliente.setNome(c.getNome());
					cliente.setSocialSecNumber(c.getSocialSecNumber());
					cliente.setTel(c.getTel());

					clientesEncontrados.add(cliente);

				}

			});
			
		} catch (Exception e) {

			logger.info("Ocorreu algum problema ao buscar os clientes na base...");
			clientesEncontrados.setError(true);
			clientesEncontrados.setMessage("Ocorreu algum problema ao buscar os clientes na base...");
			
		}

		return clientesEncontrados;
	}

}
