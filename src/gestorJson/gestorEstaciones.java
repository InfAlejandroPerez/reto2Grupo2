package gestorJson;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import modelo.Estaciones;
import operacionesBD.HibernateUtil;

public class gestorEstaciones {

	static ArrayList<Estaciones> codEstArray = new ArrayList<Estaciones>();
	static int contEst = 1;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("javax.net.ssl.trustStore", "NUL");
		System.setProperty("javax.net.ssl.trustStoreType", "Windows-ROOT");
		JsonElement json = readJSON(
				"https://opendata.euskadi.eus/contenidos/ds_informes_estudios/calidad_aire_2020/es_def/adjuntos/estaciones.json");

		JsonArray estaciones = json.getAsJsonArray();

		for (int i = 0; i < estaciones.size(); i++) {

			JsonObject estacion = estaciones.get(i).getAsJsonObject();
			Iterator<Entry<String, JsonElement>> atributosEstacion = estacion.getAsJsonObject().entrySet().iterator();
			Estaciones est = new Estaciones();
			while (atributosEstacion.hasNext()) {

				Entry atributoEstacion = (Entry) atributosEstacion.next();
				String campo = atributoEstacion.getKey().toString();
				String valor = atributoEstacion.getValue().toString();

				if (campo.equalsIgnoreCase("Name")) { // Municipio
					/*if (valor.toUpperCase().contains("MARZO")) {
						break;
					}*/
					if(valor.equalsIgnoreCase(null)){
						est.setNombreEstacion("Sin Nombre");
					}else {
						est.setNombreEstacion(valor);

					}
					
					
				
				}
				if (campo.equalsIgnoreCase("Longitude")) { // X
					String v = valor.replace("\"", "");
					v=v.replace(",", ".");
					System.out.println(campo + " > " + v);
					
					float numero = Float.valueOf(v);
					BigDecimal bDecimal = BigDecimal.valueOf(numero);
					est.setCoordenadaX((bDecimal));
				}
				if (campo.equalsIgnoreCase("Latitude")) {// Y
					String v = valor.replace("\"", "");
					v=v.replace(",", ".");
					System.out.println(campo + " > " + v);
					
					float numero = Float.valueOf(v);
					BigDecimal bDecimal = BigDecimal.valueOf(numero);
					
					est.setCoordenadaY((bDecimal));
				}
				if (campo.equalsIgnoreCase("Town")) {
					System.out.println(campo + ">" + valor);
					est.setNomMunicipio(valor);

				}
			}
			
			est.setCodEstacion(String.valueOf(contEst));
			contEst++;
			codEstArray.add(est);

			Transaction tx;

			SessionFactory sesion = HibernateUtil.getSessionFactory();

			Session s = sesion.openSession();

			tx = s.beginTransaction();

			s.save(est);
			tx.commit();
			s.close();

		}

	}

	public static JsonElement readJSON(String urlStr) {
		JsonParser parser = new JsonParser();
		InputStream is = null;

		try {
			is = new URL(urlStr).openStream();
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		String datos = null;

		try {
			datos = new String(is.readAllBytes());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		if (datos.charAt(0) != '[' && datos.charAt(0) != '{') {
			if (datos.contains("jsonCallback(")) {
				datos = datos.replace("jsonCallback(", "");
				datos = datos.substring(0, datos.length() - 2);
			}
		}

		return parser.parse(datos);
	}
}
