//13-12-2022 - Classe CondutorDAO, Sistema de gerencia de multas, 1000Devs 2022, Andressa Biagi.
package br.com.mildevs.mildevs_multas.dao;

import java.util.List;

import br.com.mildevs.mildevs_multas.entity.Condutor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

public class CondutorDAO {

	private EntityManager manager;

	public CondutorDAO() {
		this.manager = Persistence.createEntityManagerFactory("sistema_multas").createEntityManager();
	}

	public boolean criaCondutor(Condutor condutor) {
		this.manager.getTransaction().begin();
		this.manager.persist(condutor);
		this.manager.getTransaction().commit();

		return true;
	}

	public Condutor consultaCondutor(int nroCnh) {
		return this.manager.find(Condutor.class, nroCnh);
	}

	public List<Condutor> listaCondutores() {
		Query query = manager.createQuery("select c from Condutor as c");

		return query.getResultList();
	}

	public boolean removeCondutor(int nroCnh) {
		Condutor condutor = this.manager.find(Condutor.class, nroCnh);

		if (condutor == null) {
			return false;
		}
		this.manager.getTransaction().begin();
		this.manager.remove(condutor);
		this.manager.getTransaction().commit();
		return true;
	}
}
