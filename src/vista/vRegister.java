package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class vRegister extends JFrame {

	private JPanel contentPane;
	private static JTextField tfRegisterUser;
	private static JTextField tfRegisterPass;
	private static JTextField tfRegisterComPass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vRegister frame = new vRegister();
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
	public vRegister() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tfRegisterUser = new JTextField();
		tfRegisterUser.setBounds(175, 63, 119, 20);
		contentPane.add(tfRegisterUser);
		tfRegisterUser.setColumns(10);
		
		tfRegisterPass = new JTextField();
		tfRegisterPass.setBounds(175, 107, 119, 20);
		contentPane.add(tfRegisterPass);
		tfRegisterPass.setColumns(10);
		
		tfRegisterComPass = new JTextField();
		tfRegisterComPass.setBounds(175, 150, 119, 20);
		contentPane.add(tfRegisterComPass);
		tfRegisterComPass.setColumns(10);
		
		JLabel lblReUser = new JLabel("User Name");
		lblReUser.setBounds(83, 66, 63, 14);
		contentPane.add(lblReUser);
		
		JLabel lblRePass = new JLabel("Password");
		lblRePass.setBounds(97, 110, 46, 14);
		contentPane.add(lblRePass);
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password");
		lblConfirmPassword.setBounds(66, 153, 94, 14);
		contentPane.add(lblConfirmPassword);
		
		JButton btnConfirmReg = new JButton("Register");
		btnConfirmReg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!tfRegisterPass.getText().toString().equals("")){
					if(tfRegisterPass.getText().toString().equals(tfRegisterComPass.getText().toString())){

						vLogin log =new vLogin();
						log.setVisible(true);
						dispose();
					}else {
						JOptionPane.showMessageDialog(null,"Las contraseñas deben coincidir","Aviso",JOptionPane.ERROR_MESSAGE);
					}
				
				}else {
					JOptionPane.showMessageDialog(null,"Los campos no pueden estar vacios","Aviso",JOptionPane.ERROR_MESSAGE);
				}

			
			}
		});
		btnConfirmReg.setBounds(175, 196, 89, 23);
		contentPane.add(btnConfirmReg);
	
	}

}
