package operacionesBD;

import java.util.ArrayList;
import java.util.Iterator;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import Handler.CypherHandler;
import Handler.JSONHandler;
import modelo.Datosdiarios;
import modelo.EspaciosNaturales;
import modelo.Estaciones;
import modelo.FavoritosEspacios;
import modelo.Municipios;
import modelo.Provincia;
import modelo.Usuarios;
import modelo.Hashes;

public class Operaciones {

	private static void cargarArrayList(ArrayList<?> items) {

		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();

		for (Object item : items) {
			try {

				Transaction tx = session.beginTransaction();
				session.save(item);
				tx.commit();

			} catch (Exception e) {
				System.out.println();
			}
		}

		session.close();

	}

	private static boolean checkForUpdates(String latestUpdate) {
		boolean updatesAvailable = false;
		String salt = CypherHandler.getSalt(CypherHandler.DEF_SALT_LENGTH);
		String hash = getLatestUpdateHash();

		if (!CypherHandler.verifyHash(latestUpdate, hash, salt)) {
			updateHash(CypherHandler.generateHash(latestUpdate, salt));
			updatesAvailable = true;
		}

		return updatesAvailable;

	}

	private static void updateHash(String hash) {

		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();

		Transaction tx = null;
		try {

			tx = session.beginTransaction();

			String sql = "UPDATE Hashes SET hash = :nHash";
			Query q = session.createQuery(sql);

			q.setParameter("nHash", hash);
			q.executeUpdate();

			tx.commit();

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

	}

	private static void borrarTablasDatos() {

		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();

		session.createSQLQuery("truncate table datosdiarios").executeUpdate();
		session.createSQLQuery("truncate table datoshorarios").executeUpdate();
		session.createSQLQuery("truncate table datosindice").executeUpdate();

		session.close();

	}

	public static void cargarDatos() {

		// Comprobar sí hay actualizaciones
		if (checkForUpdates(JSONHandler.getLastUpdate())) {
			borrarTablasDatos();

			// Diarios
			System.out.println("CARGANDO DATOS DIARIOS...");
			cargarArrayList(JSONHandler.readDatosDiarios());
			System.out.flush();

			// Horarios
			System.out.println("CARGANDO DATOS HORARIOS...");
			cargarArrayList(JSONHandler.readDatosHorarios());
			System.out.flush();

			// Indice
			System.out.println("CARGANDO DATOS ÍNDICE...");
			cargarArrayList(JSONHandler.readDatosIndice());
			System.out.flush();

			System.out.println("-- CARGA DE DATOS FINALIZADA --");
		}

	}

	public static void cargarLugares() {
		// Municipio
		System.out.println("CARGANDO MUNICIPIOS...");
		cargarArrayList(JSONHandler.readMunicipios());

		// Espacios
		System.out.println("CARGANDO ESPACIOS...");
		cargarArrayList(JSONHandler.readEspacios());

		// Estaciones
		System.out.println("CARGANDO ESTACIONES...");
		cargarArrayList(JSONHandler.readEstaciones());

		System.out.println("-- CARGA DE DATOS FINALIZADA --");
	}

	public static String getLatestUpdateHash() {
		String res = "";

		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();

		String hql = "from Hashes";
		Query q = session.createQuery(hql).setMaxResults(1);
		Hashes hash = (Hashes) q.uniqueResult();

		if (hash != null) {
			res = hash.getHash();
		} else {
			res = ":(";
			Transaction tx = session.beginTransaction();

			Hashes nHash = new Hashes();
			nHash.setHash(res);

			session.save(nHash);
			tx.commit();
		}

		session.close();

		return res;
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
				res = "Login OK;" + user.getCodUsuario();
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

	public static String getInfoEspacioByIdEspacio(String idEspacio) {
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();

		/*
		 * String[] municipioFragments = nombreMunicipio.split("_"); String hql =
		 * "from Estaciones WHERE NomMunicipio LIKE '%";
		 * 
		 * for (String municipioFragment : municipioFragments) { hql +=
		 * municipioFragment + "%"; }
		 * 
		 * hql += "'";
		 */

		int idEsp = Integer.parseInt(idEspacio);

		String hql = "from EspaciosNaturales WHERE CodEspacio = " + idEsp;

		Query q = session.createQuery(hql);
		Iterator<?> iterator = q.iterate();

		String payload = "{\"data\":[";

		while (iterator.hasNext()) {
			EspaciosNaturales espacio = (EspaciosNaturales) iterator.next();

			payload += "{";
			payload += "\"nombre\":\"" + espacio.getNombre() + "\",";
			payload += "\"descripcion\":\"" + espacio.getDescripcion() + "\",";
			payload += "\"latitud\":\"" + espacio.getLatitud() + "\",";
			payload += "\"longitud\":\"" + espacio.getLongitud() + "\"";
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

		/*
		 * String[] municipioFragments = nombreMunicipio.split("_"); String hql =
		 * "from Estaciones WHERE NomMunicipio LIKE '%";
		 * 
		 * for (String municipioFragment : municipioFragments) { hql +=
		 * municipioFragment + "%"; }
		 * 
		 * hql += "'";
		 */

		String hql = "from Municipios WHERE CodMunicipio = '" + idMunicipio + "'";

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

		String hql = "FROM EspaciosNaturales WHERE codMunicipio = '" + idMunicipio + "'";

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

	public static String getAllEspaciosNaturales() {
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();

		String hql = "FROM EspaciosNaturales";

		Query q = session.createQuery(hql);
		Iterator<?> iterator = q.iterate();

		String payload = "{\"data\":[";

		while (iterator.hasNext()) {
			EspaciosNaturales espacioNatural = (EspaciosNaturales) iterator.next();

			payload += "{";
			payload += "\"nombre\":\"" + espacioNatural.getNombre() + "\",";
			payload += "\"codEspacio\":\"" + espacioNatural.getCodEspacio() + "\",";
			payload += "\"latitud\":\"" + espacioNatural.getLatitud() + "\",";
			payload += "\"longitud\":\"" + espacioNatural.getLongitud() + "\"";
			payload += "}";

			if (iterator.hasNext())
				payload += ",";

		}

		payload += "]}";

		session.close();

		return payload;
	}

	public static String comprobarEspacioEnFavoritosPorUsuario(String idUsuario, String idEspacio) {
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();

		String hql = "FROM FavoritosEspacios fe WHERE fe.id.codUsuario = :idUsuario AND fe.id.codEspacio = :idEspacio";

		Query q = session.createQuery(hql);
		FavoritosEspacios favEspacio = null;

		q.setParameter("idUsuario", Integer.parseInt(idUsuario)).setParameter("idEspacio", idEspacio)
		 .setMaxResults(1);

		favEspacio = (FavoritosEspacios)q.uniqueResult();

		String payload = "{";

		if (favEspacio != null) {

			payload += "\"isFav\":\"true\"";

		} else {
			payload += "\"isFav\":\"false\"";
		}

		payload += "}";

		session.close();

		return payload;
	}

	public static void main(String[] args) {
		//cargarDatos();
	}

}
