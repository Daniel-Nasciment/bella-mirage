package br.com.bm.service;

import br.com.bm.dto.request.ClientRequest;
import br.com.bm.dto.response.ClientResponse;
import br.com.bm.dto.response.ListClientResponse;

public interface ClientService {

	public ListClientResponse findByNameOrSsn(String filter);

	public void saveClient(ClientRequest request);

	public ClientResponse updateClient(String ssn, ClientRequest request);

	public ListClientResponse findAll();

	public boolean deleteBySsn(String ssn);

}
