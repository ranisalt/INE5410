package banco;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PipedOutputStream;

public class Cliente extends Thread {
	private PipedOutputStream[] saidas = new PipedOutputStream[3];
	private String acao;
	private int valorSaida;
	private int fim;
	private int limiteOperacoes = 40;

	public Cliente(PipedOutputStream[] pipesSaidas, int valorSaida) {
		for(int i = 0; i < 3; i++) {
			this.saidas[i] = pipesSaidas[i];
		}
		this.valorSaida = valorSaida;
		this.fim = 0;
	}

	@Override
	public void run() {

		while(this.fim !=this.limiteOperacoes){
			this.enviarValor();
			this.fim++;
		}
		try{
			for(int i = 0; i < 3; i++) {
				this.saidas[i].close();
			}
		}catch(Exception e){
			System.out.println("catch close");
		}
	}

	public void enviarValor() {
		try {
			for(int i = 0; i < 3; i++) {
				this.saidas[i].write(this.valorSaida);
				this.saidas[i].flush();

			}
		} catch (IOException e) {
			System.out.println("Caiu no catch do cliente");
			e.printStackTrace();
		}
	}
}