package teste;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class Principal {
public static void main(String[] args) {
	PipedOutputStream deposito, saque, correcao;
	PipedInputStream inDeposito, inSaque, inCorrecao;
	Acao[] acoes = new Acao[3]; 
	Agencia[] agencias = new Agencia[3];
	deposito = new PipedOutputStream();
	saque = new PipedOutputStream();
	correcao = new PipedOutputStream();

	
	try{
		//criando pipedOutputStream
		inDeposito = new PipedInputStream(deposito);
		inSaque = new PipedInputStream(saque);
		inCorrecao = new PipedInputStream(correcao);
		
		acoes[0] = new Acao(inDeposito, "deposito");
		acoes[1] = new Acao(inSaque, "saque");
		acoes[2] = new Acao(inCorrecao, "correcao");
		
		Cliente cliente1 = new Cliente(deposito, 10);
		Cliente cliente2 = new Cliente(saque, 3);
		Cliente cliente3 = new Cliente(correcao, 10);
		
		for(int i = 0; i < 3; i++){
			agencias[i] = new Agencia("Servidor"+i,acoes);
		}
		
		cliente1.run();
		cliente2.run();
		cliente3.run();
		
		agencias[0].iniciarOperacoes();
		agencias[1].iniciarOperacoes();
		agencias[2].iniciarOperacoes();
		
		
		
		
	}catch(IOException e) {

	}
	
	System.out.println(agencias[0].getServidor());
	System.out.println(agencias[1].getServidor());
	System.out.println(agencias[2].getServidor());
}
	

}

