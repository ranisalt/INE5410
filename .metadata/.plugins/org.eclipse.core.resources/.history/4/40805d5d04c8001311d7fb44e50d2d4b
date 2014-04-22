package coordenadorSimples;

import java.util.concurrent.Future;

public class Escritor extends Thread {
	private Buffer buffer;
	private Coordenador coordenador;
	private boolean concluido;
	private Future f;

	public Escritor (Coordenador coordenador, Buffer buffer, String nome){
		super(nome);
		this.coordenador = coordenador;
		this.buffer = buffer;
		this.concluido = false;
	}
	
	public void recebeFuture(Future f) {
		this.f = f;
	}

	public void run(){
		while(!concluido) {
			if(this.buffer.posicaoVazia() != -1){
				this.escrever();
				this.concluido = true;
				this.coordenador.bufferNaoVazio();
				System.out.println("===========================> "+this.getName()+" chegou ao fim!");
			} else {
				try {
					sleep(6000);
					System.out.println(this.getName()+" foi dormir");
				} catch (InterruptedException e) {
					System.out.println(this.getName()+" foi interrompido");
				}
			}
		}
	}

	public void escrever() {
		int numero = (int)((Math.random()*100) + 1);
		this.buffer.escrever(this.buffer.posicaoVazia(), numero);
		System.out.println(numero+" foi escrito. Pelo "+this.getName());

	}
	
	public boolean estaConcluido() {
		return this.concluido;
	}

	public Future getFuture() {
		return this.f;
	}
}
