package service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import vo.Fornitore;
import vo.Risorsa;
import vo.Skill;

public class FornitoriRisorseTest {

	public static void main(String[] args) { 
		
		AnnotationConfiguration config = new AnnotationConfiguration();
		config.addAnnotatedClass(Fornitore.class);
		config.addAnnotatedClass(Risorsa.class);
		config.addAnnotatedClass(Skill.class);
		config.configure("hibernate.cfg.xml");

		new SchemaExport(config).create(true, true);
		
		SessionFactory factory = config.buildSessionFactory();
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		/*
		Fornitore forn = new Fornitore();
		forn.setNome("IBM");
		
		Risorsa alex = new Risorsa();
		alex.setNome("Tizio");

		Risorsa bob = new Risorsa();
		bob.setNome("Caio");
		
		alex.setFornitore(forn);
		bob.setFornitore(forn);
		
		
		session.save(forn);
		session.save(alex);
		session.save(bob);
		session.getTransaction().commit();*/
		System.out.println("SCRIPT COMPLETATO");
	}
}