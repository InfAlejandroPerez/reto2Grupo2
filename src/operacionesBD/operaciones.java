package operacionesBD;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import modelo.Municipios;
import modelo.Provincia;

public class operaciones {

	public static void insertarDatos(Municipios municipio) {
	
			
		Transaction tx;
		
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		
		Session s = sesion.openSession();
		
		tx = s.beginTransaction();
		
		s.save(municipio);
		tx.commit();
		s.close();
		
	}

}
