package teste;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PipedOutputStream;

public class Cliente extends Thread {
	private DataOutputStream saida1;
	private DataOutputStream saida2;
	private DataOutputStream saida3;
	private int valorSaida;
	
	public Cliente(PipedOutputStream saida1, PipedOutputStream saida2, PipedOutputStream saida3, int valorSaida) {
		this.saida1 = new DataOutputStream(saida1);
		this.saida1 = new DataOutputStream(saida2);
		this.saida1 = new DataOutputStream(saida3);
		this.valorSaida = valorSaida;
	}
	
	@Override
	public void run() {
		while(true){
			this.enviarValor();
			try{
				sleep(1000);
			}catch(InterruptedException e) {
				
			}
		}
	}
	
	public void enviarValor() {
		try {
			saida1.writeInt(this.valorSaida);
			saida1.flush();
			saida2.write(this.valorSaida);
			saida2.flush();
			saida3.write(this.valorSaida);
			saida3.flush();
		} catch (IOException e) {
			System.out.println("Caiu no catch do cliente");
			e.printStackTrace();
		}
	}
}
