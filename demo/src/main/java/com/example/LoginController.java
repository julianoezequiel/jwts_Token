package com.example;

import java.security.Key;
import java.util.Date;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

@RestController
public class LoginController {

	@Autowired
	UsuarioService usuarioService;

	@RequestMapping(value = "/autenticar", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
	public LoginResponse autenticar(@RequestBody Usuario usuario) throws ServletException {

		if (usuario.getNome() == null || usuario.getSenha() == null) {
			throw new ServletException("Nome e senha obrigatórios");
		}

		Usuario u = usuarioService.buscarPorNome(usuario);

		if (u == null) {
			throw new ServletException("Usuário não encontrado");
		}

		if (!u.getSenha().equals(usuario.getSenha())) {
			throw new ServletException("Usuário ou senha inválido");
		}
		
		// TOKEN
		Key key = MacProvider.generateKey();
		String token = Jwts.builder().setSubject(u.getNome()).signWith(SignatureAlgorithm.HS512, "maysa")
				.setExpiration(new Date(System.currentTimeMillis() + 60 * 1000)).compact();

		return new LoginResponse(token);
	}

	private class LoginResponse {

		private String token;

		public LoginResponse(String token) {
			this.token = token;
		}

		public String getToken() {
			return token;
		}

	}

}
