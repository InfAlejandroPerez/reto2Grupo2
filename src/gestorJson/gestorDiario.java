package gestorJson;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Map.Entry;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import modelo.Datoshorarios;

public class gestorDiario {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("javax.net.ssl.trustStore", "NUL");
		System.setProperty("javax.net.ssl.trustStoreType", "Windows-ROOT");

		JsonElement json = gestorHorarios.readJSON(
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

					
					while (atributosRutas.hasNext()) {
						String format = atributosRutas.next().getValue().getAsJsonPrimitive().getAsString();
						String name = atributosRutas.next().getValue().getAsJsonPrimitive().getAsString();
						String url = atributosRutas.next().getValue().getAsJsonPrimitive().getAsString();

						if (!name.equalsIgnoreCase("3_DE_MARZO") && url.contains("datos_diarios")) {

							System.out.println("\r-------------------------");
							System.out.println(name);
							System.out.println("-------------------------\r");

							JsonElement jsonDatosHorarios =gestorHorarios.readJSON(url);
							Iterator<JsonElement> jsonDatosHorariosIterator = jsonDatosHorarios.getAsJsonArray()
									.iterator();

							while (jsonDatosHorariosIterator.hasNext()) {
								JsonObject datosHorarios = jsonDatosHorariosIterator.next().getAsJsonObject();
								Iterator<Entry<String, JsonElement>> atributosDatosHorarios = datosHorarios
										.getAsJsonObject().entrySet().iterator();

								System.out.println();
								Datoshorarios horarios = new Datoshorarios();
								
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
									//-----------------------------
										horarios.setHora(valor);
									
									if (campo.equalsIgnoreCase("NOgm3"))
										System.out.println(campo + " > " + valor);
									//-----------------------------
									String v = valor.replace("\"", "");
									v=v.replace(",", ".");
									
									float numero = Float.valueOf(v);
									BigDecimal bDecimal = BigDecimal.valueOf(numero);
									
									horarios.setNogm3(bDecimal);
									
									
									if (campo.equalsIgnoreCase("NO2gm3"))
										System.out.println(campo + " > " + valor);
									
									v = valor.replace("\"", "");
									v=v.replace(",", ".");
									
									numero = Float.valueOf(v);
									bDecimal = BigDecimal.valueOf(numero);
									
									horarios.setNogm3(bDecimal);
									
									horarios.setNo2gm3(bDecimal);
									if (campo.equalsIgnoreCase("NOXgm3"))
										System.out.println(campo + " > " + valor);
									v = valor.replace("\"", "");
									v=v.replace(",", ".");
									
									numero = Float.valueOf(v);
									bDecimal = BigDecimal.valueOf(numero);
									
									horarios.setNoxgm3(bDecimal);
									if (campo.equalsIgnoreCase("PM10gm3"))
										System.out.println(campo + " > " + valor);
									v = valor.replace("\"", "");
									v=v.replace(",", ".");
									
									numero = Float.valueOf(v);
									bDecimal = BigDecimal.valueOf(numero);
									
									horarios.setPm10gm3(bDecimal);
									if (campo.equalsIgnoreCase("PM25gm3"))
										System.out.println(campo + " > " + valor);
									v = valor.replace("\"", "");
									v=v.replace(",", ".");
									
									numero = Float.valueOf(v);
									bDecimal = BigDecimal.valueOf(numero);
									
									horarios.setPm25gm3(bDecimal);
								}
							}

						}
					}
				}
			}
		}
	}

}
