//13-12-2022 - Classe entidade Condutor, Sistema de gerencia de multas, 1000Devs 2022, Andressa Biagi.
package br.com.mildevs.mildevs_multas.entity;

import jakarta.persistence.CascadeType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Condutor {

	@Id
	@Column(name = "nro_cnh")
	private int nroCnh;

	@Column(name = "data_nascimento")
	private String dataNascimento;

	@Column(name = "orgao_emissor")
	private String orgaoEmissor;

	@Column()
	private int pontuacao;

	@OneToOne(mappedBy = "condutor", cascade = CascadeType.ALL)
	private Veiculo veiculo;

	public Condutor() {
	}

	public int getNroCnh() {
		return nroCnh;
	}
	public void setNroCnh(int nroCnh) {
		this.nroCnh = nroCnh;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getOrgaoEmissor() {
		return orgaoEmissor;
	}

	public void setOrgaoEmissor(String orgaoEmissor) {
		this.orgaoEmissor = orgaoEmissor;
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
		return "\n        --CONDUTOR-- \n nroCnh: " + nroCnh + "\n dataNascimento: " + dataNascimento
				+ "\n orgaoEmissor: " + orgaoEmissor + "\n pontuacao: " + pontuacao + "\n veiculo: "
				+ veiculo.getPlaca();
	}

}
