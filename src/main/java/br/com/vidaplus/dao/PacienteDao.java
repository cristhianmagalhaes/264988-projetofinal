package br.com.vidaplus.dao;

import br.com.vidaplus.model.Paciente;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.Tuple;


@RequestScoped
public class PacienteDao {
	
	@PersistenceContext
	EntityManager entityManager;
	
	private static final String CADASTRAR = "INSERT INTO PACIENTE (NOME, DATANASCIMENTO, ENDERECO, TELEFONE, EMAIL) VALUES(:parametro1, :parametro2, :parametro3, :parametro4, :parametro5)";
	
	private static final String LISTAR = "SELECT ID, NOME, DATANASCIMENTO, ENDERECO, TELEFONE, EMAIL FROM PACIENTE WHERE ID = :parametro1";
	
	private static final String ALTERAR = "UPDATE PACIENTE SET NOME = :parametro1, DATANASCIMENTO = :parametro2, ENDERECO = :parametro3, TELEFONE = :parametro4, EMAIL = :parametro5 WHERE ID = :parametro6";
	
	private static final String EXCLUIR = "DELETE FROM PACIENTE WHERE id = :parametro1";
	
	public void cadastrar(Paciente paciente) {
		Query query = entityManager.createNativeQuery(CADASTRAR, Tuple.class);
		query.setParameter("parametro1", paciente.getNome());
		query.setParameter("parametro2", paciente.getDataNascimento());
		query.setParameter("parametro3", paciente.getEndereço());
		query.setParameter("parametro4", paciente.getTelefone());
		query.setParameter("parametro5", paciente.getEmail());
		query.executeUpdate();
	}
	
	public Tuple visualizar(Integer id){
		Query query = entityManager.createNativeQuery(LISTAR, Tuple.class);
		query.setParameter("parametro1", id);		
		return (Tuple) query.getSingleResult();
	}

	public void alterar(Paciente paciente) {
		Query query = entityManager.createNativeQuery(ALTERAR, Tuple.class);
		query.setParameter("parametro1", paciente.getNome());
		query.setParameter("parametro2", paciente.getDataNascimento());
		query.setParameter("parametro3", paciente.getEndereço());
		query.setParameter("parametro4", paciente.getTelefone());
		query.setParameter("parametro5", paciente.getEmail());
		query.setParameter("parametro6", paciente.getId());
		query.executeUpdate();
	}
	
	public void excluir(Integer id) {
		Query query = entityManager.createNativeQuery(EXCLUIR, Tuple.class);
		query.setParameter("parametro1", id);
		query.executeUpdate();
	}

}
