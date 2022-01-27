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
		System.out.println("CARGANDO MUNICIPIOS...");
		cargarArrayList(JSONHandler.readMunicipios());

		// Espacios
		System.out.println("CARGANDO ESPACIOS...");
		cargarArrayList(JSONHandler.readEspacios());

		// Estaciones
		System.out.println("CARGANDO ESTACIONES...");
		cargarArrayList(JSONHandler.readEstaciones());

		// Diarios
		System.out.println("CARGANDO DATOS DIARIOS...");
		cargarArrayList(JSONHandler.readDatosDiarios());

		// Horarios
		System.out.println("CARGANDO DATOS HORARIOS...");
		cargarArrayList(JSONHandler.readDatosHorarios());
		
		System.out.println("-- CARGA DE DATOS FINALIZADA --");

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
				/*
				 * if (e.getCause().toString().contains(
				 * "MySQLIntegrityConstraintViolationException")) {
				 * System.out.println("Registro duplicado con PK duplicada en el indice: " +
				 * items.indexOf(item) + " se ha saltado"); } else {
				 * System.out.println(e.getMessage()); }
				 */

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

		System.out.println(res);
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

	public static String getAllProvinciasJSON() {
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();

		String hql = "from Provincia";
		Query q = session.createQuery(hql);
		Iterator<?> iterator = q.iterate();

		String payload = "{\"data\":[";

		while (iterator.hasNext()) {
			Provincia provincia = (Provincia) iterator.next();

			payload += "{";
			payload += "\"codProvincia\":\"" + provincia.getCodProvincia() + "\",";
			payload += "\"nombre\":\"" + provincia.getNombre() + "\"";
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
			Provincia obj = (Provincia) iterator.next();
			Provincia provincia = new Provincia();

			provincia.setCodProvincia(obj.getCodProvincia());
			provincia.setNombre(obj.getNombre());

			provincias.add(provincia);

		}
		session.close();

		return provincias;
	}

	public static String getMunicipiosByCodProvinciaJSON(String codProvincia) {
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();

		String hql = "from Municipios WHERE CodProvincia = '" + codProvincia + "'";
		Query q = session.createQuery(hql);
		Iterator<?> iterator = q.iterate();

		String payload = "{\"data\":[";

		while (iterator.hasNext()) {
			Municipios municipio = (Municipios) iterator.next();

			payload += "{";
			payload += "\"codMunicipio\":\"" + municipio.getCodMunicipio() + "\",";
			payload += "\"nombre\":\"" + municipio.getNombre() + "\"";
			payload += "}";

			if (iterator.hasNext())
				payload += ",";

		}
		payload += "]}";

		session.close();

		return payload;
	}

	public static ArrayList<Municipios> getMunicipiosByCodProvincia(String codProvincia) {
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();

		String hql = "from Municipios WHERE CodProvincia = '" + codProvincia + "'";
		Query q = session.createQuery(hql);
		Iterator<?> iterator = q.iterate();

		ArrayList<Municipios> municipios = new ArrayList<>();

		while (iterator.hasNext()) {
			Municipios obj = (Municipios) iterator.next();
			Municipios municipio = new Municipios();

			municipio.setCodMunicipio(obj.getCodMunicipio());
			municipio.setCodProvincia(obj.getCodProvincia());
			municipio.setDescripcion(obj.getDescripcion());
			municipio.setNombre(obj.getNombre());
			municipio.setCodMunicipio(obj.getCodMunicipio());

			municipios.add(municipio);

		}
		session.close();

		return municipios;
	}

	public static String getEstacionesByNomMunicipioJSON(String nombreMunicipio) {
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
		
		String hql = "from Estaciones WHERE NomMunicipio = '" + nombreMunicipio + "'";
		
		Query q = session.createQuery(hql);
		Iterator<?> iterator = q.iterate();

		String payload = "{\"data\":[";

		while (iterator.hasNext()) {
			Estaciones estacion = (Estaciones) iterator.next();

			payload += "{";
			payload += "\"codEstacion\":\"" + estacion.getCodEstacion() + "\",";
			payload += "\"nombre\":\"" + estacion.getNombreEstacion() + "\"";
			payload += "}";


			if (iterator.hasNext())
				payload += ",";

		}
		
		payload += "]}";

		session.close();

		return payload;
	}
	
	public static String getInfoMuniByIdMuni(String idMunicipio) {
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
		
		String hql = "from Municipios WHERE Nombre = '" + idMunicipio + "'";
		
		Query q = session.createQuery(hql);
		Iterator<?> iterator = q.iterate();

		String payload = "{\"data\":[";

		while (iterator.hasNext()) {
			Municipios municipio = (Municipios) iterator.next();

			payload += "{";
			payload += "\"nombre\":\"" + municipio.getNombre() + "\",";
			payload += "\"descripcion\":\"" + municipio.getDescripcion() + "\"";
			payload += "}";


			if (iterator.hasNext())
				payload += ",";

		}
		
		payload += "]}";

		session.close();

		return payload;
	}
	
	
	public static ArrayList<Estaciones> getEstacionesByNomMunicipio(String nombreMunicipio) {
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();

		String hql = "from Estaciones WHERE NomMunicipio = '" + nombreMunicipio + "'";
		Query q = session.createQuery(hql);
		Iterator<?> iterator = q.iterate();

		ArrayList<Estaciones> estaciones = new ArrayList<>();

		while (iterator.hasNext()) {
			Estaciones obj = (Estaciones) iterator.next();
			Estaciones estacion = new Estaciones();

			estacion.setCodEstacion(obj.getCodEstacion());
			estacion.setCoordenadaX(obj.getCoordenadaX());
			estacion.setCoordenadaY(obj.getCoordenadaY());
			estacion.setNombreEstacion(obj.getNombreEstacion());
			estacion.setNomMunicipio(obj.getNomMunicipio());

			estaciones.add(estacion);
		}
		session.close();

		return estaciones;
	}

	public static String getDatosdiariosByCodEstacionJSON(String codEstacion) {
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();

		String hql = "from Datosdiarios WHERE CodEstacion = '" + codEstacion + "'";
		Query q = session.createQuery(hql);
		Iterator<?> iterator = q.iterate();

		String payload = "{\"data\":[";

		while (iterator.hasNext()) {
			Datosdiarios datosdiario = (Datosdiarios) iterator.next();

			payload += "{";
			payload += "\"date\":\"" + datosdiario.getDate() + "\",";
			payload += "\"co8hmgm3\":\"" + datosdiario.getCo8hmgm3() + "\",";
			payload += "\"comgm3\":\"" + datosdiario.getComgm3() + "\",";
			payload += "\"no2gm3\":\"" + datosdiario.getNo2gm3() + "\",";
			payload += "\"nogm3\":\"" + datosdiario.getNogm3() + "\",";
			payload += "\"noxgm3\":\"" + datosdiario.getNoxgm3() + "\",";
			payload += "\"pm10gm3\":\"" + datosdiario.getPm10gm3() + "\",";
			payload += "\"pm25gm3\":\"" + datosdiario.getPm25gm3() + "\",";
			payload += "\"s2gm3\":\"" + datosdiario.getS2gm3() + "\"";
			
			payload += "}";


			if (iterator.hasNext())
				payload += ",";

		}
		
		payload += "]}";

		session.close();

		return payload;
	}
	
	public static ArrayList<Datosdiarios> getDatosdiariosByCodEstacion(String codEstacion) {
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();

		String hql = "from Datosdiarios WHERE CodEstacion = '" + codEstacion + "'";
		Query q = session.createQuery(hql);
		Iterator<?> iterator = q.iterate();

		ArrayList<Datosdiarios> datosdiarios = new ArrayList<>();

		while (iterator.hasNext()) {
			Datosdiarios obj = (Datosdiarios) iterator.next();
			Datosdiarios datosdiario = new Datosdiarios();

			datosdiario.setCo8hmgm3(obj.getCo8hmgm3());
			datosdiario.setCodEstacion(obj.getCodEstacion());
			datosdiario.setComgm3(obj.getCo8hmgm3());
			datosdiario.setDate(obj.getDate());
			datosdiario.setNo2gm3(obj.getNo2gm3());
			datosdiario.setNogm3(obj.getNogm3());
			datosdiario.setNoxgm3(obj.getNoxgm3());
			datosdiario.setPm10gm3(obj.getPm10gm3());
			datosdiario.setPm25gm3(obj.getPm25gm3());
			datosdiario.setS2gm3(obj.getS2gm3());

			datosdiarios.add(datosdiario);

		}
		session.close();

		return datosdiarios;
	}

	public static Estaciones getCodEstacionByName(String name) {

		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();

		String hql = "from Estaciones WHERE NombreEstacion = '" + name + "' ORDER BY CodEstacion";
		Query q = session.createQuery(hql).setMaxResults(1);
		Estaciones obj = (Estaciones) q.uniqueResult();

		Estaciones estacion = new Estaciones();

		if (obj != null) {
			estacion.setCodEstacion(obj.getCodEstacion());
			estacion.setCoordenadaX(obj.getCoordenadaX());
			estacion.setCoordenadaY(obj.getCoordenadaY());
			estacion.setNombreEstacion(obj.getNombreEstacion());
			estacion.setNomMunicipio(obj.getNomMunicipio());
		} else
			estacion = null;

		session.close();

		return estacion;
	}

	public static void main(String[] args) {
		cargarDatos();
	}

}
