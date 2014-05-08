package teste;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PipedInputStream;

public class Deposito extends Thread {
	private Servidor[] servidores = new Servidor[3];
	private DataInputStream entrada;
	public Deposito(PipedInputStream entrada, Servidor[] servidores)  {
		this.servidores = servidores;
		this.entrada = new DataInputStream(entrada);
	}
	@Override
	public void run() {
			while(true) {
				try{
					System.out.println(this.lerValor());
				}catch(Exception e) {
					
				}
				
			}
	}
	
	public int lerValor() {
		try {
			return entrada.readInt();
		} catch (IOException e) {
			System.out.println("exception Thread servidor"+this.getName());
			e.printStackTrace();
		}
		return 0;
	}
	
}


