//13-12-2022 - Classe MultaDAO, Sistema de gerencia de multas, 1000Devs 2022, Andressa Biagi.
package br.com.mildevs.mildevs_multas.dao;

import java.util.List;

import br.com.mildevs.mildevs_multas.entity.Multa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

public class MultaDAO {

	private EntityManager manager;

	public MultaDAO() {
		this.manager = Persistence.createEntityManagerFactory("sistema_multas").createEntityManager();
	}

	public boolean criaMulta(Multa multa) {

		this.manager.getTransaction().begin();
		this.manager.persist(multa);
		this.manager.getTransaction().commit();
		return true;
	}

	public Multa consultaMulta(int codigoMulta) {
		return this.manager.find(Multa.class, codigoMulta);
	}

	public List<Multa> listaMultas() {
		Query query = manager.createQuery("select m from multa as m");

		return query.getResultList();
	}

	public boolean removeMulta(int codigoMulta) {

		Multa multa = this.manager.find(Multa.class, codigoMulta);

		if (multa == null) {
			return false;
		}
		this.manager.getTransaction().begin();
		this.manager.remove(multa);
		this.manager.getTransaction().commit();
		return true;
	}
}
