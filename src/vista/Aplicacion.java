package vista;

import java.awt.EventQueue;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import Handler.JSONHandler;
import modelo.Datosdiarios;
import modelo.EspaciosNaturales;
import modelo.Estaciones;
import modelo.Municipios;
import modelo.Provincia;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JEditorPane;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;

public class Aplicacion extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String provSelec = null;
	private String munSelec = null;
	private String CodMunSelec;
	private String estSelec;
	private int espSelected;
	private JPanel contentPane;
	private JTextField tfUser;
	private CardLayout cardlayout;
	private final int PUERTO = 4444;
	private final String IP = "localhost";
	private Socket cliente = null;
	private ArrayList<Provincia> listaProv = new ArrayList<>();
	private ArrayList<Datosdiarios> listaDatosDiarios = new ArrayList<>();
	private ArrayList<Municipios> listaMun = new ArrayList<>();
	private ArrayList<Estaciones> listaEst = new ArrayList<>();
	private ArrayList<EspaciosNaturales> listaEspacios = new ArrayList<>();
	private ArrayList<EspaciosNaturales> espacioInfo = new ArrayList<>();
	private ObjectInputStream entrada = null;
	private ObjectOutputStream salida = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Aplicacion frame = new Aplicacion();
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
	public Aplicacion() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		cardlayout = new CardLayout();
		contentPane = new JPanel(cardlayout);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.add(mPanelSelected(1), "login");

	}

	public JPanel mPanelSelected(int panel) {

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
			panelSeleccionado = panelInfoEstacion();
			break;
		case 5:
			panelSeleccionado = panelProvincias();
			break;
		case 6:
			panelSeleccionado = panelEstaciones();
			break;
		case 7:
			panelSeleccionado = panelEspacios();
			break;
		case 8:
			panelSeleccionado = panelinfoEspacios();
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
					String valorPass = new String(tfPass.getPassword());

					if (usuario.equals("") || valorPass.equals("")) {
						JOptionPane.showMessageDialog(null, "Usuario no encontrado", "Mensaje",
								JOptionPane.WARNING_MESSAGE);
					} else {

						iniciar();

						String mensaje = "login " + usuario + "," + valorPass;
						System.out.println(mensaje);

						salida.writeObject(mensaje);
						salida.flush();

						String response = (String) entrada.readObject();

						cliente.close();

						if (response.split(";")[0].equals("Login OK")) {

							if (!(contentPane.getComponentCount() == 1)) {
								contentPane.remove(0);
							}

							contentPane.add(mPanelSelected(5), "provincias");
							cardlayout.show(contentPane, "provincias");

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
					String valorPass = new String(textFieldPass.getPassword());
					String valorPassRrepe = new String(textFieldPassRepeat.getPassword());

					if (usuario.equals("") || valorPass.equals("") || valorPassRrepe.equals("")) {
						JOptionPane.showMessageDialog(null, "Los campos no pueden estar vacios", "Mensaje",
								JOptionPane.WARNING_MESSAGE);

					} else if (!valorPass.equals(valorPassRrepe)) {

						JOptionPane.showMessageDialog(null, "Los campos deben coincidir", "Mensaje",
								JOptionPane.WARNING_MESSAGE);

					} else {

						iniciar();

						String mensaje = "register " + usuario + "," + valorPass;
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

		try {

			iniciar();

			String mensaje = "MUNICIPIOCODPROV " + provSelec;
			String response = null;
			JsonArray json = null;
			Iterator<?> iterator = null;

			System.out.println(mensaje);

			salida.writeObject(mensaje);
			salida.flush();

			response = (String) entrada.readObject();

			listaMun.clear();

			json = JSONHandler.readJSON(response).getAsJsonObject().get("data").getAsJsonArray();

			for (int i = 0; i < json.size(); i++) {
				Municipios municipio = new Municipios();
				JsonObject obj = json.get(i).getAsJsonObject();

				municipio.setCodMunicipio(obj.get("codMunicipio").getAsString());
				municipio.setNombre(obj.get("nombre").getAsString());
				municipio.setDescripcion(obj.get("descripcion").getAsString());


				listaMun.add(municipio);
			}

			cliente.close();

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		JPanel panelMunicipios = new JPanel();

		panelMunicipios.setBounds(this.getBounds());
		panelMunicipios.setLayout(null);
		panelMunicipios.setBackground(new Color(153, 204, 204));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(61, 64, 291, 145);
		panelMunicipios.add(scrollPane);

		DefaultListModel<String> listModel = new DefaultListModel<String>();
		JList<String> listMun = new JList<String>();
		listMun.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				munSelec = listaMun.get(listMun.getSelectedIndex()).getNombre();
				CodMunSelec = listaMun.get(listMun.getSelectedIndex()).getCodMunicipio();

				if (!(contentPane.getComponentCount() == 1)) {
					contentPane.remove(0);
				}
				contentPane.add(mPanelSelected(6), "estaciones");
				cardlayout.show(contentPane, "estacioones");

			}
		});
		scrollPane.setViewportView(listMun);

		for (int i = 0; i < listaMun.size(); i++) {

			listModel.add(i, listaMun.get(i).getNombre().toString());

		}

		listMun.setModel(listModel);

		JLabel lblNewLabel = new JLabel("Municipios");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel.setBounds(157, 22, 172, 21);
		panelMunicipios.add(lblNewLabel);

		JButton btnInfoMunicipio = new JButton("Mas informaci\u00F3n");
		btnInfoMunicipio.setBounds(154, 227, 123, 23);
		btnInfoMunicipio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Municipios municipio = listaMun.get(listMun.getSelectedIndex());
				
				if (municipio.getDescripcion() != null) {
					String body = "<html><body>" + "<h3>" + municipio.getNombre() + "</h3><p style='width: 350px'>"
							+ municipio.getDescripcion().replaceAll("\\<.*?\\>", "") + "</p></body></html>";
					JOptionPane.showMessageDialog(null, body, "Información Adicional", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "No hay info adicional de este espacio!", "Información Adicional", JOptionPane.INFORMATION_MESSAGE);
				}

			}
		});
		panelMunicipios.add(btnInfoMunicipio);

		JButton btnBack = new JButton("Back");
		btnBack.setBounds(10, 220, 70, 30);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (!(contentPane.getComponentCount() == 1)) {
					contentPane.remove(0);
				}
				contentPane.add(mPanelSelected(5), "provincias");
				cardlayout.show(contentPane, "provincias");

			}
		});
		panelMunicipios.add(btnBack);

		return panelMunicipios;

	}

	public JPanel panelProvincias() {

		try {

			iniciar();

			String mensaje = "Provincia ";
			String response = null;
			JsonArray json = null;
			Iterator<?> iterator = null;

			System.out.println(mensaje);

			salida.writeObject(mensaje);
			salida.flush();

			response = (String) entrada.readObject();

			listaProv.clear();

			json = JSONHandler.readJSON(response).getAsJsonObject().get("data").getAsJsonArray();

			for (int i = 0; i < json.size(); i++) {
				Provincia provincia = new Provincia();
				JsonObject obj = json.get(i).getAsJsonObject();

				provincia.setCodProvincia(obj.get("codProvincia").getAsString());
				provincia.setNombre(obj.get("nombre").getAsString());

				listaProv.add(provincia);
			}

			cliente.close();

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		JPanel panelProvincias = new JPanel();

		panelProvincias.setBounds(this.getBounds());
		panelProvincias.setLayout(null);
		panelProvincias.setBackground(new Color(153, 204, 204));

		JScrollPane scrollPaneProv = new JScrollPane();
		scrollPaneProv.setBounds(52, 63, 315, 173);
		panelProvincias.add(scrollPaneProv);

		DefaultListModel<String> listModel = new DefaultListModel<String>();
		JList<String> listProv = new JList<String>();
		listProv.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				provSelec = listaProv.get(listProv.getSelectedIndex()).getCodProvincia();

				if (!(contentPane.getComponentCount() == 1)) {
					contentPane.remove(0);
				}
				contentPane.add(mPanelSelected(3), "municipios");
				cardlayout.show(contentPane, "municipios");

			}
		});
		scrollPaneProv.setViewportView(listProv);

		for (int i = 0; i < listaProv.size(); i++) {

			listModel.add(i, listaProv.get(i).getNombre().toString());

		}

		listProv.setModel(listModel);

		JLabel tituloProvincias = new JLabel("Provincias");
		tituloProvincias.setForeground(new Color(255, 255, 255));
		tituloProvincias.setFont(new Font("Tahoma", Font.BOLD, 17));
		tituloProvincias.setBounds(157, 22, 172, 21);

		panelProvincias.add(tituloProvincias);

		return panelProvincias;

	}

	public JPanel panelInfoEstacion() {

		try {

			iniciar();

			String mensaje = "DatosEstacion " + estSelec;
			String response = null;
			JsonArray json = null;
			Iterator<?> iterator = null;

			System.out.println(mensaje);

			salida.writeObject(mensaje);
			salida.flush();

			response = (String) entrada.readObject();

			listaDatosDiarios.clear();

			json = JSONHandler.readJSON(response).getAsJsonObject().get("data").getAsJsonArray();

			for (int i = 0; i < json.size(); i++) {
				Datosdiarios datosDiarios = new Datosdiarios();
				JsonObject obj = json.get(i).getAsJsonObject();

				if (!obj.get("date").getAsString().equals("null"))
					datosDiarios.setDate(obj.get("date").getAsString());
				if (!obj.get("comgm3").getAsString().equals("null"))
					datosDiarios.setComgm3(obj.get("comgm3").getAsBigDecimal());
				if (!obj.get("co8hmgm3").getAsString().equals("null"))
					datosDiarios.setCo8hmgm3(obj.get("co8hmgm3").getAsBigDecimal());
				if (!obj.get("nogm3").getAsString().equals("null"))
					datosDiarios.setNogm3(obj.get("nogm3").getAsBigDecimal());
				if (!obj.get("noxgm3").getAsString().equals("null"))
					datosDiarios.setNoxgm3(obj.get("noxgm3").getAsBigDecimal());
				if (!obj.get("pm10gm3").getAsString().equals("null"))
					datosDiarios.setPm10gm3(obj.get("pm10gm3").getAsBigDecimal());
				if (!obj.get("pm25gm3").getAsString().equals("null"))
					datosDiarios.setPm25gm3(obj.get("pm25gm3").getAsBigDecimal());
				if (!obj.get("s2gm3").getAsString().equals("null"))
					datosDiarios.setS2gm3(obj.get("s2gm3").getAsBigDecimal());
				if (!obj.get("no2gm3").getAsString().equals("null"))
					datosDiarios.setNo2gm3(obj.get("no2gm3").getAsBigDecimal());

				listaDatosDiarios.add(datosDiarios);
			}

			cliente.close();

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		JPanel panelInfoEstaciones = new JPanel();

		panelInfoEstaciones.setBounds(this.getBounds());
		panelInfoEstaciones.setLayout(null);
		panelInfoEstaciones.setBackground(new Color(153, 204, 204));

		JLabel lblNombreEstacion = new JLabel("");
		lblNombreEstacion.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNombreEstacion.setBounds(14, 22, 226, 58);
		panelInfoEstaciones.add(lblNombreEstacion);

		JLabel lblCO = new JLabel("COmgm3:");
		lblCO.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCO.setBounds(14, 98, 75, 14);
		panelInfoEstaciones.add(lblCO);

		JLabel lblCO8 = new JLabel("CO8hmgm3:");
		lblCO8.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCO8.setBounds(14, 137, 75, 14);
		panelInfoEstaciones.add(lblCO8);

		JLabel lblNO = new JLabel("NOgm3:");
		lblNO.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNO.setBounds(14, 180, 64, 14);
		panelInfoEstaciones.add(lblNO);

		JLabel lblNO2 = new JLabel("NO2gm3:");
		lblNO2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNO2.setBounds(149, 98, 57, 14);
		panelInfoEstaciones.add(lblNO2);

		JLabel lblNOX = new JLabel("NOXgm3:");
		lblNOX.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNOX.setBounds(291, 138, 57, 14);
		panelInfoEstaciones.add(lblNOX);

		JLabel lblPM10 = new JLabel("PM10gm3:");
		lblPM10.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPM10.setBounds(156, 138, 64, 14);
		panelInfoEstaciones.add(lblPM10);

		JLabel lblPM25 = new JLabel("PM25gm3:");
		lblPM25.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPM25.setBounds(149, 180, 71, 14);
		panelInfoEstaciones.add(lblPM25);

		JLabel lblSO = new JLabel("SO2gm3:");
		lblSO.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSO.setBounds(290, 99, 58, 14);
		panelInfoEstaciones.add(lblSO);

		JLabel lblHora = new JLabel("Hora:");
		lblHora.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblHora.setBounds(250, 66, 64, 14);
		panelInfoEstaciones.add(lblHora);

		JLabel lblinfoHora = new JLabel("");
		lblinfoHora.setBounds(324, 66, 80, 14);
		panelInfoEstaciones.add(lblinfoHora);

		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFecha.setBounds(250, 41, 64, 14);
		panelInfoEstaciones.add(lblFecha);

		JLabel lblinfoFecha = new JLabel("");
		lblinfoFecha.setBounds(324, 41, 78, 14);
		panelInfoEstaciones.add(lblinfoFecha);

		JButton btnEspacios = new JButton("Espacios Naturales");
		btnEspacios.setBounds(250, 215, 154, 23);
		btnEspacios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!(contentPane.getComponentCount() == 1)) {
					contentPane.remove(0);
				}
				contentPane.add(mPanelSelected(7), "Espacio");
				cardlayout.show(contentPane, "Espacio");
			}
		});
		panelInfoEstaciones.add(btnEspacios);

		JLabel infoCO = new JLabel("");
		infoCO.setFont(new Font("Tahoma", Font.PLAIN, 13));
		infoCO.setBounds(79, 98, 60, 14);
		panelInfoEstaciones.add(infoCO);

		JLabel lblinfoCO8 = new JLabel("");
		lblinfoCO8.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblinfoCO8.setBounds(93, 137, 57, 14);
		panelInfoEstaciones.add(lblinfoCO8);

		JLabel lblinfoNO = new JLabel("");
		lblinfoNO.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblinfoNO.setBounds(68, 180, 60, 14);
		panelInfoEstaciones.add(lblinfoNO);

		JLabel infoNO2 = new JLabel("");
		infoNO2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		infoNO2.setBounds(216, 98, 64, 14);
		panelInfoEstaciones.add(infoNO2);

		JLabel lblinfoPM10 = new JLabel("");
		lblinfoPM10.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblinfoPM10.setBounds(223, 138, 57, 14);
		panelInfoEstaciones.add(lblinfoPM10);

		JLabel lblinfoPM25 = new JLabel("");
		lblinfoPM25.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblinfoPM25.setBounds(231, 179, 64, 14);
		panelInfoEstaciones.add(lblinfoPM25);

		JLabel infoSO2 = new JLabel("");
		infoSO2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		infoSO2.setBounds(358, 99, 66, 14);
		panelInfoEstaciones.add(infoSO2);

		JLabel lblinfoNOX = new JLabel("");
		lblinfoNOX.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblinfoNOX.setBounds(358, 138, 66, 14);
		panelInfoEstaciones.add(lblinfoNOX);

		JButton btnBack = new JButton("Back");
		btnBack.setBounds(14, 215, 125, 23);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (!(contentPane.getComponentCount() == 1)) {
					contentPane.remove(0);
				}
				contentPane.add(mPanelSelected(6), "Estaciones");
				cardlayout.show(contentPane, "Estaciones");

			}
		});
		panelInfoEstaciones.add(btnBack);

		for (int i = 0; i < listaDatosDiarios.size(); i++) {
			if (listaDatosDiarios.get(i).getNogm3() != null)
				lblinfoNO.setText(listaDatosDiarios.get(i).getNogm3().toString());
			if (listaDatosDiarios.get(i).getCo8hmgm3() != null)
				lblinfoCO8.setText(listaDatosDiarios.get(i).getCo8hmgm3().toString());
			if (listaDatosDiarios.get(i).getNoxgm3() != null)
				lblinfoNOX.setText(listaDatosDiarios.get(i).getNoxgm3().toString());
			if (listaDatosDiarios.get(i).getPm10gm3() != null)
				lblinfoPM10.setText(listaDatosDiarios.get(i).getPm10gm3().toString());
			if (listaDatosDiarios.get(i).getNo2gm3() != null)
				infoNO2.setText(listaDatosDiarios.get(i).getNo2gm3().toString());
			if (listaDatosDiarios.get(i).getComgm3() != null)
				infoCO.setText(listaDatosDiarios.get(i).getComgm3().toString());
			if (listaDatosDiarios.get(i).getS2gm3() != null)
				infoSO2.setText(listaDatosDiarios.get(i).getS2gm3().toString());
			if (listaDatosDiarios.get(i).getPm25gm3() != null)
				lblinfoPM25.setText(listaDatosDiarios.get(i).getPm25gm3().toString());
			if (listaDatosDiarios.get(i).getDate() != null)
				lblinfoFecha.setText(listaDatosDiarios.get(i).getDate().toString());
		}

		return panelInfoEstaciones;
	}

	public JPanel panelEstaciones() {

		try {

			iniciar();

			String mensaje = "EstacionesCodMun " + munSelec;

			String response = null;
			JsonArray json = null;
			Iterator<?> iterator = null;

			System.out.println(mensaje);

			salida.writeObject(mensaje);
			salida.flush();

			response = (String) entrada.readObject();

			listaEst.clear();

			json = JSONHandler.readJSON(response).getAsJsonObject().get("data").getAsJsonArray();

			for (int i = 0; i < json.size(); i++) {
				Estaciones estacion = new Estaciones();
				JsonObject obj = json.get(i).getAsJsonObject();

				estacion.setCodEstacion(obj.get("codEstacion").getAsInt());
				estacion.setNombreEstacion(obj.get("nombre").getAsString());

				listaEst.add(estacion);
			}

			cliente.close();

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		JPanel panelEstaciones = new JPanel();

		panelEstaciones.setBounds(this.getBounds());
		panelEstaciones.setLayout(null);
		panelEstaciones.setBackground(new Color(153, 204, 204));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(61, 64, 291, 145);
		panelEstaciones.add(scrollPane);

		DefaultListModel<String> listModel = new DefaultListModel<String>();
		JList<String> listEstaciones = new JList<String>();
		listEstaciones.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				estSelec = listaEst.get(listEstaciones.getSelectedIndex()).getCodEstacion().toString();

				if (!(contentPane.getComponentCount() == 1)) {
					contentPane.remove(0);
				}
				contentPane.add(mPanelSelected(4), "estacionesinfo");
				cardlayout.show(contentPane, "estacionesinfo");

			}
		});
		scrollPane.setViewportView(listEstaciones);

		if (!listaEst.isEmpty()) {

			for (int i = 0; i < listaEst.size(); i++) {

				listModel.add(i, listaEst.get(i).getNombreEstacion().toString());

			}

		} else {

			JOptionPane.showMessageDialog(null, "Este Municipio no tiene estacion", "Mensaje",
					JOptionPane.INFORMATION_MESSAGE);

		}

		listEstaciones.setModel(listModel);

		JLabel lblEst = new JLabel("Estaciones");
		lblEst.setForeground(new Color(255, 255, 255));
		lblEst.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblEst.setBounds(157, 22, 172, 21);
		panelEstaciones.add(lblEst);

		JButton btnBack = new JButton("Back");
		btnBack.setBounds(10, 220, 70, 30);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (!(contentPane.getComponentCount() == 1)) {
					contentPane.remove(0);
				}
				contentPane.add(mPanelSelected(3), "municipios");
				cardlayout.show(contentPane, "municipios");

			}
		});
		panelEstaciones.add(btnBack);

		return panelEstaciones;

	}

	public JPanel panelinfoEspacios() {
		try {

			iniciar();

			String mensaje = "INFOESPACIO " + String.valueOf(espSelected);
			String response = null;
			JsonArray json = null;
			Iterator<?> iterator = null;

			System.out.println(mensaje);

			salida.writeObject(mensaje);
			salida.flush();

			response = (String) entrada.readObject();

			espacioInfo.clear();

			json = JSONHandler.readJSON(response).getAsJsonObject().get("data").getAsJsonArray();

			for (int i = 0; i < json.size(); i++) {
				EspaciosNaturales espacio = new EspaciosNaturales();
				JsonObject obj = json.get(i).getAsJsonObject();

				espacio.setNombre(obj.get("nombre").getAsString());
				espacio.setDescripcion(obj.get("descripcion").getAsString());

				espacioInfo.add(espacio);
			}

			cliente.close();

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		JPanel panelInfoNaturales = new JPanel();

		panelInfoNaturales.setBounds(this.getBounds());
		panelInfoNaturales.setLayout(null);
		panelInfoNaturales.setBackground(new Color(153, 204, 204));

		JLabel lblTituloInfoEspacio = new JLabel("");
		lblTituloInfoEspacio.setForeground(new Color(255, 255, 255));
		lblTituloInfoEspacio.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblTituloInfoEspacio.setBounds(157, 22, 172, 21);
		panelInfoNaturales.add(lblTituloInfoEspacio);

		JLabel lblDesc = new JLabel("");
		lblDesc.setBounds(50, 71, 293, 124);
		panelInfoNaturales.add(lblDesc);

		JButton btnVolver = new JButton("Cancel");
		btnVolver.setBounds(146, 215, 97, 25);
		btnVolver.setBackground(new Color(204, 153, 204));
		btnVolver.setForeground(new Color(0, 0, 0));
		btnVolver.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (!(contentPane.getComponentCount() == 1)) {
					contentPane.remove(0);
				}
				contentPane.add(mPanelSelected(7), "Espacios");
				cardlayout.show(contentPane, "Espacios");

			}
		});
		panelInfoNaturales.add(btnVolver);

		lblTituloInfoEspacio.setText(espacioInfo.get(0).getNombre());
		
		String body = "<html><body><p style='width: 350px'>"
				+ espacioInfo.get(0).getDescripcion().replaceAll("\\<.*?\\>", "") + "</p></body></html>";
		
		lblDesc.setText(body);

		return panelInfoNaturales;

	}

	public JPanel panelEspacios() {
		try {

			iniciar();

			String mensaje = "ESPACIOSCODMUNI " + CodMunSelec;
			String response = null;
			JsonArray json = null;
			Iterator<?> iterator = null;

			System.out.println(mensaje);

			salida.writeObject(mensaje);
			salida.flush();

			response = (String) entrada.readObject();

			listaEspacios.clear();

			json = JSONHandler.readJSON(response).getAsJsonObject().get("data").getAsJsonArray();

			for (int i = 0; i < json.size(); i++) {
				EspaciosNaturales espacio = new EspaciosNaturales();
				JsonObject obj = json.get(i).getAsJsonObject();

				espacio.setCodEspacio(Integer.parseInt(obj.get("codEspacio").getAsString()));
				espacio.setNombre(obj.get("nombre").getAsString());

				listaEspacios.add(espacio);
			}

			cliente.close();

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		JPanel panelEspacioNatural = new JPanel();

		panelEspacioNatural.setBounds(this.getBounds());
		panelEspacioNatural.setLayout(null);
		panelEspacioNatural.setBackground(new Color(153, 204, 204));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(61, 64, 291, 145);
		panelEspacioNatural.add(scrollPane);

		DefaultListModel<String> listModel = new DefaultListModel<String>();
		JList<String> listEspacios = new JList<String>();

		scrollPane.setViewportView(listEspacios);

		for (int i = 0; i < listaEspacios.size(); i++) {

			listModel.add(i, listaEspacios.get(i).getNombre().toString());

		}

		listEspacios.setModel(listModel);
//		JList list = new JList();
//		list.setBounds(59, 61, 306, 131);	
//		contentPane.add(list);

		JButton btnConfirmEsp = new JButton("Confirm");
		btnConfirmEsp.setBounds(267, 205, 112, 35);
		btnConfirmEsp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				espSelected = listaEspacios.get(listEspacios.getSelectedIndex()).getCodEspacio();

				if (!(contentPane.getComponentCount() == 1)) {
					contentPane.remove(0);
				}

				contentPane.add(mPanelSelected(8), "infoEspacios");
				cardlayout.show(contentPane, "infoEspacios");

			}
		});
		panelEspacioNatural.add(btnConfirmEsp);

		JButton btnBackEsp = new JButton("Back");
		btnBackEsp.setBounds(29, 205, 106, 35);
		btnBackEsp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (!(contentPane.getComponentCount() == 1)) {
					contentPane.remove(0);
				}
				contentPane.add(mPanelSelected(6), "infoEstacion");
				cardlayout.show(contentPane, "infoEstacion");

			}
		});
		panelEspacioNatural.add(btnBackEsp);

		JLabel lblTituloEspacios = new JLabel("Espacios Naturales");
		lblTituloEspacios.setHorizontalAlignment(SwingConstants.CENTER);
		lblTituloEspacios.setBounds(122, 13, 172, 35);
		panelEspacioNatural.add(lblTituloEspacios);

		return panelEspacioNatural;
	}

	public void iniciar() {

		try {

			cliente = new Socket(IP, PUERTO);
			System.out.println("Conexión establecida con el servidor");
			salida = new ObjectOutputStream(cliente.getOutputStream());
			entrada = new ObjectInputStream(cliente.getInputStream());

		} catch (Exception e) {

		}

	}

}
