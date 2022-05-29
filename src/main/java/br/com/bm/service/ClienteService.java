package br.com.bm.service;

import br.com.bm.dto.request.ClienteRequest;
import br.com.bm.dto.response.ClienteResponse;
import br.com.bm.dto.response.ListClientResponse;

public interface ClienteService {

	public ListClientResponse findByNameOrSsn(String filter);

	public void saveClient(ClienteRequest request);

	public ClienteResponse updateClient(String ssn, ClienteRequest request);

	public ListClientResponse findAll();

}
