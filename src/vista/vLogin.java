package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class vLogin extends JFrame {

	private JPanel contentPane;
	private JTextField tfUser;
	private JTextField tfPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vLogin frame = new vLogin();
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
	public vLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tfUser = new JTextField();
		tfUser.setBounds(153, 76, 140, 20);
		contentPane.add(tfUser);
		tfUser.setColumns(10);
		
		tfPassword = new JTextField();
		tfPassword.setBounds(153, 136, 140, 20);
		contentPane.add(tfPassword);
		tfPassword.setColumns(10);
		
		JLabel lblUser = new JLabel("User Name");
		lblUser.setBounds(67, 79, 59, 14);
		contentPane.add(lblUser);
		
		JLabel lblPass = new JLabel("Password");
		lblPass.setBounds(67, 139, 46, 14);
		contentPane.add(lblPass);
		
		JButton btnRegistrar = new JButton("Register");
		btnRegistrar.setBounds(258, 190, 89, 23);
		contentPane.add(btnRegistrar);
		
		JButton btinLogin = new JButton("Log in");
		btinLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			 vMunicipios vMuni = new vMunicipios();
			 vMuni.setVisible(true);
			 dispose();
			 
			}
		});
		btinLogin.setBounds(93, 190, 89, 23);
		contentPane.add(btinLogin);
	}
}
