package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;

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
		list.setBounds(38, 46, 325, 148);
		contentPane.add(list);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(24, 216, 89, 23);
		contentPane.add(btnBack);
		
		JButton b = new JButton("Favorites");
		b.setBounds(161, 216, 100, 23);
		contentPane.add(b);
		
		JButton btnTopRanking = new JButton("Top Ranking");
		btnTopRanking.setBounds(302, 216, 106, 23);
		contentPane.add(btnTopRanking);
		
		JLabel lblTitleEspacios = new JLabel("Espacios Naturales");
		lblTitleEspacios.setBounds(161, 11, 100, 24);
		contentPane.add(lblTitleEspacios);
	}
}
