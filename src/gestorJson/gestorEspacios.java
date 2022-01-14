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

import modelo.EspaciosNaturales;
import operacionesBD.HibernateUtil;

public class gestorEspacios {

	static ArrayList<EspaciosNaturales> codEspArray = new ArrayList<EspaciosNaturales>();
	static int contEsp = 1;

	public static void main(String[] args) {

		JsonParser parser = new JsonParser();
		File file = new File("espaciosnaturales.json");

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
			EspaciosNaturales espaciosNat = new EspaciosNaturales();
			while (atributos.hasNext()) {
				Map.Entry atributo = (Entry) atributos.next();

				if (atributo.getKey().equals("documentName") || atributo.getKey().equals("municipalitycode")
						|| atributo.getKey().equals("turismDescription")) {
					System.out.println(atributo.getKey() + " > "
							+ ((JsonElement) atributo.getValue()).getAsJsonPrimitive().getAsString());
					if (atributo.getKey().equals("documentName")) {

						espaciosNat.setNombre(atributo.getValue().toString());

					} else if (atributo.getKey().equals("municipalitycode")) {

						if (atributo.getValue().toString().length() < 3) {

							espaciosNat.setCodMunicipio(atributo.getValue().toString());

						} else {

							String codMun = atributo.getValue().toString().substring(0, 4);
							espaciosNat.setCodMunicipio(codMun);

						}
					} else if (atributo.getKey().equals("turismDescription")) {

						if (atributo.getValue().toString().length() < 490) {

							espaciosNat.setDescripcion(atributo.getValue().toString());

						} else {

							String desc = atributo.getValue().toString().substring(0, 450);
							espaciosNat.setDescripcion(desc);

						}

					}
				}

			}

			espaciosNat.setCodEspacio(String.valueOf(contEsp));
			contEsp++;
			codEspArray.add(espaciosNat);

			Transaction tx;

			SessionFactory sesion = HibernateUtil.getSessionFactory();

			Session s = sesion.openSession();

			tx = s.beginTransaction();

			s.save(espaciosNat);
			tx.commit();
			s.close();

		}

	}

}
