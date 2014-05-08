package banco;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class Principal {

	public static void main(String[] args) {
		Servidor[] servidores = new Servidor[3]; 
		for(int i=1; i == 3 ; i++) {
			//criando servidores
			Servidor servidor = new Servidor("Servidor"+i);
			servidores[--i] = servidor;
		}
		
		PipedOutputStream deposito = new PipedOutputStream();
		PipedOutputStream saque = new PipedOutputStream();
		PipedOutputStream correcao = new PipedOutputStream();
		
		try {
			PipedInputStream entrada1 = new PipedInputStream(deposito);
			PipedInputStream entrada2 = new PipedInputStream(saque);
			PipedInputStream entrada3 = new PipedInputStream(correcao);
		
			//criando ThreadServidor
			Deposito tServidor1 = new Deposito(entrada1, servidores);
			Saque tServidor2 = new Saque(entrada2, servidores);
			Correcao tServidor3 = new Correcao(entrada3, servidores);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		//criando clientes
		Cliente cliente1 = new Cliente(deposito, 10);
		Cliente cliente2 = new Cliente(saque, -3);
		Cliente cliente3 = new Cliente(correcao, 10);
		
	}
}
