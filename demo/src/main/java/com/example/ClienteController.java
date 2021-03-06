package com.example;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/admin/clientes")
public class ClienteController {

	@Autowired
	ClienteService clienteservice;

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Cliente> cadastrarCliente(@RequestBody Cliente cliente) {
		Cliente c = clienteservice.cadastrar(cliente);
		return new ResponseEntity<Cliente>(c, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Collection<Cliente>> listarClientes() {
		return new ResponseEntity<>(clienteservice.buscartodos(), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "{id}")
	public ResponseEntity<Cliente> excluirCliente(@PathVariable Integer id) {
		Cliente c = clienteservice.buscarPorId(id);
		if (c == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		clienteservice.excluir(c);
		return new ResponseEntity<>(HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.GET, value = "{id}")
	public ResponseEntity<Cliente> listarClientes(@PathVariable Integer id) {

		Cliente c = clienteservice.buscarPorId(id);
		if (c != null) {
			return new ResponseEntity<>(c, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Cliente> alterarCliente(@RequestBody Cliente cliente) {

		Cliente c = clienteservice.alterar(cliente);
		if (c != null) {
			return new ResponseEntity<>(c, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
