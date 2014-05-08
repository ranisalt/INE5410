package banco;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PipedInputStream;

public class Saque extends Thread{
	private Servidor[] servidores = new Servidor[3];
	private DataInputStream entrada;
	public Saque(PipedInputStream entrada, Servidor[] servidores)  {
		this.servidores = servidores;
		this.entrada = new DataInputStream(entrada);
	}
	@Override
	public void run() {
		try {
			int valor = entrada.readInt();
			for(int i = 0; i<servidores.length; i++) {
				this.servidores[i].sacar(valor);
			}
		} catch (IOException e) {
			System.out.println("exception Thread servidor"+this.getName());
			e.printStackTrace();
		}
	}
}
