package security;

import controllers.Logins;
import controllers.Usuarios;
import models.Funcao;
import play.mvc.Before;
import play.mvc.Controller;

public class Seguranca extends Controller {
	
	@Before
	static void autenticar() {
		if(session.get("usuario") == null) {
			flash.error("É necessário se autenticar no sistema!");
			Logins.login();
		}
	}
	
	@Before
	static void verificarCadastro() {
		if(session.get("usuario") == null) {
			flash.error("É necessário se autenticar no sistema!");
			Usuarios.cadastrar();
		}
	}
	
	@Before
	static void verificarAdiministrador() {
		String funcao = session.get("funcao");
		Adiministrador admAnotacao = getActionAnnotation(Adiministrador.class);
		if(admAnotacao != null && !Funcao.admin.name().equals(funcao)) {
			forbidden("Acesso restrito para os Adimins!");
		}
	}
}
