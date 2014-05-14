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

	@Override
	public void run() {

		for (int i = 0; i < 40; ++i) {
			this.enviar();
		}
		try {
			for (int i = 0; i < 3; i++) {
				this.saidas.get(i).close();
			}
		} catch (Exception e) {
			System.out.println("");
		}
	}

	public void enviar() {
		try {
			for (PipedOutputStream p : saidas) {
				p.write(this.val);
				p.flush();
			}
		} catch (IOException e) {
			System.out.println("Caiu no catch do cliente");
			e.printStackTrace();
		}
	}
}
