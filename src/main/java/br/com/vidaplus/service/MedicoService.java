package br.com.vidaplus.service;

import br.com.vidaplus.dao.MedicoDao;
import br.com.vidaplus.model.Medico;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.Tuple;

@RequestScoped
public class MedicoService {
	
	@Inject
	MedicoDao medicoDao;

	public void cadastrar(Medico medico) {	
		medicoDao.cadastrar(medico);	
	}

	public Medico visualizar(Integer id) {
		Tuple tuple = medicoDao.visualizar(id);
		Medico medico = new Medico();
		medico.setId((Integer) tuple.get("ID"));
		medico.setNome((String) tuple.get("NOME"));
		medico.setEspecialidade((String) tuple.get("ESPECIALIDADE"));
		medico.setTelefone((String) tuple.get("TELEFONE"));
		medico.setEmail((String) tuple.get("EMAIL"));
		return medico;
	}

	public void alterar(Medico medico) {
		medicoDao.alterar(medico);
	}

	public void excluir(Integer id) {
		medicoDao.excluir(id);
	}

}
