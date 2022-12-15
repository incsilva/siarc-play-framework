package controllers;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import models.Funcao;
import models.Status;
import models.Usuario;
import models.statusUsuario;
import play.data.validation.Valid;
import play.libs.Crypto;
import play.mvc.Controller;
import play.mvc.With;
import security.Adiministrador;
import security.Seguranca;

@With(Seguranca.class)
public class Usuarios extends Controller {

	public static void cadastrar() {
		List<Funcao> funcoes = Arrays.asList(Funcao.values());
		render(funcoes);
	}

	public static void menu() {
		render();
	}

	public static void salvar(@Valid Usuario user, String senha) {

		long quantidade = Usuario.count("matricula = ?1 and status = ?2", user.matricula, statusUsuario.ativo);

		if (quantidade == 0) {
			if (validation.hasErrors()) {
				params.flash();
				validation.keep();
				flash.error("Algum problema foi detectado...");
				cadastrar();
			}
			senha = user.senha;
			user.senha = Crypto.passwordHash(senha);
			user.save();
			flash.success("O cadastro foi um sucesso!");
		} 
		cadastrar();
	}

	@Adiministrador
	public static void listar() {
		String termo = params.get("termo");

		List<Usuario> usuarios = Collections.EMPTY_LIST;
		if (termo == null || termo.isEmpty()) {
			usuarios = Usuario.find("status = ?1", statusUsuario.ativo).fetch();
		} else {
			usuarios = Usuario.find("(lower(nome) like ?1 OR lower(sobrenome) like ?2 OR lower(email) like ?3 OR matricula = ?4) AND status = ?5",
					"%" + termo.toLowerCase() + "%",
					"%" + termo.toLowerCase() + "%",
					"%" + termo.toLowerCase() + "%",
					"%" + termo.toLowerCase() + "%",
					statusUsuario.ativo).fetch();
		}
		render(usuarios, termo);
	}

	public static void editar(Long id) {
		Usuario user = Usuario.findById(id);
		List<Funcao> funcoes = Arrays.asList(Funcao.values());
		renderTemplate("Usuarios/cadastrar.html", user, funcoes);
	}

	public static void remover(Long id) {
		Usuario user = Usuario.findById(id);
		user.inativar();
		user.save();
	}

	public static void detalhar(Long id) {
		Usuario user = Usuario.findById(id);
		render(user);
	}
}