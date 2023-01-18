package job;

import models.DescricaoUsuario;
import models.Funcao;
import models.Usuario;
import play.jobs.Job;
import play.jobs.OnApplicationStart;
import play.libs.Crypto;

@OnApplicationStart
public class Initialize extends Job {

	@Override
	public void doJob() throws Exception {
		if (Usuario.count() == 0) {
			Usuario usuario1 = new Usuario();
			usuario1.nome = "João";
			usuario1.sobrenome = "Palhares";
			usuario1.email = "joao12@gmail.com";
			usuario1.senha = Crypto.passwordHash("info2019");
			usuario1.matricula = "201910640";
			usuario1.funcao = Funcao.admin;
			usuario1.save();
		}

		if (DescricaoUsuario.count() == 0) {
			DescricaoUsuario desc0 = new DescricaoUsuario();
			desc0.descricao = "não identificado";
			desc0.save();

			DescricaoUsuario desc1 = new DescricaoUsuario();
			desc1.descricao = "Servidor do IFRN";
			desc1.save();

			DescricaoUsuario desc2 = new DescricaoUsuario();
			desc2.descricao = "Aluno do IFRN";
			desc2.save();

			DescricaoUsuario desc3 = new DescricaoUsuario();
			desc3.descricao = "Terceirizado";
			desc3.save();
			
			DescricaoUsuario desc4 = new DescricaoUsuario();
			desc4.descricao = "Bolsista";
			desc4.save();
		}
	}
}
