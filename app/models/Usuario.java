package models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import models.statusUsuario;
import net.sf.oval.constraint.EqualToField;
import play.data.validation.Email;
import play.data.validation.Equals;
import play.data.validation.Min;
import play.data.validation.Password;
import play.data.validation.Required;
import play.data.validation.Unique;
import play.db.jpa.Model;

@Entity
public class Usuario extends Model {

	@Required(message = "Este campo é obrigatório")
	public String nome;

	@Required(message = "Este campo é obrigatório")
	public String sobrenome;

	@Email(message = "O e-mail deve estar no formato correto.")
	@Unique(message = "O e-mail informado já está em uso. Tente outro")
	@Required(message = "Este campo é obrigatório")
	public String email;

	@Required(message = "Este campo é obrigatório")
	@Unique(message = "A matrícula informada já está cadastrada no sistema")
	@Min(value = 8, message = "Sua matricula deve ter no mínimo 8 caracteres")
	public String matricula;

	@Required(message = "Este campo é obrigatório")
	@Password
	@Equals("confirmaSenha")
	public String senha;

	@Transient
	public String confirmaSenha;

	@Enumerated(EnumType.STRING)
	public Funcao funcao;

	@Enumerated(EnumType.STRING)
	public statusUsuario status;

	@ManyToOne
	public DescricaoUsuario descricao;
	
	public Usuario() {
		funcao = funcao.usuario;
		status = statusUsuario.ativo;
	}

	public void ativar() {
		status = statusUsuario.ativo;
	}

	public void inativar() {
		status = statusUsuario.inativo;
	}

}
