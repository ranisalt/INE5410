package bancoComCoerencia;

public class Servidor {
	
	private double saldo;
	private String nome;
	public double correcao;
	
	public Servidor(String nome){
		this.nome = nome;
		this.saldo = 0;
		this.correcao = 0;
	}
	public void depositar (double deposito) {
		this.saldo+=deposito;
	}
	
	public void sacar (int saque) {
		this.saldo-=saque;
	}
	
	public void correcao (int percentagem) {
		correcao += (this.saldo/100)*percentagem;
		this.saldo += (this.saldo/100)*percentagem;
		
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public String toString() {
		return this.nome+" R$"+this.saldo;
	}
}

