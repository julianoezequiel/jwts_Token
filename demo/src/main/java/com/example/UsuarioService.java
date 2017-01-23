package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

	@Autowired
	UsuarioRepository usuarioRepositoty;
	
	public Usuario cadastrar(Usuario usuario){
		return usuarioRepositoty.save(usuario);
	}

	
	public Usuario buscarPorNome(Usuario usuario){
		return usuarioRepositoty.buscarPorNome(usuario.getNome());
	}
}
