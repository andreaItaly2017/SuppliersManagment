package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import util.HibernateUtil;
import vo.Risorsa;
import vo.Skill;

public class SkillDao {
	
	public Skill createSkill(int idRisorsa,  String nome, int valutazione) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		Skill s = null;
		try {
			transaction = session.beginTransaction();
			Risorsa r = (Risorsa) session.get(Risorsa.class, idRisorsa);
			s = new Skill(nome, valutazione);

			s.setRisorsa(r);
			session.saveOrUpdate(r);
			session.save(s);

			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return s;
	}

	public int createSkill(Skill skill) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(skill);
		try {
			session.getTransaction().commit();
		} catch (HibernateException he) {
			session.getTransaction().rollback();
			throw he;
		} finally {
			session.close();
		}
		System.out.println("Skill Successfly create" + skill);
		return skill.getSkillId();
	}

	public void deleteSkill(int idSkill) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		Skill r = (Skill) session.get(Skill.class, idSkill);
		session.delete(r);
		try {
			session.getTransaction().commit();
		} catch (HibernateException e2) {
			session.getTransaction().rollback();
			throw e2;
		} finally {
			session.close();
		}
	}

	public Skill createRisorsa(int idRisorsa, String nome, int valutazione) {
		// TODO Auto-generated method stub
		return null;
	}

}
