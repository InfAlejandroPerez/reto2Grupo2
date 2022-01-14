package operacionesBD;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

<<<<<<< HEAD
import modelo.Municipios;
=======
>>>>>>> 01255611924fa01f3ac7906a3c66ac7de6bce716
import modelo.Provincia;

public class operaciones {

<<<<<<< HEAD
	public static void insertarDatos(Municipios municipio) {
	
			
=======
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Provincia pVizcaya=new Provincia();
		
		pVizcaya.setCodProvincia("20");
		pVizcaya.setNombre("Guipuzcoa");
		
>>>>>>> 01255611924fa01f3ac7906a3c66ac7de6bce716
		Transaction tx;
		
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		
		Session s = sesion.openSession();
		
		tx = s.beginTransaction();
		
<<<<<<< HEAD
		s.save(municipio);
=======
		s.save(pVizcaya);
		tx.commit();
		
		Provincia pAlava= new Provincia();
		
		pAlava.setCodProvincia("01");
		pAlava.setNombre("Araba");
		
		tx = s.beginTransaction();
		s.save(pAlava);
>>>>>>> 01255611924fa01f3ac7906a3c66ac7de6bce716
		tx.commit();
		s.close();
		
	}

}
