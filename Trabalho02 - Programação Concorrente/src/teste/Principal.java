package teste;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class Principal {

	public static void main(String[] args) {
		Deposito tDeposito = null;
		Saque tSaque = null;
		Correcao tCorrecao = null;
		Servidor[] servidores = new Servidor[3]; 
		for(int i=1; i == 3 ; i++) {
			//criando servidores
			Servidor servidor = new Servidor("Servidor"+i);
			servidores[--i] = servidor;
		}

		PipedOutputStream deposito1, deposito2, deposito3 = new PipedOutputStream();
		PipedOutputStream saque1, saque2, saque3 = new PipedOutputStream();
		PipedOutputStream correcao1, correcao2, correcao3 = new PipedOutputStream();
	}
}	
//try {
//PipedInputStream entrada = new PipedInputStream(deposito);

//criando ThreadServidor

//} catch (IOException e) {
//e.printStackTrace();
//}
//criando clientes
//		Cliente cliente1 = new Cliente(deposito1, deposito2, deposito3, 10);
//		Cliente cliente2 = new Cliente(saque1, saque2, saque3, 3);
//		Cliente cliente3 = new Cliente(correcao1, correcao2, correcao3, 10);		
//	}
//}
