package br.com.vidaplus.service;

import br.com.vidaplus.dao.PacienteDao;
import br.com.vidaplus.model.Paciente;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.Tuple;

@RequestScoped
public class PacienteService {
	
	@Inject
	PacienteDao pacienteDao;

	public void cadastrar(Paciente paciente) {	
		pacienteDao.cadastrar(paciente);	
	}

	public Paciente visualizar(Integer id) {
		Tuple tuple = pacienteDao.visualizar(id);
		Paciente paciente = new Paciente();
		paciente.setId((Integer) tuple.get("ID"));
		paciente.setNome((String) tuple.get("NOME"));
		paciente.setDataNascimento((String) tuple.get("DATANASCIMENTO"));
		paciente.setEndereço((String) tuple.get("ENDERECO"));
		paciente.setTelefone((String) tuple.get("TELEFONE"));
		paciente.setEmail((String) tuple.get("EMAIL"));
		return paciente;
	}

	public void alterar(Paciente paciente) {
		pacienteDao.alterar(paciente);
	}
	
	public void excluir(Integer id) {
		pacienteDao.excluir(id);
	}

}
