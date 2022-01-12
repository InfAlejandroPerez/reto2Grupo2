package Cliente;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Cliente {
		
	
	private final int puerto =5000;
	private final String IP= "127.0.0.1";
	
	public void iniciar() {
		Socket cliente = null;
		ObjectInputStream entrada = null;
		ObjectOutputStream salida =null;
		
		try {
			 cliente = new Socket(IP,puerto);
			
			System.out.println("conexion establecida con el servidor");
			salida = new ObjectOutputStream(cliente.getOutputStream());
			entrada = new ObjectInputStream(cliente.getInputStream());
			salida.writeObject("hey mozo im cliente");
			String linea = (String) entrada.readObject();
			System.out.println("recibido mike"+ linea);
			
			
			
		}catch(Exception e){
			e.printStackTrace();
			
		}
	}
	
	
	
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Cliente c = new Cliente();
		c.iniciar();
	}

}
