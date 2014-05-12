package banco;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class Principal {
	public static void main(String[] args) {
		PipedOutputStream[] pipesSaidaDeposito = new PipedOutputStream[3];
		PipedInputStream[] pipesEntradaDeposito = new PipedInputStream[3];
		PipedOutputStream[] pipesSaidaSaque = new PipedOutputStream[3];
		PipedInputStream[] pipesEntradaSaque = new PipedInputStream[3];
		PipedOutputStream[] pipesSaidaCorrecao = new PipedOutputStream[3];
		PipedInputStream[] pipesEntradaCorrecao = new PipedInputStream[3];
		Operacao[] operacoesDeposito = new Operacao[3];
		Operacao[] operacoesSaque = new Operacao[3];
		Operacao[] operacoesCorrecao = new Operacao[3];
		Servidor[] servidores = new Servidor[3];
		try{
			for(int i = 0; i < 3; i++) {
				servidores[i] = new Servidor("Servidor "+i);
				//Criando pipes de saída de cada operação
				pipesSaidaDeposito[i] = new PipedOutputStream();
				pipesSaidaSaque[i] = new PipedOutputStream();
				pipesSaidaCorrecao[i] = new PipedOutputStream();
				//Criando pipes de entrada de cada operacao e fazendo o link com as suas respectivas saídas
				pipesEntradaDeposito[i] = new PipedInputStream(pipesSaidaDeposito[i]);
				pipesEntradaSaque[i] = new PipedInputStream(pipesSaidaSaque[i]);
				pipesEntradaCorrecao[i] = new PipedInputStream(pipesSaidaCorrecao[i]);
				//Criando objetos Operação, determinando suas funções por parâmetro e adicionando os seus servidores
				operacoesDeposito[i] = new Operacao(pipesEntradaDeposito[i], "deposito");
				operacoesDeposito[i].adicionarServidor(servidores[i]);

				operacoesSaque[i] = new Operacao(pipesEntradaSaque[i], "saque");
				operacoesSaque[i].adicionarServidor(servidores[i]);

				operacoesCorrecao[i] = new Operacao(pipesEntradaCorrecao[i], "correcao");
				operacoesCorrecao[i].adicionarServidor(servidores[i]);
			}

			for(int i = 0; i < 3; i++){
				operacoesDeposito[i].start();
				operacoesSaque[i].start();
				operacoesCorrecao[i].start();

			}
			Cliente um = new Cliente(pipesSaidaDeposito, 10);		
			Cliente dois = new Cliente(pipesSaidaSaque, 3);
			Cliente tres = new Cliente(pipesSaidaCorrecao, 10);
			um.start();
			dois.start();
			tres.start();


			System.out.println(servidores[0].toString());




		}catch(IOException e) {

		}
	}


}