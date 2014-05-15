package com_sincronia;

public class Servidor {
	private String nome;
	private double saldo;

	public Servidor(String nome) {
		this.nome = nome;
		this.saldo = 0;
	}

	public void deposito(int deposito) {
		this.saldo += deposito;
	}

	public void saque(int saque) {
		this.deposito(-saque);
	}

	public void correcao(int porcentagem) {
		this.saldo *= 1 + porcentagem / 100.0;
	}

	public String toString() {
		return this.nome + " => R$" + this.saldo;
	}
}
