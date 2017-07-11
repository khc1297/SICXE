package sicxesm;


import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class SICXESIM {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				// GUI를 실행 시켜줌.
				SimGUI thisClass = new SimGUI();
				thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				thisClass.setVisible(true);
			}
		});
	}
}
