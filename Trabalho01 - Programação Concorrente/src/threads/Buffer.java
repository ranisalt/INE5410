package threads;

public class Buffer {

	private int[] posicoes;

	public Buffer() {
		this.posicoes = new int[] {0,0};
	}

	public void escrever(int posicao, int valor) {
		this.posicoes[posicao] = valor;
	}

	public int ler(int posicao) {
		int inteiroLido = this.posicoes[posicao];
		this.posicoes[posicao] = 0;
		return inteiroLido;
	}

	public boolean estaCheio() {
		if(this.posicoes[0]!=0 && this.posicoes[1]!=0) {
			return true;
		}
		return false;
	}

	public boolean estaVazio() {
		if(this.posicoes[0]==0 && this.posicoes[1]==0) {
			return true;
		}
		return false;
	}

	public int posicaoVazia() {
		for(int i = 0 ; i<2 ; i++) {
			if(posicoes[i]==0) {
				return i;
			}
		}
		return -1;
	}
	
	public int posicaoOcupada() {
		for(int i=0 ; i<2 ; i++ ) {
			if(posicoes[i]!=0){
				return i;
			}
		}
		return -1;
	}
}


