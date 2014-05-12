package bancoComCoerencia;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PipedInputStream;

public class Operacao extends Thread {
	private Servidor[] servidores = new Servidor[3];
	private DataInputStream input;
	private String acao;
	private int operacoesFeitas;

	public Operacao(PipedInputStream in, String acao)  {
		this.acao = acao.toLowerCase();
		this.input = new DataInputStream(in);
		this.operacoesFeitas=0;
	}
	@Override
	public void run() {
		try{
			int saque = 0;
			int correcao = 0;
			int deposito = 0;

			switch(this.acao){
			case "deposito": 
				while((deposito=this.lerInput()) != -1){
					this.servidores[0].depositar(deposito);
					this.servidores[1].depositar(deposito);
					this.servidores[2].depositar(deposito);
					this.operacoesFeitas++;
				}
				break;
			case "saque": 
				while((saque=this.lerInput()) != -1){
					this.servidores[0].sacar(saque);
					this.servidores[1].sacar(saque);
					this.servidores[2].sacar(saque);	
					this.operacoesFeitas++;
				}
				break;
			case "correcao": 
				while((correcao=this.lerInput()) != -1){
					this.servidores[0].correcao(correcao);
					this.servidores[1].correcao(correcao);
					this.servidores[2].correcao(correcao);	
					this.operacoesFeitas++;
				}
				break;
			}
			this.input.close();
			System.out.println(this.acao+" encerrados"+this.servidores[0].toString()+"================ COM "+this.operacoesFeitas+" OPERACOES");
			System.out.println(this.acao+" encerrados"+this.servidores[1].toString()+"================ COM "+this.operacoesFeitas+" OPERACOES");
			System.out.println(this.acao+" encerrados"+this.servidores[2].toString()+"================ COM "+this.operacoesFeitas+" OPERACOES");
		}catch(Exception e) {

		}
	}

	public int lerInput() {
		try {
			return input.read();

		} catch (IOException e) {
			System.out.println("exception Thread operacao"+this.getName());
			e.printStackTrace();
		}
		return 0;
	}

	public void adicionarServidor(Servidor[] servidores){
		this.servidores = servidores;
	}

	public Servidor[] getServidor(){
		return this.servidores;
	}


}