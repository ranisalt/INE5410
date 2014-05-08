package banco;

import java.io.DataInputStream;
import java.io.PipedInputStream;

public class ThreadServidor extends Thread {
	private Servidor[] servidores = new Servidor[3];
	private DataInputStream entrada;
	public ThreadServidor(PipedInputStream entrada, Servidor[] servidores)  {
		this.servidores = servidores;
		this.entrada = new DataInputStream(entrada);
	}
	@Override
	public void run() {
		
	}
}


