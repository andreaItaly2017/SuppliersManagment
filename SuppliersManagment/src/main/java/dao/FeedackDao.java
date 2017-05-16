package dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import util.HibernateUtil;
import vo.Feedback;

public class FeedackDao {

	
	public  int createFeedback(Feedback f) {
		System.out.println("Creazione feedback: " + f);
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(f);
		try{
			session.getTransaction().commit();
		}catch(HibernateException he){
			session.getTransaction().rollback();
			throw he;
		}finally{
			session.close();
		}
		System.out.println("Feedback Successfly create" + f);
		return f.getId();
	}

	public void deleteFeedback(long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Feedback f = (Feedback)session.get(Feedback.class, id);
		session.delete(f);
		try {
			session.getTransaction().commit();
		} catch (HibernateException e2) {
			session.getTransaction().rollback();
			throw e2;
		}finally {
			session.close();
		}
	}

	public Feedback updateFeedback(Feedback f) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Feedback feedback = (Feedback) session.get(Feedback.class, f.getId());
		feedback.setEsito(f.getEsito());
		try{
			session.getTransaction().commit();
		}catch(HibernateException he){
			session.getTransaction().rollback();
			throw he;
		}finally{
			session.close();
		}
		System.out.println("Successfully updated " + feedback);
		return feedback;
	}

	public List<Feedback> readFeedback() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		List<Feedback> feedbackList = null;
		try{
			feedbackList = session.createQuery(" from Feedback ").list();
		}catch(HibernateException he){
			session.getTransaction().rollback();
			throw he;
		}finally{
			session.close();
		}
		System.out.println("Found " + feedbackList.size() + " feedback lista");
		return feedbackList;
	}

}
