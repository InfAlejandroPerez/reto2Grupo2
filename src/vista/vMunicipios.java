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
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
		LblTitleMunicipios.setBounds(167, 24, 70, 29);
		contentPane.add(LblTitleMunicipios);
		
		 list = new JList();
		 list.addMouseListener(new MouseAdapter() {
		 	@Override
		 	public void mouseClicked(MouseEvent e) {
		 	}
		 });
		list.setBounds(61, 64, 291, 145);
		contentPane.add(list);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(10, 220, 70, 30);
		contentPane.add(btnBack);
		
	}
	public static void rellenarLista() {
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		 ArrayList<String> listaMunicipios = new ArrayList<String>();
		listaMunicipios.add("hola");
		listaMunicipios.add("adios");
	
		for(int i=0; i<listaMunicipios.size(); i++) {

		    listModel.add(i, listaMunicipios.get(i).toString());
		    
		    
		    
		   
		}
	
		list.setModel(listModel);
	}
}
