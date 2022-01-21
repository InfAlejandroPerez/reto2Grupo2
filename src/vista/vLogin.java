package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;

public class vLogin extends JFrame {

	private JPanel contentPane;
	private JTextField tfUser;
	private JTextField tfPassword;
	private CardLayout cardlayout;
	private final int PUERTO = 4444;
	private final String IP = "127.0.0.1";
	private Socket cliente = null;
	private ObjectInputStream entrada = null;
	private ObjectOutputStream salida = null;

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
		cardlayout = new CardLayout();
		contentPane = new JPanel(cardlayout);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.add(mPanelSelected(1), "login");// , "login"

	}

	public JPanel mPanelSelected(int panel) {

		/*
		 * if(contentPane.getComponentCount() > 1) { contentPane.remove(0); }
		 */

		JPanel panelSeleccionado = null;

		switch (panel) {
		case 1:
			panelSeleccionado = panelLogin();
			break;
		case 2:
			panelSeleccionado = panelRegister();

			break;
		case 3:
			panelSeleccionado = panelMunicipios();
			break;
		case 4:
			panelSeleccionado = panelInfoMunicipios();
			break;
		}

		return panelSeleccionado;

	}

	public JPanel panelLogin() {
		JPanel panelLog = new JPanel();

		panelLog.setBounds(this.getBounds());
		panelLog.setLayout(null);
		panelLog.setBackground(new Color(153, 204, 204));

		tfUser = new JTextField();
		tfUser.setBounds(185, 88, 118, 20);
		panelLog.add(tfUser);
		tfUser.setColumns(10);

		JPasswordField tfPass = new JPasswordField();
		tfPass.setBounds(185, 126, 118, 20);
		panelLog.add(tfPass);
		tfPass.setColumns(10);

		JLabel lblUser = new JLabel("User Name:");
		lblUser.setForeground(new Color(255, 255, 255));
		lblUser.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblUser.setBounds(87, 88, 88, 20);
		panelLog.add(lblUser);

		JLabel lblPass = new JLabel("Password:");
		lblPass.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPass.setForeground(new Color(255, 255, 255));
		lblPass.setBounds(87, 126, 88, 17);
		panelLog.add(lblPass);

		JButton btnLog = new JButton("Log in");
		btnLog.setBackground(new Color(204, 153, 204));
		btnLog.setForeground(new Color(0, 0, 0));
		btnLog.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnLog.setBounds(87, 197, 89, 23);
		btnLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (!(contentPane.getComponentCount() == 1)) {
					contentPane.remove(0);
				}

				try {

					String usuario = tfUser.getText().toString();
					String pass = tfPass.getText().toString();

					if (usuario.equals("") || pass.equals("")) {
						JOptionPane.showMessageDialog(null, "Usuario no encontrado", "Mensaje",
								JOptionPane.WARNING_MESSAGE);
					} else {

						iniciar();
						
						String mensaje = "login " + usuario + "," + pass;
						System.out.println(mensaje);

						salida.writeObject(mensaje);
						salida.flush();

						Object response = null;
						response = entrada.readObject();
						
						cliente.close();
						
						if (response.equals("Login OK")) {

							if (!(contentPane.getComponentCount() == 1)) {
								contentPane.remove(0);
							}
							
							contentPane.add(mPanelSelected(3), "municipios");
							cardlayout.show(contentPane, "municipios");
							
						} else {

							JOptionPane.showMessageDialog(null, response, "Mensaje", JOptionPane.WARNING_MESSAGE);

						}

					}

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		panelLog.add(btnLog);

		JButton btnRegister = new JButton("Register");
		btnRegister.setBackground(new Color(204, 153, 204));
		btnRegister.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnRegister.setBounds(228, 197, 89, 23);
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (!(contentPane.getComponentCount() == 1)) {
					contentPane.remove(0);
				}
				
				contentPane.add(mPanelSelected(2), "register");
				cardlayout.show(contentPane, "register");

			}
		});
		panelLog.add(btnRegister);

		JLabel lblTituloLog = new JLabel("Bienvenido");
		lblTituloLog.setForeground(new Color(255, 255, 255));
		lblTituloLog.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblTituloLog.setBounds(162, 34, 118, 20);
		panelLog.add(lblTituloLog);

		return panelLog;
	}

	public JPanel panelRegister() {
		JPanel panelRegister = new JPanel();

		panelRegister.setBounds(this.getBounds());
		panelRegister.setLayout(null);
		panelRegister.setBackground(new Color(153, 204, 204));

		JLabel lblUserRegister = new JLabel("User:");
		lblUserRegister.setForeground(new Color(255, 255, 255));
		lblUserRegister.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblUserRegister.setBounds(40, 25, 160, 35);
		panelRegister.add(lblUserRegister);

		JLabel lblPassRegister = new JLabel("Password:");
		lblPassRegister.setForeground(new Color(255, 255, 255));
		lblPassRegister.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPassRegister.setBounds(40, 75, 160, 35);
		panelRegister.add(lblPassRegister);

		JLabel lblRepeatPass = new JLabel("Repeat password:");
		lblRepeatPass.setForeground(new Color(255, 255, 255));
		lblRepeatPass.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblRepeatPass.setBounds(40, 125, 160, 35);
		panelRegister.add(lblRepeatPass);

		JTextField textFieldUserRegis = new JTextField();
		textFieldUserRegis.setBounds(220, 25, 160, 35);
		panelRegister.add(textFieldUserRegis);
		textFieldUserRegis.setColumns(10);

		JPasswordField textFieldPass = new JPasswordField();
		textFieldPass.setColumns(10);
		textFieldPass.setBounds(220, 75, 160, 35);
		panelRegister.add(textFieldPass);

		JPasswordField textFieldPassRepeat = new JPasswordField();
		textFieldPassRepeat.setColumns(10);
		textFieldPassRepeat.setBounds(220, 125, 160, 35);
		panelRegister.add(textFieldPassRepeat);

		JButton btnRegister = new JButton("Register");
		btnRegister.setBackground(new Color(204, 153, 204));
		btnRegister.setForeground(new Color(0, 0, 0));
		btnRegister.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnRegister.setBounds(83, 202, 117, 35);
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (!(contentPane.getComponentCount() == 1)) {
					contentPane.remove(0);
				}

				try {

					String usuario = textFieldUserRegis.getText().toString();
					String pass = textFieldPass.getText().toString();
					String passRepe = textFieldPassRepeat.getText().toString();

					if (usuario.equals("") || pass.equals("") || passRepe.equals("")) {
						JOptionPane.showMessageDialog(null, "Los campos no pueden estar vacios", "Mensaje",
								JOptionPane.WARNING_MESSAGE);
					} else {

						iniciar();
						
						String mensaje = "register " + usuario + "," + pass;
						System.out.println(mensaje);

						salida.writeObject(mensaje);
						salida.flush();

						Object response = null;
						response = entrada.readObject();
						
						cliente.close();
						
						if (response.equals("Register OK")) {

							if (!(contentPane.getComponentCount() == 1)) {
								contentPane.remove(0);
							}
							contentPane.add(mPanelSelected(1), "login");
							cardlayout.show(contentPane, "login");

						} else {

							JOptionPane.showMessageDialog(null, response, "Mensaje", JOptionPane.WARNING_MESSAGE);

						}

					}

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		panelRegister.add(btnRegister);

		JButton btnBack = new JButton("Back");
		btnBack.setBackground(new Color(204, 153, 204));
		btnBack.setForeground(new Color(0, 0, 0));
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (!(contentPane.getComponentCount() == 1)) {
					contentPane.remove(0);
				}
				contentPane.add(mPanelSelected(1), "login");
				cardlayout.show(contentPane, "login");

			}
		});
		btnBack.setBounds(220, 202, 117, 35);
		panelRegister.add(btnBack);

		return panelRegister;
	}

	public JPanel panelMunicipios() {
		JPanel panelMunicipios = new JPanel();

		panelMunicipios.setBounds(this.getBounds());
		panelMunicipios.setLayout(null);
		panelMunicipios.setBackground(new Color(153, 204, 204));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(52, 63, 315, 173);
		panelMunicipios.add(scrollPane);

		JList list = new JList();
		scrollPane.setViewportView(list);

		JLabel lblNewLabel = new JLabel("Municipios");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel.setBounds(157, 22, 172, 21);
		panelMunicipios.add(lblNewLabel);
		return panelMunicipios;

	}
	public JPanel panelProvincias() {
		JPanel panelProvincias = new JPanel();

		panelProvincias.setBounds(this.getBounds());
		panelProvincias.setLayout(null);
		panelProvincias.setBackground(new Color(153, 204, 204));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(52, 63, 315, 173);
		panelProvincias.add(scrollPane);

		JList list = new JList();
		scrollPane.setViewportView(list);

		JLabel tituloProvincias = new JLabel("Provincias");
		tituloProvincias.setForeground(new Color(255, 255, 255));
		tituloProvincias.setFont(new Font("Tahoma", Font.BOLD, 17));
		tituloProvincias.setBounds(157, 22, 172, 21);
		panelProvincias.add(tituloProvincias);
		return panelProvincias;

	}


	public JPanel panelInfoMunicipios() {
		JPanel panelInfoMunicipios = new JPanel();

		panelInfoMunicipios.setBounds(this.getBounds());
		panelInfoMunicipios.setLayout(null);
		panelInfoMunicipios.setBackground(new Color(153, 204, 204));

		JLabel lblNombreMunicipio = new JLabel("");
		lblNombreMunicipio.setBounds(32, 22, 163, 39);
		lblNombreMunicipio.add(panelInfoMunicipios);

		JLabel lblCO = new JLabel("COmgm3:");
		lblCO.setForeground(new Color(255, 255, 255));
		lblCO.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCO.setBounds(32, 98, 57, 14);
		panelInfoMunicipios.add(lblCO);

		JLabel lblCO8 = new JLabel("CO8hmgm3:");
		lblCO8.setForeground(new Color(255, 255, 255));
		lblCO8.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCO8.setBounds(34, 137, 69, 14);
		panelInfoMunicipios.add(lblCO8);

		JLabel lblNO = new JLabel("NOgm3:");
		lblNO.setForeground(new Color(255, 255, 255));
		lblNO.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNO.setBounds(32, 180, 46, 14);
		panelInfoMunicipios.add(lblNO);

		JLabel lblNO2 = new JLabel("NO2gm3:");
		lblNO2.setForeground(new Color(255, 255, 255));
		lblNO2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNO2.setBounds(149, 98, 46, 14);
		panelInfoMunicipios.add(lblNO2);

		JLabel lblNOX = new JLabel("NOXgm3:");
		lblNOX.setForeground(new Color(255, 255, 255));
		lblNOX.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNOX.setBounds(279, 137, 57, 14);
		panelInfoMunicipios.add(lblNOX);

		JLabel lblPM10 = new JLabel("PM10gm3:");
		lblPM10.setForeground(new Color(255, 255, 255));
		lblPM10.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPM10.setBounds(149, 137, 57, 14);
		panelInfoMunicipios.add(lblPM10);

		JLabel lblPM25 = new JLabel("PM25gm3:");
		lblPM25.setForeground(new Color(255, 255, 255));
		lblPM25.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPM25.setBounds(149, 180, 57, 14);
		panelInfoMunicipios.add(lblPM25);

		JLabel lblSO = new JLabel("SO2gm3:");
		lblSO.setForeground(new Color(255, 255, 255));
		lblSO.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSO.setBounds(279, 98, 46, 14);
		panelInfoMunicipios.add(lblSO);

		JLabel lblHora = new JLabel("Hora:");
		lblHora.setForeground(new Color(255, 255, 255));
		lblHora.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblHora.setBounds(249, 47, 46, 14);
		panelInfoMunicipios.add(lblHora);

		JLabel lblNewLabel = new JLabel("CambiarNombre");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(279, 47, 80, 14);
		panelInfoMunicipios.add(lblNewLabel);

		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setForeground(new Color(255, 255, 255));
		lblFecha.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFecha.setBounds(249, 22, 46, 14);
		panelInfoMunicipios.add(lblFecha);

		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(281, 22, 78, 14);
		panelInfoMunicipios.add(lblNewLabel_1);

		JButton btnEspacios = new JButton("Espacios Naturales");
		btnEspacios.setBounds(279, 215, 125, 23);
		panelInfoMunicipios.add(btnEspacios);

		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2.setBounds(79, 98, 46, 14);
		panelInfoMunicipios.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_3.setBounds(93, 137, 46, 14);
		panelInfoMunicipios.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("New label");
		lblNewLabel_4.setForeground(new Color(255, 255, 255));
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_4.setBounds(79, 180, 46, 14);
		panelInfoMunicipios.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("New label");
		lblNewLabel_5.setForeground(new Color(255, 255, 255));
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_5.setBounds(194, 98, 46, 14);
		panelInfoMunicipios.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("New label");
		lblNewLabel_6.setForeground(new Color(255, 255, 255));
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_6.setBounds(205, 137, 46, 16);
		panelInfoMunicipios.add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("New label");
		lblNewLabel_7.setForeground(new Color(255, 255, 255));
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_7.setBounds(205, 180, 46, 14);
		panelInfoMunicipios.add(lblNewLabel_7);

		JLabel lblNewLabel_8 = new JLabel("New label");
		lblNewLabel_8.setForeground(new Color(255, 255, 255));
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_8.setBounds(325, 98, 46, 14);
		panelInfoMunicipios.add(lblNewLabel_8);

		JLabel lblNewLabel_9 = new JLabel("New label");
		lblNewLabel_9.setForeground(new Color(255, 255, 255));
		lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_9.setBounds(325, 137, 46, 14);
		panelInfoMunicipios.add(lblNewLabel_9);

		JButton btnBackMunicipios = new JButton("Back");
		lblCO8.setForeground(new Color(255, 255, 255));
		lblCO8.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnBackMunicipios.setBounds(14, 215, 89, 23);
		panelInfoMunicipios.add(btnBackMunicipios);

		return panelInfoMunicipios;
	}

	public void iniciar() {

		try {
			// necesitamos una IP y un PUERTO para establecer la comunicacion
			cliente = new Socket(IP, PUERTO);
			System.out.println("Conexión establecida con el servidor");
			salida = new ObjectOutputStream(cliente.getOutputStream());
			entrada = new ObjectInputStream(cliente.getInputStream());

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
