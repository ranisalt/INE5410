package bancoComCoerencia;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PipedInputStream;

public class Operacao extends Thread {
	private Servidor[] servidores;
	private PipedInputStream input;
	private String acao;
	int operacoesFeitas = 0;
	int servidor;

	public Operacao(PipedInputStream in, String acao, int servidor)  {
		this.acao = acao.toLowerCase();
		this.input = in;
		this.servidor = servidor;

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
					this.servidores[servidor].depositar(deposito);
					System.out.println(this.servidores[servidor].toString()+ " depositou R$"+deposito);
					this.operacoesFeitas++;
				}
				break;
			case "saque": 
				while((saque=this.lerInput()) != -1){
					this.servidores[servidor].sacar(saque);
					System.out.println(this.servidores[servidor].toString()+ " sacou R$"+saque);
					this.operacoesFeitas++;
				}
				break;
			case "correcao": 
					while((correcao=this.lerInput()) != -1){
						if(this.servidores[servidor].getNome().equalsIgnoreCase("servidor 0")) {
							this.servidores[servidor].correcao(correcao);
						}else {
							this.servidores[servidor].depositar((this.servidores[0].correcao)/40);
						
						}
						System.out.println(this.servidores[servidor].toString()+ " corrigiu "+correcao+"%");
						this.operacoesFeitas++;
					}
				

					break;
				}
			this.input.close();
			
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
