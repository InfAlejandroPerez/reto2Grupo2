package operacionesBD;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import modelo.Usuarios;

public class operaciones {

	/*
	 public static void main(String[] args) {
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
	*/
	
	public static String validarLogin(String usuario, String pass) {
		String res = "";
		
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
		
		String hql = "from Usuarios as user where user.nombre = '" + usuario + "'";
		Query q = session.createQuery(hql);
		Usuarios user = (Usuarios) q.uniqueResult();
		
		if(user != null) {
			if(user.getPassword().equals(pass)) 
				res = "Login OK";
			else 
				res = "Credenciales inválidas";
		}
		else
			res = "Usuario no encontrado";
		
		session.close();
		
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
	
	public static String getAllMunicipios() {
		String res = "";
		
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
		
		String hql = "from Municipios";
		Query q = session.createQuery(hql);
		Usuarios user = (Municipios) q.();
		
		if(user != null) {
			if(user.getPassword().equals(pass)) 
				res = "Login OK";
			else 
				res = "Credenciales inválidas";
		}
		else
			res = "Usuario no encontrado";
		
		session.close();
		
		return res;
	}

}
