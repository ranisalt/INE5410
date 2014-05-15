package com_sincronia;

import java.io.IOException;
import java.io.PipedInputStream;

public class Atendente extends Thread {
	private Servidor servidor;
	private PipedInputStream input;
	private char op;

	public Atendente(PipedInputStream in, char op) {
		this.input = in;
		this.op = op;
	}

	public void run() {
		try {
			int v;
			switch (this.op) {
			case 'c':
				while ((v = this.ler()) != -1) {
					this.servidor.correcao(v);
				}
				break;

			case 'd':
				while ((v = this.ler()) != -1) {
					this.servidor.deposito(v);
				}
				break;
			case 'r':
				while ((v = this.ler()) != -1) {
					this.servidor.saque(v);
				}
				break;
			}
			this.input.close();
		} catch (Exception e) {
			System.out.println("==> Catch " + e.getClass().getName() + " em " + this.getName() + "::run()");
		}
	}

	public int ler() {
		try {
			return input.read();
		} catch (IOException e) {
			System.out.println("==> Catch " + e.getClass().getName() + " em " + this.getName() + "::ler()");
			e.printStackTrace();
		}
		return 0;
	}

	public void setServidor(Servidor servidor) {
		this.servidor = servidor;
	}

	public Servidor getServidor() {
		return this.servidor;
	}
}
