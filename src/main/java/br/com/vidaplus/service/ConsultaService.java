package br.com.vidaplus.service;

import br.com.vidaplus.dao.ConsultaDao;
import br.com.vidaplus.model.Consulta;
import br.com.vidaplus.model.Medico;
import br.com.vidaplus.model.Paciente;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.Tuple;

@RequestScoped
public class ConsultaService {

	@Inject
	PacienteService pacienteService;

	@Inject
	MedicoService medicoService;

	@Inject
	ConsultaDao consultaDao;

	public void cadastrar(Consulta consulta) {

		Paciente paciente = pacienteService.visualizar(consulta.getPacienteId());
		consulta.setPacienteNome(paciente.getNome());

		Medico medico = medicoService.visualizar(consulta.getMedicoId());
		consulta.setMedicoNome(medico.getNome());

		consultaDao.cadastrar(consulta);

	}

	public Consulta visualizar(Integer id) {
		Tuple tuple = consultaDao.visualizar(id);
		Consulta consulta = new Consulta();
		consulta.setId((Integer) tuple.get("ID"));
		consulta.setData((String) tuple.get("DATA"));
		consulta.setHora((String) tuple.get("HORA"));
		consulta.setPacienteId((Integer) tuple.get("PACIENTEID"));
		consulta.setPacienteNome((String) tuple.get("PACIENTENOME"));
		consulta.setMedicoId((Integer) tuple.get("MEDICOID"));
		consulta.setMedicoNome((String) tuple.get("MEDICONOME"));
		
		return consulta;
	}
	
	public void excluir(Integer id) {
		consultaDao.excluir(id);
	}

}
