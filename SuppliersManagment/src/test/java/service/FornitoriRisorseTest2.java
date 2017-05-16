package service;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import util.HibernateUtil;
import vo.Fornitore;
import vo.Risorsa;

public class FornitoriRisorseTest2 {

	public static void main(String[] args) { 
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Risorsa r = null;
		Fornitore f = null;
		try{
			r = (Risorsa)session.get(Risorsa.class, 2);
			f = (Fornitore)session.get(Fornitore.class, (long)1);
		}catch(HibernateException he){
			session.getTransaction().rollback();
			throw he;
		}finally{
			session.close();
		}
		System.out.println();
	}
}