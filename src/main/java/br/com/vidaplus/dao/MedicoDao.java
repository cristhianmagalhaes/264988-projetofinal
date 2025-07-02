package br.com.vidaplus.dao;

import br.com.vidaplus.model.Medico;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.Tuple;


@RequestScoped
public class MedicoDao {
	
	@PersistenceContext
	EntityManager entityManager;
	
	private static final String CADASTRAR = "INSERT INTO MEDICO (NOME, ESPECIALIDADE, TELEFONE, EMAIL) VALUES(:parametro1, :parametro2, :parametro3, :parametro4)";
	
	private static final String VISUALIZAR = "SELECT ID, NOME, ESPECIALIDADE, TELEFONE, EMAIL FROM MEDICO WHERE ID = :parametro1";
	
	private static final String ALTERAR = "UPDATE MEDICO SET NOME = :parametro1, ESPECIALIDADE = :parametro2, TELEFONE = :parametro3, EMAIL = :parametro4 WHERE ID = :parametro5";
	
	private static final String EXCLUIR = "DELETE FROM MEDICO WHERE id = :parametro1";
	
	public void cadastrar(Medico medico) {
		Query query = entityManager.createNativeQuery(CADASTRAR, Tuple.class);
		query.setParameter("parametro1", medico.getNome());
		query.setParameter("parametro2", medico.getEspecialidade());
		query.setParameter("parametro3", medico.getTelefone());
		query.setParameter("parametro4", medico.getEmail());
		query.executeUpdate();
	}
	
	public Tuple visualizar(Integer id){
		Query query = entityManager.createNativeQuery(VISUALIZAR, Tuple.class);
		query.setParameter("parametro1", id);		
		return (Tuple) query.getSingleResult();
	}

	public void alterar(Medico medico) {
		Query query = entityManager.createNativeQuery(ALTERAR, Tuple.class);
		query.setParameter("parametro1", medico.getNome());
		query.setParameter("parametro2", medico.getEspecialidade());
		query.setParameter("parametro3", medico.getTelefone());
		query.setParameter("parametro4", medico.getEmail());
		query.setParameter("parametro5", medico.getId());
		query.executeUpdate();
	}

	public void excluir(Integer id) {
		Query query = entityManager.createNativeQuery(EXCLUIR, Tuple.class);
		query.setParameter("parametro1", id);
		query.executeUpdate();
	}

}
