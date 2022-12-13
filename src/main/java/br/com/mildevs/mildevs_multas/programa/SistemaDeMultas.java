//13-12-2022 - Sistema de gerencia de multas, 1000Devs 2022, Andressa Biagi.
//Sistema com o mapeamento de classes Java para tabelas e tipos de dados SQL
//feito com Hibernate para o banco de dados Postgres.
package br.com.mildevs.mildevs_multas.programa;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import br.com.mildevs.mildevs_multas.dao.CondutorDAO;
import br.com.mildevs.mildevs_multas.dao.MultaDAO;
import br.com.mildevs.mildevs_multas.dao.VeiculoDao;
import br.com.mildevs.mildevs_multas.entity.Condutor;
import br.com.mildevs.mildevs_multas.entity.Multa;
import br.com.mildevs.mildevs_multas.entity.Veiculo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class SistemaDeMultas {

	public static int menuInicial(Scanner teclado) {
		int opcao;
		System.out.println(" -- SISTEMA DE MULTAS -- ");

		System.out.println(" -Digite a opção desejada- ");
		System.out.println("##------------------------##");
		System.out.println("1 - CONDUTOR");
		System.out.println("2 - VEICULOS");
		System.out.println("3 - MULTAS");
		System.out.println("0 - Encerrar o programa");
		opcao = teclado.nextInt();
		return opcao;
	}

	public static int menuDoCondutor(Scanner teclado) {
		int opcao;
		System.out.println(" -- MENU DO CONDUTOR -- ");

		System.out.println(" -Digite a opção desejada- ");
		System.out.println("##------------------------##");
		System.out.println("1 - Cadastrar um novo condutor");
		System.out.println("2 - Excluir um condutor");
		System.out.println("3 - Consultar dados de um condutor");
		System.out.println("4 - Listar condutores cadastrados");
		opcao = teclado.nextInt();
		return opcao;
	}

	public static void cadastraCondutor(Scanner teclado) {
		System.out.println("-- NOVO CADASTRO -- ");
		System.out.println("DIGITE O NUMERO DA CNH: ");
		int nroCnh = teclado.nextInt();
		System.out.println("DIGITE A DATA DE NASCIMENTO DO CONDUTOR: ");
		String dataNascimento = teclado.next();
		teclado.nextLine();
		System.out.println("DIGITE O ORGAO EMISSOR: ");
		String orgaoEmissor = teclado.next();

		Condutor condutorInserido = new Condutor();
		condutorInserido.setNroCnh(nroCnh);
		condutorInserido.setDataNascimento(dataNascimento);
		condutorInserido.setOrgaoEmissor(orgaoEmissor);

		CondutorDAO condutorDao = new CondutorDAO();
		if (condutorDao.criaCondutor(condutorInserido)) {
			System.out.println("Condutor cadastrado com sucesso!\n");
		}
	}

	public static void removeCondutor(Scanner teclado) {
		System.out.println("-- REMOVER UM CONDUTOR -- ");
		System.out.println("Digite o número da CNH do condutor: ");
		int nroCnh = teclado.nextInt();

		CondutorDAO condutorDao = new CondutorDAO();
		if (condutorDao.removeCondutor(nroCnh)) {
			System.out.println("Condutor removido com sucesso!");
		}
	}

	public static void consultaCondutor(Scanner teclado) {
		System.out.println("-- CONSULTAR UM CONDUTOR -- ");
		System.out.println("Digite o número da CNH do condutor: ");
		int nroCnh = teclado.nextInt();

		CondutorDAO condutorDao = new CondutorDAO();
		Condutor condutorConsultado = condutorDao.consultaCondutor(nroCnh);
		System.out.println("\n -- DADOS DO CONDUTOR -- ");
		System.out.println(condutorConsultado.toString());
	}

	public static void listaCondutores(Scanner teclado) {
		CondutorDAO condutorDao = new CondutorDAO();
		List<Condutor> condutoresNoDb = condutorDao.listaCondutores();
		for (Condutor condutorEncontradoNaListagem : condutoresNoDb) {
			System.out.println(condutorEncontradoNaListagem.toString());
		}
	}

	public static int menuDoVeiculo(Scanner teclado) {
		int opcao;
		System.out.println(" -- MENU DO VEICULO -- ");
		System.out.println(" -Digite a opção desejada- ");
		System.out.println("##------------------------##");
		System.out.println("1 - Cadastrar um novo veiculo");
		System.out.println("2 - Excluir um veiculo");
		System.out.println("3 - Consultar dados de um veiculo");
		System.out.println("4 - Listar veiculos cadastrados");
		opcao = teclado.nextInt();
		return opcao;
	}

	public static void cadastraVeiculo(Scanner teclado) {
		System.out.println("-- NOVO CADASTRO -- ");
		System.out.println("DIGITE A PLACA DO VEICULO: ");
		String placa = teclado.next();
		System.out.println("DIGITE O ANO: ");
		int ano = teclado.nextInt();
		teclado.nextLine();
		System.out.println("DIGITE O MODELO DO VEICULO: ");
		String modelo = teclado.next();
		System.out.println("DIGITE A MARCA DO VEICULO: ");
		String marca = teclado.next();
		teclado.nextLine();
		System.out.println("DIGITE O NUMERO DA CNH DO CONDUTOR: ");
		int cnhCondutor = teclado.nextInt();

		CondutorDAO condutorDao = new CondutorDAO();
		Veiculo veiculoInserido = new Veiculo();
		veiculoInserido.setAno(ano);
		veiculoInserido.setPlaca(placa);
		veiculoInserido.setModelo(modelo);
		veiculoInserido.setMarca(marca);
		veiculoInserido.setCondutor(condutorDao.consultaCondutor(cnhCondutor));

		VeiculoDao veiculoDao = new VeiculoDao();
		if (veiculoDao.criaVeiculo(veiculoInserido)) {
			System.out.println("Veiculo cadastrado com sucesso!\n");
		}
	}

	public static void removeVeiculo(Scanner teclado) {
		System.out.println("-- REMOVER UM VEICULO -- ");
		System.out.println("DIGITE A PLACA DO VEICULO QUE DESEJA REMOVER: ");
		String placa = teclado.next();
		teclado.nextLine();
		VeiculoDao veiculoDao = new VeiculoDao();

		if (veiculoDao.removeVeiculo(placa)) {
			System.out.println("Veiculo removido com sucesso!\n");
		}
	}

	public static void consultaVeiculo(Scanner teclado) {
		System.out.println("-- CONSULTAR UM VEICULO -- ");
		System.out.println("Digite a placa do veiculo: ");
		String placa = teclado.next();

		VeiculoDao veiculoDao = new VeiculoDao();
		Veiculo veiculoConsultado = veiculoDao.consultaVeiculo(placa);
		System.out.println("\n -- DADOS DO CONDUTOR -- ");
		System.out.println(veiculoConsultado.toString());
	}

	public static void listaVeiculos(Scanner teclado) {
		VeiculoDao veiculoDao = new VeiculoDao();
		List<Veiculo> veiculosNoDb = veiculoDao.listaVeiculos();
		for (Veiculo veiculoEncontradoNaListagem : veiculosNoDb) {
			System.out.println(veiculoEncontradoNaListagem.toString());
		}
	}

	public static int menuDeMultas(Scanner teclado) {
		int opcao;
		System.out.println(" -- MENU DE MULTAS -- ");
		System.out.println(" -Digite a opção desejada- ");
		System.out.println("##------------------------##");
		System.out.println("1 - Cadastrar uma nova multa");
		System.out.println("2 - Excluir uma multa");
		System.out.println("3 - Consultar dados de uma multa");
		System.out.println("4 - Listar multas cadastradas");
		opcao = teclado.nextInt();
		return opcao;
	}

	public static void cadastraMulta(Scanner teclado) {
		System.out.println("-- NOVO CADASTRO -- ");
		System.out.println("DIGITE O VALOR DA MULTA: ");
		double valor = teclado.nextDouble();
		System.out.println("DIGITE A PONTUAÇÃO: ");
		int pontuacao = teclado.nextInt();
		teclado.nextLine();
		System.out.println("DIGITE A PLACA DO VEICULO: ");
		String placaVeiculo = teclado.next();

		MultaDAO multaDao = new MultaDAO();
		Multa multaInserida = new Multa();
		multaInserida.setPontuacao(pontuacao);
		multaInserida.setValor(valor);

		VeiculoDao veiculoDao = new VeiculoDao();
		multaInserida.setVeiculo(veiculoDao.consultaVeiculo(placaVeiculo));
		if (multaDao.criaMulta(multaInserida)) {
			System.out.println("Multa cadastrada com sucesso!\n");
		}
	}

	public static void removeMulta(Scanner teclado) {
		System.out.println("-- REMOVER UMA MULTA -- ");
		System.out.println("DIGITE O CÓDIGO DA MULTA QUE DESEJA REMOVER: ");
		int codigoMulta = teclado.nextInt();

		MultaDAO multaDao = new MultaDAO();
		if (multaDao.removeMulta(codigoMulta)) {
			System.out.println("Multa removida com sucesso!\n");
		}
	}

	public static void consultaMulta(Scanner teclado) {
		System.out.println("-- CONSULTAR UMA MULTA -- ");
		System.out.println("DIGITE O CÓDIGO DA MULTA: ");
		int codigoMulta = teclado.nextInt();

		MultaDAO multaDao = new MultaDAO();
		Multa multaConsultado = multaDao.consultaMulta(codigoMulta);
		System.out.println("-- INFORMAÇÕES DA MULTA -- ");
		System.out.println(multaConsultado.toString());
	}

	public static void listaMultas(Scanner teclado) {
		MultaDAO multaDao = new MultaDAO();
		List<Multa> multasNoDb = multaDao.listaMultas();
		for (Multa multaEncontradaNaListagem : multasNoDb) {
			System.out.println(multaEncontradaNaListagem.toString());
		}
	}

	public static void main(String[] args) {
		EntityManager manager = Persistence.createEntityManagerFactory("sistema_multas").createEntityManager();
		Scanner teclado = new Scanner(System.in);

		int opcao;

		do {
			opcao = menuInicial(teclado);
			try {

				switch (opcao) {
				case 1:
					switch (menuDoCondutor(teclado)) {
					case 1:
						cadastraCondutor(teclado);
						break;
					case 2:
						removeCondutor(teclado);
						break;
					case 3:
						consultaCondutor(teclado);
						break;
					case 4:
						listaCondutores(teclado);
						break;
					}
					break;
				case 2:
					switch (menuDoVeiculo(teclado)) {
					case 1:
						cadastraVeiculo(teclado);
						break;
					case 2:
						removeVeiculo(teclado);
						break;
					case 3:
						consultaVeiculo(teclado);
						break;
					case 4:
						listaVeiculos(teclado);
						break;
					}
					break;
				case 3:
					switch (menuDeMultas(teclado)) {
					case 1:
						cadastraMulta(teclado);
						break;
					case 2:
						removeMulta(teclado);
						break;
					case 3:
						consultaMulta(teclado);
						break;
					case 4:
						listaMultas(teclado);
						break;
					}
					break;

				}
			} catch (InputMismatchException e) {
				System.out.println("Erro! refaça a operação");
				teclado.nextLine();
			}

		} while (opcao != 0);

		teclado.close();
		manager.close();
	}
}
