package com.example;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository clienteRepository;

	Map<Integer, Cliente> clientes = new HashMap<>();
	Integer proximoId = 1;

	public Cliente cadastrar(Cliente cliente) {
		return clienteRepository.save(cliente);

	}

	public Collection<Cliente> buscartodos() {
		return clienteRepository.findAll();
	}

	public void excluir(Cliente cliente) {
		clienteRepository.delete(cliente);
		;
	}

	public Cliente buscarPorId(Integer id) {
		return clienteRepository.findOne(id);
	}

	public Cliente alterar(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

}
