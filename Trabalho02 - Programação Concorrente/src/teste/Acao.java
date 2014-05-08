package teste;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PipedInputStream;

public class Acao extends Thread {
	private Servidor[] servidores = new Servidor[3];
	private DataInputStream deposito,saque,correcao;
	private int fim;
	private int limiteOperacoes = 40;

	public Acao(PipedInputStream deposito, PipedInputStream saque, PipedInputStream correcao, Servidor[] servidores)  {
		this.servidores = servidores;
		this.deposito = new DataInputStream(deposito);
		this.saque = new DataInputStream(saque);
		this.correcao = new DataInputStream(correcao);
		this.fim = 0;

	}
	@Override
	public void run() {
		while(this.fim != this.limiteOperacoes) {
			try{
				int deposito = this.lerDeposito();
				int saque = this.lerSaque();
				int correcao = this.lerCorrecao();

				if(saque != 0 && correcao != 0 && deposito != 0){
					System.out.println("-----------------------\n"+"Operação "+fim+"\n"+"-----------------------"+"\n");
					for(int i = 0; i < 3; i++) {
						servidores[i].depositar(deposito);
						servidores[i].sacar(saque);
						servidores[i].correcao(correcao);

					}
					this.fim++;	
				}
			}catch(Exception e) {

			}

		}
		System.out.println("Fim das operações!\n");
		for(int i = 0; i < 3; i++) {

			System.out.println(servidores[i].toString());;

		}
	}

	public int lerDeposito() {
		try {
			return deposito.read();
		} catch (IOException e) {
			System.out.println("exception Thread servidor"+this.getName());
			e.printStackTrace();
		}
		return 0;
	}
	public int lerSaque() {
		try {
			return saque.read();
		} catch (IOException e) {
			System.out.println("exception Thread servidor"+this.getName());
			e.printStackTrace();
		}
		return 0;
	}
	public int lerCorrecao() {
		try {
			return correcao.read();
		} catch (IOException e) {
			System.out.println("exception Thread servidor"+this.getName());
			e.printStackTrace();
		}
		return 0;
	}


}


