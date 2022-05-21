package br.com.bm.service;

import br.com.bm.dto.ClienteRequest;
import br.com.bm.dto.ListClientResponse;

public interface ClienteService {

	public ListClientResponse findByNameOrSsn(String filter);

	public void saveClient(ClienteRequest request);

}
