import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		List<List<PipedOutputStream>> pipes_saida = new ArrayList<List<PipedOutputStream>>(
				3);
		for (int i = 0; i < 3; ++i) {
			pipes_saida.add(new ArrayList<PipedOutputStream>(3));
		}

		List<List<PipedInputStream>> pipes_entrada = new ArrayList<List<PipedInputStream>>(
				3);
		for (int i = 0; i < 3; ++i) {
			pipes_entrada.add(new ArrayList<PipedInputStream>(3));
		}

		List<List<Atendente>> atendentes = new ArrayList<List<Atendente>>(3);
		for (int i = 0; i < 3; ++i) {
			atendentes.add(new ArrayList<Atendente>(3));
		}

		List<Servidor> servidores = new ArrayList<Servidor>(3);

		try {
			for (int i = 0; i < 3; i++) {
				servidores.add(new Servidor("Servidor-" + i));
				pipes_saida.add(new ArrayList<PipedOutputStream>(3));
				pipes_entrada.add(new ArrayList<PipedInputStream>(3));

				for (int j = 0; j < 3; ++j) {
					pipes_saida.get(i).add(new PipedOutputStream());
				}

				for (int j = 0; j < 3; ++j) {
					pipes_entrada.get(i).add(
							new PipedInputStream(pipes_saida.get(i).get(j)));
				}

				char[] ops = { 'c', 'd', 'r' };
				for (int j = 0; j < 3; ++j) {
					atendentes.get(i).add(
							new Atendente(pipes_entrada.get(i).get(j), ops[j]));
					atendentes.get(i).get(j)
							.adicionarServidor(servidores.get(i));
				}
			}

			for (int i = 0; i < 3; ++i) {
				for (int j = 0; j < 3; ++j) {
					atendentes.get(i).get(j).start();
				}
			}

			List<Cliente> clientes = new ArrayList<Cliente>(3);
			int val[] = { 10, 10, 3 };
			for (int i = 0; i < 3; ++i) {
				List<PipedOutputStream> l = new ArrayList<PipedOutputStream>(3);
				for (int j = 0; j < 3; ++j) {
					l.add(pipes_saida.get(j).get(i));
				}
				clientes.add(new Cliente(l, val[i]));
			}

			for (int i = 0; i < 3; ++i) {
				clientes.get(i).start();
			}

			while (!terminou(clientes, atendentes)) {
			}

			System.out.println("==> Término");
			for (Servidor s : servidores) {
				System.out.println(s.toString());
			}

		} catch (IOException e) {

		}
	}

	public static boolean terminou(List<Cliente> clientes,
			List<List<Atendente>> atendentes) {
		for (Cliente c : clientes) {
			if (c.isAlive()) {
				return false;
			}
		}
		for (List<Atendente> aL : atendentes) {
			for (Atendente a : aL) {
				if (a.isAlive()) {
					return false;
				}
			}
		}
		return true;
	}
}
