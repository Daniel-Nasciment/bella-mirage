package br.com.bm.service;

import br.com.bm.dto.ListClientResponse;

public interface ClienteService {

	public ListClientResponse findByNameOrSsn(String filter);

}
