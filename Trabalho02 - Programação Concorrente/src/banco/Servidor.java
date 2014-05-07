package banco;

public class Servidor {
	
	private ThreadServidor deposito, retira, correcao;
	private String nome;
	
	public Servidor(String nome){
		this.nome = nome;
		this.deposito = new ThreadServidor();
		this.retira = new ThreadServidor();
		this.correcao = new ThreadServidor();
	}
	
	public ThreadServidor getDeposito() {
		return deposito;
	}

	public ThreadServidor getRetira() {
		return retira;
	}

	public ThreadServidor getCorrecao() {
		return correcao;
	}
	
	public void depositar (short deposito) {
		//implementar
	}
	
	public void retirar (short saque) {
		//implementar
	}
	
	public void correcao (short percentagem) {
		//implementar
	}
}
