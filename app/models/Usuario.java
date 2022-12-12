package models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

	@Required
	@Unique
	public String nome;

	@Email(message = "O e-mail deve estar no formato correto.")
	@Unique
	public String email;

	@Required
	@Unique
	@Min(value = 8)
	public String matricula;
	
	@Required
	@Password
	@Equals("confirmaSenha")
	public String senha;

	@Transient
	public String confirmaSenha;

	@Enumerated(EnumType.STRING)
	public Funcao funcao;
	
	@Enumerated(EnumType.STRING)
	public statusUsuario status;

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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public Funcao getFuncao() {
		return funcao;
	}

	public void setFuncao(Funcao funcao) {
		this.funcao = funcao;
	}
	
	
}
