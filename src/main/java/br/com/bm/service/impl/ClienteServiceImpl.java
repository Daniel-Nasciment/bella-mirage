package br.com.bm.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.bm.dto.ClienteRequest;
import br.com.bm.dto.ClienteResponse;
import br.com.bm.dto.EnderecoRequest;
import br.com.bm.dto.EnderecoResponse;
import br.com.bm.dto.ListClientResponse;
import br.com.bm.dto.TelefoneDTO;
import br.com.bm.entity.ClienteEntity;
import br.com.bm.entity.EnderecoEntity;
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

			logger.info("Buscando clientes a partir do nome ou social security number");
			clientes.forEach(c -> {

				ClienteResponse cliente = new ClienteResponse();

				if (c.getEndereco() != null && (c.getNome() != null || c.getSocialSecNumber() != null)) {

					EnderecoEntity endereco = c.getEndereco();

					cliente.setNome(c.getNome());
					cliente.setSocialSecNumber(c.getSocialSecNumber());
					cliente.setPhone1(c.getTelefones().getPhone1());
					cliente.setPhone2(c.getTelefones().getPhone2());
					cliente.setEndereco(new EnderecoResponse(endereco.getRua(), endereco.getBairro(),
							endereco.getNumero(), endereco.getCidade(), endereco.getEstado(), endereco.getCep()));

					clientesEncontrados.add(cliente);

				}

			});

		} catch (Exception e) {

			logger.info("Ocorreu algum problema ao buscar os clientes na base...");
			clientesEncontrados.setError(true);
			clientesEncontrados.setMessage("Ocorreu algum problema ao buscar os clientes na base...");

		}

		logger.info("Saindo do serviço e método findByNameOrSsn");
		return clientesEncontrados;
	}

	@Override
	public void saveClient(ClienteRequest request) {

		logger.info("Entrando serviço e método saveClient");

		ClienteEntity cliente = request.toModel();

		clienteRepository.save(cliente);

		logger.info("Saindo do serviço e método saveClient");

	}

	@Override
	public ClienteResponse updateClient(String ssn, ClienteRequest request) {

		logger.info("Entrando no serviço e método updateClient");

		ClienteResponse response = new ClienteResponse();
		Optional<ClienteEntity> entity = clienteRepository.findBySocialSecNumber(ssn);

		logger.info("Buscando cliente na base....");
		
		if (entity.isPresent()) {

			logger.info("Cliente encontrado na base....");
			
			ClienteEntity cliente = entity.get();

			EnderecoEntity endereco = cliente.getEndereco();
			
			EnderecoRequest enderecoRequest = request.getEndereco();
			
			enderecoRequest.updateEndereco(endereco);

			cliente.setNome(request.getNome());
			cliente.setSocialSecNumber(request.getSocialSecNumber());
			cliente.setTelefones(new TelefoneDTO(request.getPhone1(), request.getPhone2()));
			cliente.setEndereco(endereco);
			
			clienteRepository.save(cliente);
			
			response.toResponse(cliente);
			
		}

		logger.info("Cliente nao encontrado na base....");
		logger.info("Saindo do serviço e método updateClient");

		return null;
	}

}
