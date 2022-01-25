package Handler;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import modelo.Datosdiarios;
import modelo.Datoshorarios;
import modelo.EspaciosNaturales;
import modelo.Estaciones;
import modelo.Municipios;
import operacionesBD.Operaciones;

public class JSONHandler {
	public static ArrayList<Municipios> readMunicipios() {

		JsonElement json = readJSON("pueblos.json");

		JsonArray array = json.getAsJsonArray();
		Iterator<?> iter = array.iterator();

		ArrayList<Municipios> municipios = new ArrayList<>();

		while (iter.hasNext()) {

			JsonElement entrada = (JsonElement) iter.next();
			JsonObject objeto = entrada.getAsJsonObject();

			Iterator<?> atributos = objeto.entrySet().iterator();

			Municipios municipio = new Municipios();
			municipio.setCodProvincia("");

			while (atributos.hasNext()) {
				Map.Entry<?, ?> atributo = (Entry<?, ?>) atributos.next();
				String campo = atributo.getKey().toString();
				String valor = atributo.getValue().toString();

				if (campo.equals("documentName")) {

					municipio.setNombre(cleanString(valor));

				}

				if (campo.equals("municipalitycode")) {

					if (valor.length() < 3) {

						municipio.setCodMunicipio(cleanString(valor));

					} else {

						municipio.setCodMunicipio(cleanString(valor.substring(0, 4)));

					}
				}

				if (campo.equals("turismDescription")) {

					if (valor.length() < 490) {

						municipio.setDescripcion(cleanString(valor));

					} else {

						municipio.setDescripcion(cleanString(valor.substring(0, 450)));

					}

				}

				if (campo.equals("territorycode")) {

					if (valor.length() < 3) {

						municipio.setCodProvincia(cleanString(valor));

					} else {

						municipio.setCodProvincia(cleanString(valor.substring(0, 3)));

					}

				}

				if (!atributos.hasNext()) {
					if (municipio.getCodProvincia().length() > 0) {
						if (!municipios.contains(municipio)) {
							municipios.add(municipio);
							municipio = new Municipios();
						}
					}
				}
			}
		}

		return municipios;
	}

	public static ArrayList<EspaciosNaturales> readEspacios() {

		JsonElement json = readJSON("espaciosnaturales.json");

		JsonArray array = json.getAsJsonArray();
		Iterator<?> iter = array.iterator();

		ArrayList<EspaciosNaturales> espacios = new ArrayList<>();

		while (iter.hasNext()) {

			JsonElement entrada = (JsonElement) iter.next();
			JsonObject objeto = entrada.getAsJsonObject();

			Iterator<?> atributos = objeto.entrySet().iterator();
			EspaciosNaturales espacio = new EspaciosNaturales();

			espacio.setCodMunicipio("");

			while (atributos.hasNext()) {
				Map.Entry<?, ?> atributo = (Entry<?, ?>) atributos.next();
				String campo = atributo.getKey().toString();
				String valor = cleanString(atributo.getValue().toString());

				if (campo.equals("documentName")) {

					espacio.setNombre(valor);

				}

				if (campo.equals("municipalitycode")) {

					
					if (valor.split(" ").length > 1) {
						espacio.setCodMunicipio(valor.split(" ")[0]);
					} else {
						espacio.setCodMunicipio(valor);
					}
					

				} 
				
				if (campo.equals("turismDescription")) {

					if (valor.length() < 490) {

						espacio.setDescripcion(valor);

					} else {

						espacio.setDescripcion(valor.substring(0, 450));

					}

				}
				
				if (campo.equals("latwgs84")) {

					if (valor.length() > 0) {
						espacio.setLatitud(parseBigDecimal(valor));
					}

				}

				if (campo.equals("lonwgs84")) {

					if (valor.length() > 0) {
						espacio.setLongitud(parseBigDecimal(valor));
					}

				}
				
				if (!atributos.hasNext()) {
					if (espacio.getCodMunicipio().length() > 0 && hasCoords(espacio)) {
						espacios.add(espacio);
						espacio = new EspaciosNaturales();
					}
				}

			}
		}

		return espacios;
	}

	public static ArrayList<Estaciones> readEstaciones() {
		JsonElement json = readJSON(
				"https://opendata.euskadi.eus/contenidos/ds_informes_estudios/calidad_aire_2020/es_def/adjuntos/estaciones.json");

		JsonArray array = json.getAsJsonArray();

		ArrayList<Estaciones> estaciones = new ArrayList<>();

		for (int i = 0; i < array.size(); i++) {

			JsonObject objeto = array.get(i).getAsJsonObject();
			Iterator<Entry<String, JsonElement>> atributos = objeto.getAsJsonObject().entrySet().iterator();

			Estaciones estacion = new Estaciones();

			estacion.setNomMunicipio("");

			while (atributos.hasNext()) {

				Entry<?, ?> atributo = (Entry<?, ?>) atributos.next();
				String campo = atributo.getKey().toString();
				String valor = atributo.getValue().toString();

				if (campo.equalsIgnoreCase("Name")) { // Municipio

					if (valor.equalsIgnoreCase(null)) {

						estacion.setNombreEstacion("Sin Nombre");
					} else {

						estacion.setNombreEstacion(cleanString(valor));
					}

				}

				if (campo.equalsIgnoreCase("Longitude")) { // X

					estacion.setCoordenadaX(parseBigDecimal(cleanString(valor)));
				}

				if (campo.equalsIgnoreCase("Latitude")) {// Y

					estacion.setCoordenadaY(parseBigDecimal(cleanString(valor)));
				}

				if (campo.equalsIgnoreCase("Town")) {

					estacion.setNomMunicipio(cleanString(valor));
				}

				if (!atributos.hasNext()) {
					if (estacion.getNomMunicipio().length() > 0) {
						estaciones.add(estacion);

						estacion = new Estaciones();
					}
				}
			}

		}

		return estaciones;
	}

	public static ArrayList<Datosdiarios> readDatosDiarios() {

		JsonElement json = readJSON(
				"https://opendata.euskadi.eus/contenidos/ds_informes_estudios/calidad_aire_2021/es_def/adjuntos/index.json");

		Iterator<Entry<String, JsonElement>> atributos = json.getAsJsonObject().entrySet().iterator();

		ArrayList<Datosdiarios> diarios = new ArrayList<>();

		while (atributos.hasNext()) {
			Entry<String, JsonElement> atributo = atributos.next();

			// if (atributo.getKey().equals("lastUpdateDate"))

			if (atributo.getKey().equals("aggregated")) {
				JsonArray aggregated = atributo.getValue().getAsJsonArray();

				for (int i = 0; i < aggregated.size(); i++) {

					JsonObject rutas = aggregated.get(i).getAsJsonObject();
					Iterator<Entry<String, JsonElement>> atributosRutas = rutas.getAsJsonObject().entrySet().iterator();

					while (atributosRutas.hasNext()) {

						String format = atributosRutas.next().getValue().getAsJsonPrimitive().getAsString();
						String name = atributosRutas.next().getValue().getAsJsonPrimitive().getAsString();
						String url = atributosRutas.next().getValue().getAsJsonPrimitive().getAsString();

						Estaciones estacion = Operaciones.getCodEstacionByName(name);

						if (estacion != null) {

							String codEstacion = String.valueOf(estacion.getCodEstacion());

							if (url.contains("datos_diarios")) {

								JsonElement jsonDatos = readJSON(url);
								
								if (jsonDatos == null) {
									continue;
								}
								
								Iterator<JsonElement> jsonDatosIterator = jsonDatos.getAsJsonArray().iterator();

								while (jsonDatosIterator.hasNext()) {

									JsonObject datos = jsonDatosIterator.next().getAsJsonObject();
									Iterator<Entry<String, JsonElement>> atributosDatos = datos.getAsJsonObject()
											.entrySet().iterator();

									Datosdiarios diario = new Datosdiarios();
									boolean isDateOk = true;

									diario.setCodEstacion(codEstacion);

									while (atributosDatos.hasNext() && isDateOk) {
										Entry<?, ?> atributoDatos = (Entry<?, ?>) atributosDatos.next();
										String campo = atributoDatos.getKey().toString();
										String valor = cleanString(atributoDatos.getValue().toString());

										if (campo.equalsIgnoreCase("Date")) {

											// Si no es de esta fecha se salta el objeto
											if (!valor.equals("31/12/2021"))
												isDateOk = false;

											diario.setDate(valor);

										}

										if (campo.equalsIgnoreCase("COmgm3")) {

											diario.setComgm3(parseBigDecimal(valor));
										}

										if (campo.equalsIgnoreCase("Co8hmgm3")) {

											diario.setCo8hmgm3(parseBigDecimal(valor));
										}

										if (campo.equalsIgnoreCase("NOgm3")) {

											diario.setNogm3(parseBigDecimal(valor));
										}

										if (campo.equalsIgnoreCase("NO2gm3")) {

											diario.setNo2gm3(parseBigDecimal(valor));
										}

										if (campo.equalsIgnoreCase("NOXgm3")) {

											diario.setNoxgm3(parseBigDecimal(valor));
										}

										if (campo.equalsIgnoreCase("PM10gm3")) {

											diario.setPm10gm3(parseBigDecimal(valor));
										}

										if (campo.equalsIgnoreCase("PM25gm3")) {

											diario.setPm25gm3(parseBigDecimal(valor));
										}

										if (campo.equalsIgnoreCase("S2gm3")) {

											diario.setS2gm3(parseBigDecimal(valor));
										}

										// Si es del 31/12/2021 lo añadimos
										if (isDateOk) {
											if (!atributosDatos.hasNext()) {
												if (diario.getCodEstacion().length() > 0) {
													diarios.add(diario);
												}
											}
										}

									}

								}

							}
						}
					}
				}
			}
		}

		return diarios;
	}

	public static ArrayList<Datoshorarios> readDatosHorarios() {

		JsonElement json = readJSON(
				"https://opendata.euskadi.eus/contenidos/ds_informes_estudios/calidad_aire_2021/es_def/adjuntos/index.json");

		Iterator<Entry<String, JsonElement>> atributos = json.getAsJsonObject().entrySet().iterator();

		ArrayList<Datoshorarios> horarios = new ArrayList<>();

		while (atributos.hasNext()) {
			Entry<String, JsonElement> atributo = atributos.next();

			// if (atributo.getKey().equals("lastUpdateDate"))

			if (atributo.getKey().equals("aggregated")) {
				JsonArray aggregated = atributo.getValue().getAsJsonArray();

				for (int i = 0; i < aggregated.size(); i++) {

					JsonObject rutas = aggregated.get(i).getAsJsonObject();
					Iterator<Entry<String, JsonElement>> atributosRutas = rutas.getAsJsonObject().entrySet().iterator();

					while (atributosRutas.hasNext()) {

						String format = atributosRutas.next().getValue().getAsJsonPrimitive().getAsString();
						String name = atributosRutas.next().getValue().getAsJsonPrimitive().getAsString();
						String url = atributosRutas.next().getValue().getAsJsonPrimitive().getAsString();

						Estaciones estacion = Operaciones.getCodEstacionByName(name);

						if (estacion != null) {

							String codEstacion = String.valueOf(estacion.getCodEstacion());

							if (url.contains("datos_horarios")) {

								JsonElement jsonDatos = readJSON(url);
								
								if (jsonDatos == null) {
									continue;
								}
								
								Iterator<JsonElement> jsonDatosIterator = jsonDatos.getAsJsonArray().iterator();

								while (jsonDatosIterator.hasNext()) {

									JsonObject datos = jsonDatosIterator.next().getAsJsonObject();
									Iterator<Entry<String, JsonElement>> atributosDatos = datos.getAsJsonObject()
											.entrySet().iterator();

									Datoshorarios horario = new Datoshorarios();
									boolean isDateOk = true;

									horario.setCodEstacion(codEstacion);

									while (atributosDatos.hasNext() && isDateOk) {
										Entry<?, ?> atributoDatos = (Entry<?, ?>) atributosDatos.next();
										String campo = atributoDatos.getKey().toString();
										String valor = cleanString(atributoDatos.getValue().toString());

										if (campo.equalsIgnoreCase("Date")) {

											// Si no es de esta fecha se salta el objeto
											if (!valor.equals("31/12/2021"))
												isDateOk = false;

											horario.setFecha(valor);

										}

										if (campo.equalsIgnoreCase("HourGMT")) {

											horario.setHora(valor);
										}

										if (campo.equalsIgnoreCase("COmgm3")) {

											horario.setComgm3(parseBigDecimal(valor));
										}

										if (campo.equalsIgnoreCase("CO8hmgm3")) {

											horario.setCo8hmgm3(parseBigDecimal(valor));
										}

										if (campo.equalsIgnoreCase("NOgm3")) {

											horario.setNogm3(parseBigDecimal(valor));
										}

										if (campo.equalsIgnoreCase("NO2gm3")) {

											horario.setNo2gm3(parseBigDecimal(valor));
										}

										if (campo.equalsIgnoreCase("NOXgm3")) {

											horario.setNoxgm3(parseBigDecimal(valor));
										}

										if (campo.equalsIgnoreCase("PM10gm3")) {

											horario.setPm10gm3(parseBigDecimal(valor));
										}

										if (campo.equalsIgnoreCase("PM25gm3")) {

											horario.setPm25gm3(parseBigDecimal(valor));
										}

										if (campo.equalsIgnoreCase("SO2gm3")) {

											horario.setSo2gm3(parseBigDecimal(valor));
										}

										// Si es del 31/12/2021 lo añadimos
										if (isDateOk) {
											if (!atributosDatos.hasNext()) {
												if (horario.getCodEstacion().length() > 0) {
													horarios.add(horario);
												}
											}
										}

									}

								}

							}
						}
					}
				}
			}
		}

		return horarios;
	}

	private static JsonElement readJSON(String urlStr) {
		System.setProperty("javax.net.ssl.trustStore", "NUL");
		System.setProperty("javax.net.ssl.trustStoreType", "Windows-ROOT");

		JsonParser parser = new JsonParser();
		InputStream is = null;
		
		String datos = "";

		boolean isLocalFile = false;

		try {
			is = new URL(urlStr).openStream();
		} catch (MalformedURLException e) {

			if (e.getMessage().contains("no protocol")) {
				isLocalFile = true;
			} else {
				System.out.println(e.getMessage());
			}

		} catch (IOException e) {
			return null;
		}

		try {

			if (isLocalFile) {
				File file = new File(urlStr);
				datos = new String(Files.readAllBytes(file.toPath()));
			} else {
				datos = new String(is.readAllBytes());
			}

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

	private static String cleanString(String n) {

		return n.replace("\"", "");
	}

	public static BigDecimal parseBigDecimal(String n) {
		n = n.replace("\"", "").replace(",", ".");
		
		return BigDecimal.valueOf(Float.valueOf(n));
	}
	
	private static boolean hasCoords(EspaciosNaturales espacio) {
		return espacio.getLatitud() != null && espacio.getLongitud() != null;
	}
}
