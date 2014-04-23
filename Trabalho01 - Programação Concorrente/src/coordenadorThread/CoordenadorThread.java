package coordenadorThread;

public class CoordenadorThread extends Thread {

	private Escritor[] escritores;
	private Leitor[] leitores;
	private Buffer buffer;
	public void run(){
		while(!this.fim()) {		
			if(buffer.naoCheio()) {
				this.bufferNaoCheio();
			}
			if(buffer.naoVazio()) {
				this.bufferNaoVazio();
			}
			try {
				sleep(150);
			} catch (InterruptedException e) {

			}
		}
	}
	
	public CoordenadorThread(Buffer buffer) {
		this.buffer = buffer;
	}

	public void adicionarEscritores(Escritor[] escritores) {
		this.escritores = escritores;
	}

	public void adicionarLeitores(Leitor[] leitores) {
		this.leitores = leitores;
	}

	public Escritor[] getEscritores() {
		return this.escritores;
	}

	public Leitor[] getLeitores() {
		return this.leitores;
	}
	
	public boolean fim() {
		Leitor leitorNaoConcluido = this.getLeitorNaoConcluido();
		if(leitorNaoConcluido == null) {
			return true;
		}
		return false;
	}

	public void bufferNaoVazio() {
		Leitor leitorNaoConcluido = this.getLeitorNaoConcluido();
		if(leitorNaoConcluido.getState().toString().equalsIgnoreCase("sleep")) {
			leitorNaoConcluido.interrupt();
		}
		leitorNaoConcluido.run();
		

	}

	public void bufferNaoCheio() {
		Escritor escritorNaoConcluido = this.getEscritorNaoConcluido();
		try{
			if(escritorNaoConcluido.getState().toString().equalsIgnoreCase("sleep")) {
				escritorNaoConcluido.interrupt();
			}
			escritorNaoConcluido.run();
		} catch(NullPointerException e) {

		}

	}



	public Escritor getEscritorNaoConcluido() {
		for(int i=0 ; i < 100 ; i++) {
			if(!this.escritores[i].estaConcluido()) {
				return this.escritores[i];
			}
		}
		return null;
	}

	public Leitor getLeitorNaoConcluido() {
		for(int i=0 ; i < 100 ; i++){
			if(!this.leitores[i].estaConcluido()) {
				return this.leitores[i];
			}	
		}
		return null;

	}

}
