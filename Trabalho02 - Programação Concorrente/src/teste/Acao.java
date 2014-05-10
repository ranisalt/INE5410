package teste;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PipedInputStream;

public class Acao extends Thread {
	private Servidor servidor;
	private DataInputStream input;
	private String acao;
	private int fim;
	private int limiteOperacoes = 40;

	public Acao(PipedInputStream in, String acao)  {
		this.acao = acao.toLowerCase();
		this.input = new DataInputStream(in);
		this.fim = 0;

	}
	@Override
	public void run() {
		int deposito = 0;
		int saque = 0;
		int correcao = 0;
		while(this.fim != this.limiteOperacoes) {
			try{
				switch(this.acao){
				case "deposito": 
					while(deposito == 0){
						deposito = this.lerInput();
					}
					this.servidor.depositar(deposito);
					break;
				case "saque": 
					while(saque == 0){
						saque = this.lerInput();
					}
					this.servidor.sacar(saque);
					break;
				case "correcao": 
					while(correcao == 0){
						correcao = this.lerInput();
					}
					this.servidor.depositar(correcao);
					break;
				}
				this.fim++;	
				}catch(Exception e) {

				}
			}
		}

	public int lerInput() {
		try {
			return input.read();
		} catch (IOException e) {
			System.out.println("exception Thread servidor"+this.getName());
			e.printStackTrace();
		}
		return 0;
	}
	
	public void adicionarServidor(Servidor servidor){
		this.servidor = servidor;
	}


}


