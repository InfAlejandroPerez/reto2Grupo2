package operacionesBD;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import modelo.Provincia;

public class operaciones {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Provincia pVizcaya=new Provincia();
		
		pVizcaya.setCodProvincia("20");
		pVizcaya.setNombre("Guipuzcoa");
		
		Transaction tx;
		
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		
		Session s = sesion.openSession();
		
		tx = s.beginTransaction();
		
		s.save(pVizcaya);
		tx.commit();
		
		Provincia pAlava= new Provincia();
		
		pAlava.setCodProvincia("01");
		pAlava.setNombre("Araba");
		
		tx = s.beginTransaction();
		s.save(pAlava);
		tx.commit();
		s.close();
		
	}

}
