package models;

import org.hibernate.validator.constraints.NotBlank;

public class UsuarioDto {
	public String nome;
	public String email;
	public String matricula;
	@NotBlank
	public Funcao funcao;

	public UsuarioDto() {
	}

	public UsuarioDto(Usuario usuario) {
		this.nome = usuario.getNome();
		this.email = usuario.getEmail();
		this.matricula = usuario.getMatricula();
		this.funcao = usuario.getFuncao();
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
