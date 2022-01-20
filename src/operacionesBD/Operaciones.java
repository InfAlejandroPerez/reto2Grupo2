package operacionesBD;

import java.util.ArrayList;
import java.util.Iterator;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import Handler.JSONHandler;
import modelo.Estaciones;
import modelo.Municipios;
import modelo.Provincia;
import modelo.Usuarios;

public class Operaciones {

	public static void cargarDatos() {

		// Municipio
		cargarArrayList(JSONHandler.readMunicipios());

		// Espacios
		cargarArrayList(JSONHandler.readEspacios());

		// Estaciones
		cargarArrayList(JSONHandler.readEstaciones());

		// Diarios
		cargarArrayList(JSONHandler.readDatosDiarios());

		// Horarios
		cargarArrayList(JSONHandler.readDatosHorarios());

	}

	public static void cargarArrayList(ArrayList<?> items) {

		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
		Transaction tx = session.beginTransaction();

		items.forEach(item -> {
			try {

				session.save(item);
				tx.commit();

			} catch (ConstraintViolationException e) {
				System.out.println("El objeto " + item.getClass() + " en la posición " + items.indexOf(item)
						+ " se ha saltado (Ya existe en la BBDD!)");
			}
		});

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

	public static String getCodProvinciaByName(String name) {

		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();

		String hql = "from Estaciones WHERE Nombre = '" + name + "'";
		Query q = session.createQuery(hql);
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
	}
	
	public static String getCodEstacionByName(String name) {

		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();

		String hql = "from Estaciones WHERE Nombre = '" + name + "'";
		Query q = session.createQuery(hql);
		Estaciones estacion = (Estaciones) q.uniqueResult();

		String payload = "{";
		
		if (estacion != null) {
			
			payload += "\"exists\":\"true\",";
			payload += "\"codEstacion\":\"" + estacion.getCodEstacion() + "\"";
		} else {
			payload += "\"exists\":\"false\"";
		}
		
		payload += "}";

		session.close();

		return payload;
	}

	public static void main(String[] args) {
		// Test
		cargarDatos();
	}

}
