//13-12-2022 - Classe VeiculoDAO, Sistema de gerencia de multas, 1000Devs 2022, Andressa Biagi.
package br.com.mildevs.mildevs_multas.dao;

import java.util.List;

import br.com.mildevs.mildevs_multas.entity.Veiculo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

public class VeiculoDao {
	private EntityManager manager;

	public VeiculoDao() {
		this.manager = Persistence.createEntityManagerFactory("sistema_multas").createEntityManager();
	}

	public boolean criaVeiculo(Veiculo veiculo) {

		this.manager.getTransaction().begin();
		this.manager.persist(veiculo);
		this.manager.getTransaction().commit();
		return true;

	}

	public Veiculo consultaVeiculo(String placa) {
		return this.manager.find(Veiculo.class, placa);
	}

	public List<Veiculo> listaVeiculos() {
		Query query = manager.createQuery("select v from veiculo as v");

		return query.getResultList();
	}

	public boolean removeVeiculo(String placa) {

		Veiculo veiculo = this.manager.find(Veiculo.class, placa);

		if (veiculo == null) {
			return false;
		}

		this.manager.getTransaction().begin();
		this.manager.remove(veiculo);
		this.manager.getTransaction().commit();
		return true;

	}
}
