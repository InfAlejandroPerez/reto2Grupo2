package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import java.awt.CardLayout;

public class vInfoEspacio extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vInfoEspacio frame = new vInfoEspacio();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public vInfoEspacio() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		JLabel lblNombreEspacio = new JLabel("Nombre:");
		contentPane.add(lblNombreEspacio, "name_2949298489184400");
		
		JLabel lblDescripcion = new JLabel("Descripcion:");
		contentPane.add(lblDescripcion, "name_2949298509750700");
		
		JTextPane textPane = new JTextPane();
		contentPane.add(textPane, "name_2949298532762800");
	}
}
