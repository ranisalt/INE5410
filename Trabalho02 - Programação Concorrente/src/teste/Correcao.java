package teste;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PipedInputStream;

public class Correcao extends Thread {
	private Servidor[] servidores = new Servidor[3];
	private DataInputStream entrada;
	public Correcao(PipedInputStream entrada, Servidor[] servidores)  {
		this.servidores = servidores;
		this.entrada = new DataInputStream(entrada);
	}
	@Override
	public void run() {
		try {
			int valor = entrada.readInt();
			for(int i = 0; i<servidores.length; i++) {
				this.servidores[i].correcao(valor);
			}
		} catch (IOException e) {
			System.out.println("exception Thread servidor"+this.getName());
			e.printStackTrace();
		}
	}
}
