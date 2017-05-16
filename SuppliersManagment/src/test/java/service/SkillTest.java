package service;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.junit.Assert;
import org.junit.Test;

import util.HibernateUtil;
import vo.Risorsa;
import vo.Skill;

public class SkillTest {

	@Test
	public void createRisorsaTestAnnotations() {
		
		int idRisorsa = 5;
		String nome = "Java";
		int valutazione = 7;
		
		int idRisorsa2 = 5;
		String nome2 = "Java";
		int valutazione2 = 7;
		
		SkillService skillService = new SkillService();
		Skill skill1 = skillService.createSkill(idRisorsa, nome, valutazione);
		Skill skill2 = skillService.createSkill(idRisorsa2, nome2, valutazione2);
		
		Assert.assertTrue(skill1.getSkillId() > 0);
		Assert.assertTrue(skill2.getSkillId() > 0);
		System.out.println();
		
	}

	@Test
	public void getSkillById() {
		int id = 3;
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Skill r = (Skill) session.get(Skill.class, id);
		try {
			session.getTransaction().commit();
			System.out.println(r);
		} catch (HibernateException e2) {
			session.getTransaction().rollback();
			throw e2;
		} finally {
			session.close();
		}
	}
	
	@Test
	public void deleteSkill() {
		// mi aspetto in input gli id del fornitore associato alla risorsa e l'
		// id della risorsa
		// idForn lo prendo dal servizio
		// gli altri campi li prendo dal form
		int idRisorsa = 93;
		RisorseService risService = new RisorseService();
		int idForn = risService.getIdFornitoreByIdRisorsa(idRisorsa);
		risService.deleteRisorsa(idForn, idRisorsa);
	}
	

}
