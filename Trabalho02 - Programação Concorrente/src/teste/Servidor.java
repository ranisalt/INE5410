package teste;

public class Servidor {
	
	private double saldo;
	private String nome;
	
	public Servidor(String nome){
		this.nome = nome;
		this.saldo = 0;
	}
	public void depositar (int deposito) {
		System.out.println(this.nome+" depositando o valor R$"+deposito+"\n");
		this.saldo+=deposito;
	}
	
	public void sacar (int saque) {
		System.out.println(this.nome+" sacando o valor R$"+saque+"\n");
		this.saldo-=saque;
	}
	
	public void correcao (int percentagem) {
		System.out.println(this.nome+" corrigindo o saldo em "+percentagem+"%\n");
		this.saldo += (this.saldo/100)*percentagem;
	}
	
	public String toString() {
<<<<<<< HEAD:Trabalho02 - Programação Concorrente/src/banco/Servidor.java
		return this.nome+" R$"+this.saldo;
=======
		return "R$"+this.saldo;
>>>>>>> 9c7dff57780fb8a7b57ac48a3b39e33fdb60e0e5:Trabalho02 - Programação Concorrente/src/teste/Servidor.java
	}
}
