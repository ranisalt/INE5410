package banco;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PipedInputStream;

public class Operacao extends Thread {
	private Servidor servidor;
	private PipedInputStream input;
	private String acao;

	public Operacao(PipedInputStream in, String acao)  {
		this.acao = acao.toLowerCase();
		this.input = in;

	}
	@Override
	public void run() {
		try{
			int saque = 0;
			int correcao = 0;
			int deposito = 0;
			while((deposito=this.lerInput()) != -1 && (correcao = this.lerInput())!=-1 && (saque = this.lerInput())!=-1){
				switch(this.acao){
				case "deposito": 
					this.servidor.depositar(deposito);
					break;
				case "saque": 
					this.servidor.sacar(saque);
					break;
				case "correcao": 
					this.servidor.correcao(correcao);
					break;
				}
			}
			this.input.close();
			System.out.println(this.acao+" encerrados"+this.servidor.toString());
		}catch(Exception e) {

		}
	}

	public int lerInput() {
		try {
			return input.read();

		} catch (IOException e) {
			System.out.println("exception Thread operacao"+this.getName());
			e.printStackTrace();
		}
		return 0;
	}

	public void adicionarServidor(Servidor servidor){
		this.servidor = servidor;
	}

	public Servidor getServidor(){
		return this.servidor;
	}


}
