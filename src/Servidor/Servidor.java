package Servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import operacionesBD.operaciones;

public class Servidor {

	public void iniciar() {
		
		final String LOGIN = "LOGIN";
		
		ServerSocket servidor = null;
		int puerto = 4444;
		Socket cliente = null;
		ObjectInputStream entrada = null;
		ObjectOutputStream salida = null;
		String message = "";

		String cmd = null;
		String[] params = null;

		try {
			
			servidor = new ServerSocket(puerto);
			System.out.println("Servidor iniciado");
			cliente = servidor.accept();

			entrada = new ObjectInputStream(cliente.getInputStream());
			salida = new ObjectOutputStream(cliente.getOutputStream());
			System.out.println("Cliente conectado");

			message = (String) entrada.readObject();
			
			cmd = message.split(" ")[0];
			params = message.split(" ")[1].split(",");
			
			switch (cmd.toUpperCase()) {
			case LOGIN:
				if (operaciones.validarLogin(params[0], params[1]))
					salida.writeObject("OK");
				else
					salida.writeObject("ERROR");

				break;
			}
			
			entrada.close();
			salida.close();
			cliente.close();

		} catch (IOException e) {
			e.printStackTrace();
		}  catch (ClassNotFoundException e) {
			System.out.println("Error: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			try {
				if (servidor != null)
					servidor.close();
				if (cliente != null)
					cliente.close();
				if (entrada != null)
					entrada.close();
				if (salida != null)
					salida.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	public static void main(String[] args) {
		
		  Servidor s = new Servidor(); 
		  s.iniciar();
		 
	}
}
