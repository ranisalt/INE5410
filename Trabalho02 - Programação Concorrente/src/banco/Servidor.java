package banco;

public class Servidor {
	
	private ThreadServidor deposito, retira, correcao;
	private double saldo;
	private String nome;
	
	public Servidor(String nome){
		this.nome = nome;
		this.saldo = 0;
		this.deposito = new ThreadServidor("Depósito");
		this.retira = new ThreadServidor("Saque");
		this.correcao = new ThreadServidor("Correção");
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
	
	public void depositar (int deposito) {
		this.saldo+=deposito;
	}
	
	public void retirar (int saque) {
		this.saldo-=saque;
	}
	
	public void correcao (int percentagem) {
		this.saldo += (this.saldo/100)*percentagem;
	}
}
