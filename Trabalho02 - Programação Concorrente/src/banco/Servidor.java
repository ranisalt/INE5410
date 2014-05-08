package banco;

public class Servidor {
	
	private double saldo;
	private String nome;
	
	public Servidor(String nome){
		this.nome = nome;
		this.saldo = 0;
	}
	public void depositar (int deposito) {
		this.saldo+=deposito;
	}
	
	public void sacar (int saque) {
		this.saldo-=saque;
	}
	
	public void correcao (int percentagem) {
		this.saldo += (this.saldo/100)*percentagem;
	}
}
