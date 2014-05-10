package teste;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PipedOutputStream;

public class Cliente extends Thread {
	private DataOutputStream saida;
	private int valorSaida;
	private int fim;
	private int limiteOperacoes = 40;
	
	public Cliente(PipedOutputStream saida1, int valorSaida) {
		this.saida = new DataOutputStream(saida1);
		this.valorSaida = valorSaida;
		this.fim = 0;
	}
	
	@Override
	public void run() {
		while(this.fim != this.limiteOperacoes){
			
			this.enviarValor();
			this.fim++;
			
		}
	}
	
	public void enviarValor() {
		try {
			saida.writeInt(this.valorSaida);
			saida.flush();
		} catch (IOException e) {
			System.out.println("Caiu no catch do cliente");
			e.printStackTrace();
		}
	}
}
