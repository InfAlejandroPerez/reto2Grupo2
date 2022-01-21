package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextPane;

public class vInfoMunicipio extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vInfoMunicipio frame = new vInfoMunicipio();
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
	public vInfoMunicipio() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(56, 11, 46, 14);
		contentPane.add(lblNombre);
		
		JLabel lblDescripcion = new JLabel("Descripcion:");
		lblDescripcion.setBounds(56, 176, 95, 14);
		contentPane.add(lblDescripcion);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(115, 176, 270, 68);
		contentPane.add(textPane);
		
		JLabel lblNewLabel = new JLabel("Fecha:");
		lblNewLabel.setBounds(56, 48, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Hora:");
		lblNewLabel_1.setBounds(201, 48, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblCO = new JLabel("COmgm3");
		lblCO.setBounds(56, 116, 46, 14);
		contentPane.add(lblCO);
		
		JLabel lblCOh = new JLabel("COhmgm3");
		lblCOh.setBounds(56, 141, 67, 14);
		contentPane.add(lblCOh);
		
		JLabel lblNOg = new JLabel("NOgm3");
		lblNOg.setBounds(313, 116, 46, 14);
		contentPane.add(lblNOg);
		
		JLabel lblNO2 = new JLabel("NO2gm3");
		lblNO2.setBounds(313, 91, 46, 14);
		contentPane.add(lblNO2);
		
		JLabel lblNOX = new JLabel("NOXgm3");
		lblNOX.setBounds(183, 91, 46, 14);
		contentPane.add(lblNOX);
		
		JLabel lblPM10 = new JLabel("PM10gm3");
		lblPM10.setBounds(183, 116, 46, 14);
		contentPane.add(lblPM10);
		
		JLabel lblPM25 = new JLabel("PM25gm3");
		lblPM25.setBounds(183, 141, 46, 14);
		contentPane.add(lblPM25);
		
		JLabel lblSO = new JLabel("SO2gm3");
		lblSO.setBounds(56, 91, 46, 14);
		contentPane.add(lblSO);
		
		JLabel infoSO = new JLabel("New label");
		infoSO.setBounds(105, 91, 46, 14);
		contentPane.add(infoSO);
		
		JLabel infoCO = new JLabel("New label");
		infoCO.setBounds(105, 116, 46, 14);
		contentPane.add(infoCO);
		
		JLabel infoCOh = new JLabel("New label");
		infoCOh.setBounds(115, 141, 46, 14);
		contentPane.add(infoCOh);
		
		JLabel infoNOX = new JLabel("New label");
		infoNOX.setBounds(242, 91, 46, 14);
		contentPane.add(infoNOX);
		
		JLabel infoPM10 = new JLabel("New label");
		infoPM10.setBounds(242, 116, 46, 14);
		contentPane.add(infoPM10);
		
		JLabel infoPM25 = new JLabel("New label");
		infoPM25.setBounds(239, 141, 46, 14);
		contentPane.add(infoPM25);
		
		JLabel infoNO2 = new JLabel("New label");
		infoNO2.setBounds(361, 91, 46, 14);
		contentPane.add(infoNO2);
		
		JLabel infoNO = new JLabel("New label");
		infoNO.setBounds(361, 116, 46, 14);
		contentPane.add(infoNO);
		
		JLabel infoFecha = new JLabel("New label");
		infoFecha.setBounds(105, 48, 46, 14);
		contentPane.add(infoFecha);
		
		JLabel infoHora = new JLabel("New label");
		infoHora.setBounds(242, 48, 46, 14);
		contentPane.add(infoHora);
		
		JLabel infoNombre = new JLabel("New label");
		infoNombre.setBounds(105, 11, 46, 14);
		contentPane.add(infoNombre);
	}

}
