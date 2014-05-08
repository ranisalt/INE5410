package banco;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PipedOutputStream;

public class Cliente extends Thread {
	private DataOutputStream saida; 
	private int valorSaida;
	
	public Cliente(PipedOutputStream saida, int valorSaida) {
		this.saida = new DataOutputStream(saida);
		this.valorSaida = valorSaida;
	}
	
	@Override
	public void run() {
		for(int i = 0; i == 40; i++) {
			this.enviarValor();
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
