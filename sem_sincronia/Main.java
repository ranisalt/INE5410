package sem_sincronia;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		/* Criar as listas de listas para pipes e atendentes */
		List<List<PipedOutputStream>> pipes_saida = new ArrayList<List<PipedOutputStream>>(
				3);
		List<List<PipedInputStream>> pipes_entrada = new ArrayList<List<PipedInputStream>>(
				3);
		List<List<Atendente>> atendentes = new ArrayList<List<Atendente>>(3);

		/* Criar as listas internas pra poder inserir depois */
		for (int i = 0; i < 3; ++i) {
			pipes_saida.add(new ArrayList<PipedOutputStream>(3));
			pipes_entrada.add(new ArrayList<PipedInputStream>(3));
			atendentes.add(new ArrayList<Atendente>(3));
		}

		/* Lista de servidores */
		List<Servidor> servidores = new ArrayList<Servidor>(3);

		try {
			for (int i = 0; i < 3; i++) {
				servidores.add(new Servidor("Servidor-" + i));
				pipes_saida.add(new ArrayList<PipedOutputStream>(3));
				pipes_entrada.add(new ArrayList<PipedInputStream>(3));

				/*
				 * Instanciar um pipe de saída, conectar uma entrada e adicionar
				 * um atendente nele
				 */
				char[] ops = { 'c', 'd', 'r' };
				for (int j = 0; j < 3; ++j) {
					pipes_saida.get(i).add(new PipedOutputStream());
					pipes_entrada.get(i).add(
							new PipedInputStream(pipes_saida.get(i).get(j)));
					atendentes.get(i).add(
							new Atendente(pipes_entrada.get(i).get(j), ops[j]));
					atendentes.get(i).get(j).setServidor(servidores.get(i));
				}
			}

			/* Iniciar os atendentes */
			for (int i = 0; i < 3; ++i)
				for (int j = 0; j < 3; ++j)
					atendentes.get(i).get(j).start();

			/* Instanciar os clientes com seus valores */
			List<Cliente> clientes = new ArrayList<Cliente>(3);
			int val[] = { 10, 10, 3 };
			for (int i = 0; i < 3; ++i) {
				List<PipedOutputStream> l = new ArrayList<PipedOutputStream>(3);
				for (int j = 0; j < 3; ++j)
					l.add(pipes_saida.get(j).get(i));
				clientes.add(new Cliente(l, val[i]));
			}

			/* Iniciar os clientes */
			for (int i = 0; i < 3; ++i)
				clientes.get(i).start();

			esperar(clientes, atendentes);

			System.out.println("==> Término");
			for (Servidor s : servidores)
				System.out.println(s.toString());
		} catch (IOException e) {
			System.out.println("==> Catch " + e.getClass().getName() + " em main()");
		}
	}

	public static void esperar(List<Cliente> clientes,
			List<List<Atendente>> atendentes) {
		int i = 1;
		while (i > 0) {
			i = 0;
			for (Cliente c : clientes) {
				if (c.isAlive()) {
					++i;
					break;
				}
			}
			for (List<Atendente> aL : atendentes) {
				for (Atendente a : aL) {
					if (a.isAlive()) {
						++i;
						break;
					}
				}
			}
		}
	}
}
