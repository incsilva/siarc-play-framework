package models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import play.data.validation.IPv4Address;
import play.data.validation.Required;
import play.data.validation.Unique;
import play.db.jpa.Model;

@Entity
public class Aparelho extends Model {

	@Required
	@Unique
	public String nome;

	@Required
	@IPv4Address
	@Unique
	public String enderecoIp;

	@Required
	public String enderecoMac;

	@Required
	public String local;

	@Required
	public String codIrLigar;

	@Required
	public String codIrDesligar;

	public Aparelho() {
		status = status.ligado;
	}

	@Enumerated(EnumType.STRING)
	public Status status;
}
