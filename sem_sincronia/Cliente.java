package sem_sincronia;

import java.io.IOException;
import java.io.PipedOutputStream;
import java.util.List;

public class Cliente extends Thread {
	private List<PipedOutputStream> saidas;
	private int val;

	public Cliente(List<PipedOutputStream> list, int val) {
		this.saidas = list;
		this.val = val;
	}

	public void run() {
		for (int i = 0; i < 40; ++i)
			this.enviar();
		try {
			for (int i = 0; i < 3; i++)
				this.saidas.get(i).close();
		} catch (Exception e) {
			System.out.println("==> Catch " + e.getClass().getName() + " em "
					+ this.getName() + "::run()");
		}
	}

	public void enviar() {
		try {
			for (PipedOutputStream p : saidas) {
				p.write(this.val);
				p.flush();
			}
		} catch (IOException e) {
			System.err.println("==> Catch IOException em " + this.getName()
					+ "::enviar()");
			e.printStackTrace();
		}
	}
}
