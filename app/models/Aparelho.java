package models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import play.data.validation.Required;
import play.data.validation.Unique;
import play.db.jpa.Model;

@Entity
public class Aparelho extends Model {

	@Required(message = "Este campo é obrigatório")
	@Unique(message = "Este nome de aparelho já está em uso")
	public String nome;

	@Required(message = "Este campo é obrigatório")
	@Unique(message = "Este endereço IP já está em uso")
	public String enderecoIp;

	@Required(message = "Este campo é obrigatório")
	public String enderecoMac;

	@Required(message = "Este campo é obrigatório")
	public String local;

	@Required(message = "Este campo é obrigatório")
	public String codIrLigar;

	@Required(message = "Este campo é obrigatório")
	public String codIrDesligar;

	public Aparelho() {
		status = status.ATIVO;
	}

	@Enumerated(EnumType.STRING)
	public Status status;
}
