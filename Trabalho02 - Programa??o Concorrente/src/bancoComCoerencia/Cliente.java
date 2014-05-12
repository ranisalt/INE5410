package bancoComCoerencia;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PipedOutputStream;

public class Cliente extends Thread {
	private DataOutputStream saida;
	private int valorSaida;
	private int fim;
	private int limiteOperacoes = 40;

	public Cliente(PipedOutputStream pipesSaida, int valorSaida) {
		this.saida = new DataOutputStream(pipesSaida);
		this.valorSaida = valorSaida;
		this.fim = 0;
	}

	@Override
	public void run() {
		while(this.fim !=this.limiteOperacoes){
			this.enviarValor();
			this.fim++;
		}
		try{

			this.saida.close();

		}catch(Exception e){
			System.out.println("catch close");
		}
	}

	public void enviarValor() {
		try {
			this.saida.write(this.valorSaida);
			this.saida.flush();

		} catch (IOException e) {
			System.out.println("Caiu no catch do cliente");
			e.printStackTrace();
		}
	}
}