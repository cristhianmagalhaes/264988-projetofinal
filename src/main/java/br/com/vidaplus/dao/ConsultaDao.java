package br.com.vidaplus.dao;

import br.com.vidaplus.model.Consulta;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.Tuple;


@RequestScoped
public class ConsultaDao {
	
	@PersistenceContext
	EntityManager entityManager;
	
	private static final String CADASTRAR = "INSERT INTO CONSULTA (DATA, HORA, PACIENTEID, PACIENTENOME, MEDICOID, MEDICONOME) VALUES(:parametro1, :parametro2, :parametro3, :parametro4, :parametro5, :parametro6)";
	
	private static final String VISUALIZAR = "SELECT ID, DATA, HORA, PACIENTEID, PACIENTENOME, MEDICOID, MEDICONOME FROM CONSULTA WHERE ID = :parametro1";
	
	private static final String EXCLUIR = "DELETE FROM CONSULTA WHERE id = :parametro1";
		
	public void cadastrar(Consulta consulta) {
		Query query = entityManager.createNativeQuery(CADASTRAR, Tuple.class);
		query.setParameter("parametro1", consulta.getData());
		query.setParameter("parametro2", consulta.getHora());
		query.setParameter("parametro3", consulta.getPacienteId());
		query.setParameter("parametro4", consulta.getPacienteNome());
		query.setParameter("parametro5", consulta.getMedicoId());
		query.setParameter("parametro6", consulta.getMedicoNome());
		query.executeUpdate();
	}
	
	public Tuple visualizar(Integer id){
		Query query = entityManager.createNativeQuery(VISUALIZAR, Tuple.class);
		query.setParameter("parametro1", id);		
		return (Tuple) query.getSingleResult();
	}
	
	public void excluir(Integer id) {
		Query query = entityManager.createNativeQuery(EXCLUIR, Tuple.class);
		query.setParameter("parametro1", id);
		query.executeUpdate();
	}

}
