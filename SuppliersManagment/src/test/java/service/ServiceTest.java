package service;


import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;

public class ServiceTest {
	protected static void deleteAllFornitori()	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("DELETE FROM Fornitori where id = 329 "  );
		query.executeUpdate();
		try {
			session.getTransaction().commit();
		} catch (HibernateException e2) {
			session.getTransaction().rollback();
			throw e2;
		}finally {
			session.close();
		}
		System.out.println("Successfully deleted all suppliers");
	}
}