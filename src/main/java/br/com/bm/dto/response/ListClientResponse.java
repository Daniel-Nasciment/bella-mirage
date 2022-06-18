package br.com.bm.dto.response;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListClientResponse {

	List<ClientResponse> clients = new ArrayList<ClientResponse>();
	private String message;
	private boolean error = false;

	public List<ClientResponse> getClients() {
		return Collections.unmodifiableList(this.clients);
	}

	public void add(ClientResponse client) {
		this.clients.add(client);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

}
