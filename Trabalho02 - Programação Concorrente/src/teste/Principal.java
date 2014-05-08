package teste;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class Principal {
public static void main(String[] args) {
	PipedOutputStream deposito, saque, correcao;
	PipedInputStream inDeposito, inSaque, inCorrecao;
	Servidor[] servidores = new Servidor[3]; 
	

	deposito = new PipedOutputStream();
	saque = new PipedOutputStream();
	correcao = new PipedOutputStream();

	for(int i = 0; i < 3; i++) {
		Servidor servidor = new Servidor("Servidor"+i);
		servidores[i] = servidor;
	}
	
	try{
		//criando pipedOutputStream
		inDeposito = new PipedInputStream(deposito);
		inSaque = new PipedInputStream(saque);
		inCorrecao = new PipedInputStream(correcao);
		
		Acao acao = new Acao(inDeposito, inSaque, inCorrecao, servidores);
		
		Cliente cliente1 = new Cliente(deposito, 10);
		Cliente cliente2 = new Cliente(saque, 3);
		Cliente cliente3 = new Cliente(correcao, 10);
		
		cliente1.run();
		cliente2.run();
		cliente3.run();
		acao.run();
		
		
		
	}catch(IOException e) {

	}
}
	

}

