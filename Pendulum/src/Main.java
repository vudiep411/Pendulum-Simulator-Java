import javax.swing.JFrame;



public class Main {

	public static void main(String[] args) {
		Pendulum pen = new Pendulum(150);
		JFrame frame = new JFrame("Pendulum Simulator");
		frame.add(pen);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.pack();
	}

}
