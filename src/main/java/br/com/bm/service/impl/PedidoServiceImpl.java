package br.com.bm.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bm.dto.request.ItemPedidoRequest;
import br.com.bm.dto.request.PedidoRequest;
import br.com.bm.entity.ClienteEntity;
import br.com.bm.entity.ItemPedidoEntity;
import br.com.bm.entity.PedidoEntity;
import br.com.bm.repository.ClienteRepository;
import br.com.bm.repository.ItemPedidoRepository;
import br.com.bm.repository.PedidoRepository;
import br.com.bm.service.PedidoService;

@Service
public class PedidoServiceImpl implements PedidoService{
	
	private final Logger logger = LoggerFactory.getLogger(PedidoServiceImpl.class);
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	@Override
	public void newRequest(PedidoRequest request) {

		logger.info("Entrando no serviço e método newRequest");
		
		List<ItemPedidoRequest> itensRequest = request.getItens();
		
		logger.info("Buscando cliente a partir do social security number...");
		Optional<ClienteEntity> cliente = clienteRepository.findBySocialSecNumber(request.getSocialSecNumber());
		
		if (cliente.isPresent()) {
			
			logger.info("Cliente encontrado!!!");
			
			PedidoEntity pedido = new PedidoEntity(cliente.get());
			
			itensRequest.forEach(i -> {
				
				logger.info("Criando item " + i.getDescricao() + " a partir da request");
				
				ItemPedidoEntity item = new ItemPedidoEntity(i.getValorUnitario(), i.getQuantidade(), i.getDescricao(), pedido);
				
				itemPedidoRepository.save(item);
				
				pedido.adicionaItem(item);
				
			});
			
			pedidoRepository.save(pedido);
			
		}
		
		
		
		
		
	}

}
