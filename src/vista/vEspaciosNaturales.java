package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JButton;

public class vEspaciosNaturales extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vEspaciosNaturales frame = new vEspaciosNaturales();
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
	public vEspaciosNaturales() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JList list = new JList();
		list.setBounds(64, 209, 277, -164);
		contentPane.add(list);
		
		JButton btnFavoritos = new JButton("Favoritos");
		btnFavoritos.setBounds(159, 209, 99, 23);
		contentPane.add(btnFavoritos);
		
		JButton btnTopRanking = new JButton("Top Ranking");
		btnTopRanking.setBounds(295, 209, 99, 23);
		contentPane.add(btnTopRanking);
		
		JButton btnAtras = new JButton("Atras");
		btnAtras.setBounds(23, 209, 89, 23);
		contentPane.add(btnAtras);
	}
}
