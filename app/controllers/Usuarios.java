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

	public static void salvar(@Valid Usuario user) {

		long quantidade = Usuario.count("matricula = ?1 and status = ?2", user.matricula, statusUsuario.ativo);

		if (quantidade == 0) {
			if (validation.hasErrors()) {
				validation.keep();
				cadastrar();
			}
			user.save();
			flash.success("O cadastro foi um sucesso!");
		} else {
			flash.error("Essa matrícula já existe no sistema, tente outra!");
			cadastrar();
		}
		Home.home();
	}

	@Adiministrador
	public static void listar() {
		String termo = params.get("termo");

		List<Usuario> usuarios = Collections.EMPTY_LIST;
		if (usuarios == null || usuarios.isEmpty()) {
			usuarios = Usuario.find("status = ?1", statusUsuario.ativo).fetch();
		} else {
			usuarios = Usuario.find("(nome like ?1 OR email like ?2 OR matricula like ?3) AND status = ?4 ",
					"%" + termo.toLowerCase() + "%", "%" + termo.toLowerCase() + "%", "%" + termo.toLowerCase() + "%",
					statusUsuario.ativo).fetch();
		}
		render(usuarios, termo);
	}

	public static void editar(Long id) {
		Usuario usuario = Usuario.findById(id);
		List<Funcao> funcoes = Arrays.asList(Funcao.values());
		renderTemplate("Usuarios/cadastrar.html", usuario, funcoes);
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