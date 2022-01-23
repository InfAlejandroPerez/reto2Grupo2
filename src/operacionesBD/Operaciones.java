package operacionesBD;

import java.util.ArrayList;
import java.util.Iterator;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import Handler.JSONHandler;
import modelo.Datosdiarios;
import modelo.Estaciones;
import modelo.Municipios;
import modelo.Provincia;
import modelo.Usuarios;

public class Operaciones {

	public static void cargarDatos() {

		// Municipio
		//cargarArrayList(JSONHandler.readMunicipios());

		// Espacios
		//cargarArrayList(JSONHandler.readEspacios());

		// Estaciones
		cargarArrayList(JSONHandler.readEstaciones());

		// Diarios
		//cargarArrayList(JSONHandler.readDatosDiarios());

		// Horarios
		//cargarArrayList(JSONHandler.readDatosHorarios());

	}

	public static void cargarArrayList(ArrayList<?> items) {

		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
		

		for (Object item : items) {
			try {
				
				Transaction tx = session.beginTransaction();
				session.save(item);
				tx.commit();
				
			} catch (Exception e) {
				/*if (e.getCause().toString().contains("MySQLIntegrityConstraintViolationException")) {
					System.out.println("Registro duplicado con PK duplicada en el indice: " + items.indexOf(item) + " se ha saltado");
				}
				else {
					System.out.println(e.getMessage());
				}*/
				
				System.out.println(e.getMessage());
			}
		}
		
		
		session.close();

	}

	public static String validarLogin(String usuario, String pass) {
		String res = "";

		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();

		String hql = "from Usuarios as user where user.nombre = '" + usuario + "'";
		Query q = session.createQuery(hql);
		Usuarios user = (Usuarios) q.uniqueResult();

		if (user != null) {
			if (user.getPassword().equals(pass))
				res = "Login OK";
			else
				res = "Credenciales inválidas";
		} else
			res = "Usuario no encontrado";

		session.close();

		return res;

	}

	public static String validarRegister(String usuario, String pass) {
		String res = null;

		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
		Transaction tx = session.beginTransaction();

		Usuarios user = new Usuarios();
		user.setNombre(usuario);
		user.setPassword(pass);

		try {
			session.save(user);
			tx.commit();
			res = "Register OK";
		} catch (ConstraintViolationException e) {
			res = "Usuario ya existe";
		}

		session.close();

		return res;

	}

	public static String getAllMunicipios() {
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();

		String hql = "from Municipios";
		Query q = session.createQuery(hql);
		Iterator<?> iterator = q.iterate();

		String payload = "{\"data\":[";

		while (iterator.hasNext()) {
			Municipios muni = (Municipios) iterator.next();

			payload += "{";
			payload += "\"codMunicipio\":\"" + muni.getCodMunicipio() + "\",";
			payload += "\"nombre\":\"" + muni.getNombre() + "\"";
			payload += "}";

			if (iterator.hasNext())
				payload += ",";
		}

		payload += "]}";

		session.close();

		return payload;
	}
	
	public static ArrayList<Provincia> getAllProvincias() {
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();

		String hql = "from Provincia";
		Query q = session.createQuery(hql);
		Iterator<?> iterator = q.iterate();

		ArrayList<Provincia> provincias = new ArrayList<>();

		while (iterator.hasNext()) {
			provincias.add((Provincia) iterator.next());

		}
		session.close();

		return provincias;
	}
	
	public static ArrayList<Municipios> getMunicipiosByCodProvincia(String codProvincia) {
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();

		String hql = "from Municipios WHERE CodProvincia = '" + codProvincia + "'";
		Query q = session.createQuery(hql);
		Iterator<?> iterator = q.iterate();

		ArrayList<Municipios> municipios = new ArrayList<>();

		while (iterator.hasNext()) {
			municipios.add((Municipios) iterator.next());

		}
		session.close();

		return municipios;
	}
	
	public static ArrayList<Estaciones> getEstacionesByNomMunicipio(String nombreMunicipio) {
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();

		String hql = "from Estaciones WHERE NomMunicipio = '" + nombreMunicipio + "'";
		Query q = session.createQuery(hql);
		Iterator<?> iterator = q.iterate();

		ArrayList<Estaciones> estaciones = new ArrayList<>();

		while (iterator.hasNext()) {
			estaciones.add((Estaciones) iterator.next());

		}
		session.close();

		return estaciones;
	}
	
	public static ArrayList<Datosdiarios> getDatosdiariosByCodEstacion(String codEstacion) {
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();

		String hql = "from Datosdiarios WHERE CodEstacion = '" + codEstacion + "'";
		Query q = session.createQuery(hql);
		Iterator<?> iterator = q.iterate();

		ArrayList<Datosdiarios> datosdiarios = new ArrayList<>();

		while (iterator.hasNext()) {
			datosdiarios.add((Datosdiarios) iterator.next());

		}
		session.close();

		return datosdiarios;
	}

	/*public static String getCodProvinciaByName(String name) {

		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();

		String hql = "from Estaciones WHERE Nombre = '" + name + "'";
		Query q = session.createQuery(hql).setMaxResults(1);
		Provincia provincia = (Provincia) q.uniqueResult();

		String payload = "{";
		
		if (provincia != null) {
			
			payload += "\"exists\":\"true\",";
			payload += "\"codProvincia\":\"" + provincia.getCodProvincia() + "\"";
		} else {
			payload += "\"exists\":\"false\"";
		}
		
		payload += "}";

		session.close();

		return payload;
	}*/
	
	public static Estaciones getCodEstacionByName(String name) {

		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();

		String hql = "from Estaciones WHERE NombreEstacion = '" + name + "' ORDER BY CodEstacion";
		Query q = session.createQuery(hql).setMaxResults(1);
		Estaciones estacion = (Estaciones) q.uniqueResult();


		session.close();

		return estacion;
	}

	public static void main(String[] args) {
		// Test
		cargarDatos();
		
		//String n = ""
		
	}

}
