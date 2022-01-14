/*package gestorJson;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map.Entry;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import modelo.Datos;

public class gestorHorarios {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("javax.net.ssl.trustStore", "NUL");
		System.setProperty("javax.net.ssl.trustStoreType", "Windows-ROOT");

		JsonElement json = readJSON(
				"https://opendata.euskadi.eus/contenidos/ds_informes_estudios/calidad_aire_2021/es_def/adjuntos/index.json");

		Iterator<Entry<String, JsonElement>> atributos = json.getAsJsonObject().entrySet().iterator();

		while (atributos.hasNext()) {
			Entry<String, JsonElement> atributo = atributos.next();

			if (atributo.getKey().equals("lastUpdateDate"))
				System.out.println(atributo.getKey() + " > " + atributo.getValue().getAsJsonPrimitive().getAsString());

			if (atributo.getKey().equals("aggregated")) {
				JsonArray aggregated = atributo.getValue().getAsJsonArray();

				for (int i = 0; i < aggregated.size(); i++) {

					JsonObject rutas = aggregated.get(i).getAsJsonObject();
					Iterator<Entry<String, JsonElement>> atributosRutas = rutas.getAsJsonObject().entrySet().iterator();

					Datos datos = new Datos();
					while (atributosRutas.hasNext()) {
						String format = atributosRutas.next().getValue().getAsJsonPrimitive().getAsString();
						String name = atributosRutas.next().getValue().getAsJsonPrimitive().getAsString();
						String url = atributosRutas.next().getValue().getAsJsonPrimitive().getAsString();

						if (!name.equalsIgnoreCase("3_DE_MARZO") && url.contains("datos_horarios")) {

							System.out.println("\r-------------------------");
							System.out.println(name);
							System.out.println("-------------------------\r");

							JsonElement jsonDatosHorarios = readJSON(url);
							Iterator<JsonElement> jsonDatosHorariosIterator = jsonDatosHorarios.getAsJsonArray()
									.iterator();

							while (jsonDatosHorariosIterator.hasNext()) {
								JsonObject datosHorarios = jsonDatosHorariosIterator.next().getAsJsonObject();
								Iterator<Entry<String, JsonElement>> atributosDatosHorarios = datosHorarios
										.getAsJsonObject().entrySet().iterator();

								System.out.println();

								while (atributosDatosHorarios.hasNext()) {
									Entry atributoDatosHorarios = (Entry) atributosDatosHorarios.next();
									String campo = atributoDatosHorarios.getKey().toString();
									String valor = atributoDatosHorarios.getValue().toString();

									if (campo.equalsIgnoreCase("Date")) {
										
									}
										if (valor.equals("31/12/2021"))
											System.out.println(campo + " > " + valor);
									if (campo.equalsIgnoreCase("HourGMT"))
										System.out.println(campo + " > " + valor);
									if (campo.equalsIgnoreCase("NOgm3"))
										System.out.println(campo + " > " + valor);
									if (campo.equalsIgnoreCase("NO2gm3"))
										System.out.println(campo + " > " + valor);
									if (campo.equalsIgnoreCase("NOXgm3"))
										System.out.println(campo + " > " + valor);
									if (campo.equalsIgnoreCase("PM10gm3"))
										System.out.println(campo + " > " + valor);
									if (campo.equalsIgnoreCase("PM25gm3"))
										System.out.println(campo + " > " + valor);
								}
							}

						}
					}
				}
			}
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
*/