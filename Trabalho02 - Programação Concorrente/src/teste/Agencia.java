package teste;

public class Agencia {
	private Acao[] acoes = new Acao[3];
	private Servidor servidor;
	
	public Agencia (String nomeServidor, Acao[] acoes) {
		this.servidor = new Servidor(nomeServidor);
		this.acoes = acoes;
		for (int i=0; i < 3; i++) {
			this.acoes[i].adicionarServidor(this.servidor);
		}
	}
	
	public void iniciarOperacoes() {
		for(int i = 0; i < 3; i++){
			this.acoes[i].run();
		}
	}
	
	public Servidor getServidor() {
		return this.servidor;
	}
	
	
}
