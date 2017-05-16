package service;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import util.HibernateUtil;
import vo.Risorse;

public class ServiceTestRisorsa {
	
	
	
	protected List<Risorse> readRisorse() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		List<Risorse> risorse = null;
		try{
			risorse = session.createQuery(" from Risorse ").list();
		}catch(HibernateException he){
			session.getTransaction().rollback();
			throw he;
		}finally{
			session.close();
		}
		System.out.println("Found " + risorse.size() + " risorse");
		return risorse;
	}
	

}
