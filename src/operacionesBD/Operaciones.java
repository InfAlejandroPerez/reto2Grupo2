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
import modelo.EspaciosNaturales;
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

	public static String getAllProvincias() {
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

	public static String getMunicipiosByCodProvincia(String codProvincia) {
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

	public static String getEstacionesByNomMunicipio(String nombreMunicipio) {
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

	public static String getDatosdiariosByCodEstacion(String codEstacion) {
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

	public static String getInfoMuniByIdMuni(String idMunicipio) {
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();

		/*String[] municipioFragments = nombreMunicipio.split("_");
		String hql = "from Estaciones WHERE NomMunicipio LIKE '%";
		
		for (String municipioFragment : municipioFragments) {
			hql += municipioFragment + "%";
		}
		
		hql += "'";*/
		
		String hql = "from Municipios WHERE CodMunicipio = '" + idMunicipio + "'" ;
		
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
	
	public static String getEspaciosNaturalesByIdMuni(String idMunicipio) {
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
		
		String hql = "FROM EspaciosNaturales WHERE codMunicipio = '"+ idMunicipio + "'";
		
		Query q = session.createQuery(hql);
		Iterator<?> iterator = q.iterate();

		String payload = "{\"data\":[";

		while (iterator.hasNext()) {
			EspaciosNaturales espacioNatural = (EspaciosNaturales) iterator.next();

			payload += "{";
			payload += "\"nombre\":\"" + espacioNatural.getNombre() + "\",";
			payload += "\"codEspacio\":\"" + espacioNatural.getCodEspacio() + "\"";
			payload += "}";


			if (iterator.hasNext())
				payload += ",";

		}
		
		payload += "]}";

		session.close();

		return payload;
	}
	
	public static void main(String[] args) {
		cargarDatos();
	}

}
