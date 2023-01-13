package controllers;

import models.Usuario;
import play.libs.Crypto;
import play.mvc.Controller;

public class Logins extends Controller {
	
	public static void login() {
		render();
	}
	
	public static void loginSecurity(String matricula, String senha) {
		Usuario usuario = Usuario.find("matricula = ?1 AND senha = ?2", matricula, Crypto.passwordHash(senha)).first();
		
		if(usuario == null) {
			flash.error("Matrícula ou senha inválidas!");
			login();
		} else {
			session.put("usuario", usuario.nome);
			session.put("funcao", usuario.funcao);
			session.put("adm", usuario.funcao.admin);
			flash.success("Você logou no sistema!");
			Home.home();
		}
		render();
	}
	
	public static void logout() {
		session.clear();
		flash.success("Você saiu do sistema!");
		login();
	}
}
