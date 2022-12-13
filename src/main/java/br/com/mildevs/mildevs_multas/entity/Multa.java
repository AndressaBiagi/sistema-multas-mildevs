//13-12-2022 - Classe entidade Multa, Sistema de gerencia de multas, 1000Devs 2022, Andressa Biagi.
package br.com.mildevs.mildevs_multas.entity;

import jakarta.persistence.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Multa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo_multa")
	private int codigoMulta;

	@Column
	private double valor;

	@Column
	private int pontuacao;

	@ManyToOne
	@JoinColumn(name = "placa_fk", referencedColumnName = "placa")
	private Veiculo veiculo;

	public Multa() {
	}

	public int getCodigoMulta() {
		return codigoMulta;
	}

	public void setCodigoMulta(int codigoMulta) {
		this.codigoMulta = codigoMulta;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public int getPontuacao() {
		return pontuacao;
	}

	public void setPontuacao(int pontuacao) {
		this.pontuacao = pontuacao;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	@Override
	public String toString() {
		return "\n     -- MULTA --  \n codigoMulta: " + codigoMulta + "\n valor: " + valor + "\n pontuacao: "
				+ pontuacao + "\n veiculo: " + veiculo;
	}
}
