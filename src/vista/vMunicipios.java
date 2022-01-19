package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextPane;
import java.awt.GridLayout;
import java.awt.Font;

public class vMunicipios extends JFrame {

	private JPanel contentPane;
	static JList list;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vMunicipios frame = new vMunicipios();
					rellenarLista();
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
	public vMunicipios() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel LblTitleMunicipios = new JLabel("Municipios");
		LblTitleMunicipios.setFont(new Font("Tahoma", Font.PLAIN, 15));
		LblTitleMunicipios.setBounds(182, 31, 70, 29);
		contentPane.add(LblTitleMunicipios);
		
		 list = new JList();
		list.setBounds(88, 71, 253, 127);
		contentPane.add(list);
		
	}
	public static void rellenarLista() {
		DefaultListModel listModel = new DefaultListModel();
		 ArrayList<String> listaMunicipios = new ArrayList<String>();
		listaMunicipios.add("hola");
		listaMunicipios.add("adios");
	
		for(int i=0; i<listaMunicipios.size(); i++) {

		    listModel.add(i, listaMunicipios.get(i).toString());
		    
		}
	
		list.setModel(listModel);
	}
}
