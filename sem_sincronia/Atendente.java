
import java.io.IOException;
import java.io.PipedInputStream;

public class Atendente extends Thread {
	private Servidor servidor;
	private PipedInputStream input;
	private char op;

	public Atendente(PipedInputStream in, char op) {
		this.input = in;
		this.op = op;
	}

	public void run() {
		try {
			int v;
			switch (this.op) {
			case 'c':
				while ((v = this.lerInput()) != -1) {
					this.servidor.correcao(v);
				}
				break;

			case 'd':
				while ((v = this.lerInput()) != -1) {
					this.servidor.depositar(v);

				}
				break;
			case 'r':
				while ((v = this.lerInput()) != -1) {
					this.servidor.sacar(v);
				}
				break;
			// System.out.println(this.acao+" encerrados"+this.servidor.toString()+" ================ COM "+this.operacoesFeitas+" OPERACOES");
			}
			this.input.close();
		} catch (Exception e) {

		}
	}

	public int lerInput() {
		try {
			return input.read();

		} catch (IOException e) {
			System.out.println("exception Thread operacao" + this.getName());
			e.printStackTrace();
		}
		return 0;
	}

	public void adicionarServidor(Servidor servidor) {
		this.servidor = servidor;
	}

	public Servidor getServidor() {
		return this.servidor;
	}

}
