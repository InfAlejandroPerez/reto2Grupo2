package gestorJson;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import modelo.Municipios;
import operacionesBD.HibernateUtil;

public class datosJson {

	static ArrayList<Municipios> codMunArray = new ArrayList<Municipios>();
	static boolean CodRepe = false;

	public static void main(String[] args) {

		JsonParser parser = new JsonParser();
		File file = new File("pueblos.json");

		String datos = null;

		try {
			datos = new String(Files.readAllBytes(file.toPath()));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		if (datos.charAt(0) != '[' && datos.charAt(0) != '{') {
			if (datos.contains("jsonCallback(")) {
				datos = datos.replace("jsonCallback(", "");
				datos = datos.substring(0, datos.length() - 2);
			}
		}

		JsonElement json = parser.parse(datos);

		JsonArray array = json.getAsJsonArray();
		Iterator iter = array.iterator();

		while (iter.hasNext()) {

			JsonElement entrada = (JsonElement) iter.next();
			JsonObject objeto = entrada.getAsJsonObject();

			Iterator atributos = objeto.entrySet().iterator();
			System.out.println("------------------------");
			Municipios municipios = new Municipios();
			while (atributos.hasNext()) {
				Map.Entry atributo = (Entry) atributos.next();

				if (atributo.getKey().equals("documentName") || atributo.getKey().equals("municipalitycode")
						|| atributo.getKey().equals("turismDescription") || atributo.getKey().equals("territorycode")) {
					System.out.println(atributo.getKey() + " > "
							+ ((JsonElement) atributo.getValue()).getAsJsonPrimitive().getAsString());
					if (atributo.getKey().equals("documentName")) {
						String v = atributo.getValue().toString().replace("\"", "");

						municipios.setNombre(v);
						
						//municipios.setNombre(atributo.getValue().toString());

					} else if (atributo.getKey().equals("municipalitycode")) {

						if (atributo.getValue().toString().length() < 3) {
							
							String v = atributo.getValue().toString().replace("\"", "");

							municipios.setCodMunicipio(v);
							
							//municipios.setCodMunicipio(atributo.getValue().toString());

						} else {
							
							String codMun = atributo.getValue().toString().substring(0, 4);
							String codMuni=codMun.replace("\"", "");
							municipios.setCodMunicipio(codMuni);

						}
					} else if (atributo.getKey().equals("turismDescription")) {

						if (atributo.getValue().toString().length() < 490) {
							String v = atributo.getValue().toString().replace("\"", "");

							municipios.setDescripcion(v);
							
							//municipios.setDescripcion(atributo.getValue().toString());

						} else {

							String desc = atributo.getValue().toString().substring(0, 450);
							String descrip=desc.replace("\"", "");
							municipios.setDescripcion(descrip);
							
							//municipios.setDescripcion(desc);

						}

					} else if (atributo.getKey().equals("territorycode")) {

						if (atributo.getValue().toString().length() < 3) {
							String v = atributo.getValue().toString().replace("\"", "");

							municipios.setCodProvincia(v);
							
							//municipios.setCodProvincia(atributo.getValue().toString());

						} else {

							String codProv = atributo.getValue().toString().substring(0, 3);
							String codProvi=codProv.replace("\"", "");
							municipios.setDescripcion(codProvi);
							
							//municipios.setCodProvincia(codProv);

						}

					}
				}

			}

			for (int i = 0; i < codMunArray.size(); i++) {
				if (codMunArray.get(i).getCodMunicipio().toString().equals(municipios.getCodMunicipio().toString())) {
					CodRepe = true;
					break;
				}
			}

			if (CodRepe == false) {

				codMunArray.add(municipios);

				Transaction tx;

				SessionFactory sesion = HibernateUtil.getSessionFactory();

				Session s = sesion.openSession();

				tx = s.beginTransaction();

				s.save(municipios);
				tx.commit();
				s.close();

			} else {
				CodRepe = false;
			}

		}
	}

}
