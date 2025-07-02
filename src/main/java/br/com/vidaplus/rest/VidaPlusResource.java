package br.com.vidaplus.rest;

import br.com.vidaplus.model.Consulta;
import br.com.vidaplus.model.Medico;
import br.com.vidaplus.model.Paciente;
import br.com.vidaplus.service.ConsultaService;
import br.com.vidaplus.service.MedicoService;
import br.com.vidaplus.service.PacienteService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/")
public class VidaPlusResource {
	
	@Inject
	PacienteService pacienteService;
	
	@Inject
	MedicoService medicoService;
	
	@Inject
	ConsultaService consultaService;
	
	//Endpoints para PACIENTES
	
	@Path("pacientes")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public void cadastrarPaciente(Paciente paciente) {
		pacienteService.cadastrar(paciente);
	}
	
	@Path("pacientes/{id}")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public void alterarPaciente(@PathParam("id") Integer id, Paciente paciente) {
		
		Paciente pacienteBanco = pacienteService.visualizar(id);
		
		if(paciente.getNome() != null) {
			pacienteBanco.setNome(paciente.getNome());
		}
		if(paciente.getDataNascimento() != null) {
			pacienteBanco.setDataNascimento(paciente.getDataNascimento());
		}
		if(paciente.getEndereço() != null) {
			pacienteBanco.setEndereço(paciente.getEndereço());
		}
		if(paciente.getTelefone() != null) {
			pacienteBanco.setTelefone(paciente.getTelefone());
		}
		if(paciente.getEmail() != null) {
			pacienteBanco.setEmail(paciente.getEmail());
		}
		pacienteService.alterar(pacienteBanco);
		
	}
	
	@Path("pacientes/{id}")
	@DELETE
	@Transactional
	public void excluirPaciente(@PathParam("id") Integer id) {
		pacienteService.excluir(id);
	}
	
	@Path("pacientes/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Paciente visualizarPaciente(@PathParam("id") Integer id) {
		
		return pacienteService.visualizar(id);
		
	}
	
	//Endpoints para MÉDICOS
	
	@Path("medicos")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public void cadastrarMedico(Medico medico) {
		medicoService.cadastrar(medico);
	}
	
	@Path("medicos/{id}")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public void alterarMedico(@PathParam("id") Integer id, Medico medico) {
		
		Medico medicoBanco = medicoService.visualizar(id);
		
		if(medico.getNome() != null) {
			medicoBanco.setNome(medico.getNome());
		}
		if(medico.getEspecialidade() != null) {
			medicoBanco.setEspecialidade(medico.getEspecialidade());
		}
		if(medico.getTelefone() != null) {
			medicoBanco.setTelefone(medico.getTelefone());
		}
		if(medico.getEmail() != null) {
			medicoBanco.setEmail(medico.getEmail());
		}
	
		medicoService.alterar(medicoBanco);
		
	}
	
	@Path("medicos/{id}")
	@DELETE
	@Transactional
	public void excluirMedico(@PathParam("id") Integer id) {
		medicoService.excluir(id);
	}
	
	@Path("medicos/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Medico visualizarMedico(@PathParam("id") Integer id) {
		
		return medicoService.visualizar(id);
		
	}
	
	//Endpoints para CONSULTAS
	
	@Path("consultas")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public void cadastrarConsulta(Consulta consulta) {
		consultaService.cadastrar(consulta);
	}
	
	@Path("consultas/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Consulta visualizarConsulta(@PathParam("id") Integer id) {
		
		return consultaService.visualizar(id);
		
	}
	
	@Path("cosultas/{id}")
	@DELETE
	@Transactional
	public void cancelarConsulta(@PathParam("id") Integer id) {
		consultaService.excluir(id);
	}

}
