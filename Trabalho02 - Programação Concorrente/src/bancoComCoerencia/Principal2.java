package bancoComCoerencia;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class Principal2 {
	public static void main(String[] args) {
		PipedOutputStream[] saida = new PipedOutputStream[3];
		PipedInputStream[] entrada = new PipedInputStream[3];
		Operacao[] operacoes = new Operacao[3];
		Cliente[] clientes = new Cliente[3];
		Servidor[] servidores = new Servidor[3];
		try{
			for(int i = 0; i < 3; i++) {
				servidores[i] = new Servidor("Servidor "+i);
				//Criando pipes de saída de cada operação
				saida[i] = new PipedOutputStream();
				//Criando pipes de entrada de cada operacao e fazendo o link com as suas respectivas saídas
				entrada[i] = new PipedInputStream(saida[i]);
				//Criando objetos Operação, determinando suas funções por parâmetro e adicionando os seus servidores

			}
			operacoes[0] = new Operacao(entrada[0],"deposito");
			operacoes[1] = new Operacao(entrada[1],"saque");
			operacoes[2] = new Operacao(entrada[2],"correcao");
			
			operacoes[0].adicionarServidor(servidores);
			operacoes[1].adicionarServidor(servidores);
			operacoes[2].adicionarServidor(servidores);
			
			clientes[0] = new Cliente(saida[0], 10);		
			clientes[1] = new Cliente(saida[1], 3);
			clientes[2] = new Cliente(saida[2], 10);

			for(int i = 0; i < 3; i++){
				operacoes[i].start();
				clientes[i].start();
			}

		}catch(IOException e) {

		}
	}


}
