package Servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	
	private final int puerto= 5000;
	
	public void iniciar() {
		ServerSocket servidor= null;
		
		Socket cliente = null;
		ObjectInputStream entrada = null;
		ObjectOutputStream salida =null;
		
		
		try {
			servidor= new ServerSocket(puerto);
			System.out.println("servidor iniciado");
			cliente= servidor.accept();
			
			
			entrada = new ObjectInputStream(cliente.getInputStream());
			salida = new ObjectOutputStream(cliente.getOutputStream());
			System.out.println("cliente conectado");
			//EnviarThread e = new EnviarThread();
			
			
			String linea = (String) entrada.readObject();
			System.out.println("recibo: "+ linea);
			salida.writeObject("bienvenido cliente");
		
			
		}catch(IOException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void  main (String[] args) {
		Servidor s = new Servidor();
		s.iniciar();
	}
}
