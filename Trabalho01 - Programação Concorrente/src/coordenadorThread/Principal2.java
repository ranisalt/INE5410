package coordenadorThread;

public class Principal2 {
	
	public static void main(String[] args) {
		Buffer buffer = new Buffer();
		CoordenadorThread coordenador = new CoordenadorThread(buffer);
		Escritor [] arrayEscritores = new Escritor[101];
		Leitor [] arrayLeitores = new Leitor[100];
		
		
		for(int i = 0 ; i < 100 ; i++) {
			Escritor escritor = new Escritor(buffer, "Escritor"+i);
			arrayEscritores[i] = escritor;
			Leitor leitor = new Leitor(buffer, "Leitor"+i);
			arrayLeitores[i] = leitor;
		}
		coordenador.adicionarEscritores(arrayEscritores);
		coordenador.adicionarLeitores(arrayLeitores);
		
		coordenador.run();
	}
}
