package controllers;

import java.util.Collections;
import java.util.List;

import models.Aparelho;
import models.Status;
import play.mvc.Controller;
import play.mvc.With;
import security.Seguranca;

public class Aparelhos extends Controller {

	public static void form() {
		render();
	}

	public static void menu() {
		render();
	}

	public static void salvar(Aparelho aparelho) {
		long qtd = Aparelho.count("nome = ?1", aparelho.nome);
		if (qtd == 0) {
			aparelho.save();
			flash.success("Aparelho cadastrado com sucesso.");
		} else {
			flash.error("O Aparelho informado já existe");
		}
		form();
	}

	public static void editar(Long id) {
		Aparelho aparelho = Aparelho.findById(id);
		renderTemplate("Aparelhos/form.html", aparelho);
	}

	public static void remover(Long id) {
		Aparelho ap = Aparelho.findById(id);
		ap.delete();
		flash.success("Aparelho removido com sucesso!");
		listar();
	}

	public static void listar() {
		String termo = params.get("termo");

		List<Aparelho> aparelhos = Collections.EMPTY_LIST;
		if (termo == null || termo.isEmpty()) {
			aparelhos = Aparelho.findAll();
		} else {
			aparelhos = Aparelho.find(
					"(lower(nome) like ?1 OR lower(enderecoIp) like ?2 OR lower(enderecoMac) like ?3 OR lower(local) like ?4)",
					"%" + termo.toLowerCase() + "%", "%" + termo.toLowerCase() + "%", "%" + termo.toLowerCase() + "%",
					"%" + termo.toLowerCase() + "%").fetch();
		}
		render(aparelhos, termo);
	}
}
