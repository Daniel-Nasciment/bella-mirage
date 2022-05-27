package br.com.bm.dto.response;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListClientResponse {

	List<ClienteResponse> clientes = new ArrayList<ClienteResponse>();
	private String message;
	private boolean error = false;

	public List<ClienteResponse> getClientes() {
		return Collections.unmodifiableList(this.clientes);
	}

	public void add(ClienteResponse cliente) {
		this.clientes.add(cliente);
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
