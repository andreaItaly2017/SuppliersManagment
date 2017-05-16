package service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import util.HibernateUtil;
import managedBean.FornitoriBean;
import managedBean.FornitoriRisorse;
import vo.Fornitore;
import vo.Risorsa;

public class FornitoriTest {
	
	@Test
	public void testGetAllFornitoriBean(){
		FornitoriBean bean = new FornitoriBean();
		bean.getAllFornitori();
	}
	
	/**
	 * SELECT * FROM FORNITORE
	 */
	@Test
	public void getTuttiFornitoriTest()	{ 
		System.out.println("=================READ=================");
		FornitoreService fornitoreService = new FornitoreService();
		List<FornitoriRisorse> suppliers =  fornitoreService.readFornitoriJoinRisorse();
		for (FornitoriRisorse f : suppliers) {
			System.out.println(f);
		}
	}
	
	/**
	 * SELECT fornitore0_.fornitoreId AS fornitoreId1_0_0_,
       risorse1_.risorsaId AS risorsaId1_1_1_,
       fornitore0_.COD_IDENTIFICATIVO AS COD_IDENTIFICATIVO2_0_0_,
       fornitore0_.dataRiunione AS dataRiunione3_0_0_,
       fornitore0_.descrizione AS descrizione4_0_0_,
       fornitore0_.nome AS nome5_0_0_,
       fornitore0_.telefono AS telefono6_0_0_,
       risorse1_.cognome AS cognome2_1_1_,
       risorse1_.fornitore_Id AS fornitore_Id6_1_1_,
       risorse1_.info AS info3_1_1_,
       risorse1_.nome AS nome4_1_1_,
       risorse1_.VALUTAZIONE_SKILL AS VALUTAZIONE_SKILL5_1_1_
		FROM Fornitore fornitore0_
		INNER JOIN Risorsa risorse1_ ON fornitore0_.fornitoreId=risorse1_.fornitore_Id
		ORDER BY risorse1_.cognome
	 */
	@Test
	public void getAllRisorseJoinRisorseTest(){
		System.out.println("=================READ=================");
		FornitoreService fornitoreService = new FornitoreService();
		List<FornitoriRisorse> risorseFornitori =  fornitoreService.readFornitoriJoinRisorse();
		System.out.println(risorseFornitori);
	}
	
	/**
	 * Fa una select con join tra le tabelle fornitori e risorse con filtro su id della risorsa
	 * SELECT fornitore0_.fornitoreId AS fornitoreId1_0_0_,
       risorse1_.risorsaId AS risorsaId1_1_1_,
       fornitore0_.COD_IDENTIFICATIVO AS COD_IDENTIFICATIVO2_0_0_,
       fornitore0_.dataRiunione AS dataRiunione3_0_0_,
       fornitore0_.descrizione AS descrizione4_0_0_,
       fornitore0_.nome AS nome5_0_0_,
       fornitore0_.telefono AS telefono6_0_0_,
       risorse1_.cognome AS cognome2_1_1_,
       risorse1_.fornitore_Id AS fornitore_Id6_1_1_,
       risorse1_.info AS info3_1_1_,
       risorse1_.nome AS nome4_1_1_,
       risorse1_.VALUTAZIONE_SKILL AS VALUTAZIONE_SKILL5_1_1_
		FROM Fornitore fornitore0_
		INNER JOIN Risorsa risorse1_ ON fornitore0_.fornitoreId=risorse1_.fornitore_Id
		WHERE risorse1_.risorsaId=2
	 */
	@Test
	public void getRisorsaAssociataConFornitoreByIdRisorsa(){
		System.out.println("=================getRisorsaAssociataConFornitoreByIdRisorsa=================");
		RisorseService risorseService = new RisorseService();
		long idForn = risorseService.getIdFornitoreByIdRisorsa(2);
		System.out.println(idForn);
	}
	
	/**
	 *  insert into Fornitore (COD_IDENTIFICATIVO, dataRiunione, descrizione, nome, telefono, fornitoreId) values (?, ?, ?, ?, ?, ?)
		insert into Risorsa (cognome, fornitore_Id, info, nome, VALUTAZIONE_SKILL, risorsaId) values (?, ?, ?, ?, ?, ?)
		insert into Risorsa (cognome, fornitore_Id, info, nome, VALUTAZIONE_SKILL, risorsaId) values (?, ?, ?, ?, ?, ?)
	 */
	@Test
	public void testInsertOneToManyRisorse() {

		Session session = util.HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();

			List<Risorsa> risorse = new ArrayList<Risorsa>();
			Risorsa r1 = new Risorsa("Aurelio", "De Filippo", "Bravo con java", 7);
			Risorsa r2 = new Risorsa("Emanuele", "Verdi", "sviluppatore mobile", 8);
			Fornitore fornitore = new Fornitore("NTT Data", "Lavora in applicazioni j2e", "348-5420369", "kdsjf65498",
					new GregorianCalendar(2009, 05, 26), risorse);
			r1.setFornitore(fornitore);
			r2.setFornitore(fornitore);

			session.save(fornitore);
			session.save(r1);
			session.save(r2);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	/**
	 * SELECT risorsa0_.risorsaId AS risorsa_id,
       risorsa0_.cognome AS risorsa_Cognome,
       risorsa0_.fornitore_Id AS fornitore_Id,
       risorsa0_.info AS info,
       risorsa0_.nome AS risorsa_nome,
       risorsa0_.VALUTAZIONE_SKILL AS VALUTAZIONE_SKILL,
       fornitore1_.fornitoreId AS fornitoreId,
       fornitore1_.COD_IDENTIFICATIVO AS Forn_COD_IDENTIFICATIVO,
       fornitore1_.dataRiunione AS Forn_dataRiun,
       fornitore1_.descrizione AS Forn_descrizione,
       fornitore1_.nome AS Forn_nome,
       fornitore1_.telefono AS Forn_telefono
		FROM Risorsa risorsa0_ LEFT OUTER JOIN Fornitore fornitore1_ ON risorsa0_.fornitore_Id=fornitore1_.fornitoreId
		WHERE risorsa0_.risorsaId=?
	 */
	@Test
	public void testGetRisorsaById(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();

			Risorsa r = (Risorsa)session.get(Risorsa.class, 3);
			System.out.println(r);
			
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	/**
	 * SELECT fornitore0_.fornitoreId AS fornitoreId1_0_0_,
       fornitore0_.COD_IDENTIFICATIVO AS COD_IDENTIFICATIVO2_0_0_,
       fornitore0_.dataRiunione AS dataRiunione3_0_0_,
       fornitore0_.descrizione AS descrizione4_0_0_,
       fornitore0_.nome AS nome5_0_0_,
       fornitore0_.telefono AS telefono6_0_0_,
       risorse1_.fornitore_Id AS fornitore_Id6_0_1_,
       risorse1_.risorsaId AS risorsaId1_1_1_,
       risorse1_.risorsaId AS risorsaId1_1_2_,
       risorse1_.cognome AS cognome2_1_2_,
       risorse1_.fornitore_Id AS fornitore_Id6_1_2_,
       risorse1_.info AS info3_1_2_,
       risorse1_.nome AS nome4_1_2_,
       risorse1_.VALUTAZIONE_SKILL AS VALUTAZIONE_SKILL5_1_2_
		FROM Fornitore fornitore0_
		LEFT OUTER JOIN Risorsa risorse1_ ON fornitore0_.fornitoreId=risorse1_.fornitore_Id
		WHERE fornitore0_.fornitoreId=?
	 */
	@Test
	public  void getFornitoreById()	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();

			Fornitore fornitore = (Fornitore)session.get(Fornitore.class, 4);
			System.out.println(fornitore);
			
			System.out.println(" SIZE: " + fornitore.getRisorse().size());
			transaction.commit();
			System.out.println(fornitore);
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	/**
	 * UPDATE Fornitore
		SET COD_IDENTIFICATIVO=?,
		    dataRiunione=?,
		    descrizione=?,
		    nome=?,
		    telefono=?
		WHERE fornitoreId=?
	 */
	@Test
	public void testUpdateFornitoreById(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Fornitore f = (Fornitore)session.get(Fornitore.class, 4);
			
			f.setDescrizione("adesso il suo settore è il mobile");
			
			session.saveOrUpdate(f);
			System.out.println(f);
			
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	/**
	 * 	delete from Fornitore where fornitoreId=?
	 *	delete from Risorsa where risorsaId=?
	 *	delete from Risorsa where risorsaId=?
	 */
	@Test
	public void testDeleteFornitore(){
		int id = 4;
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Fornitore f = (Fornitore)session.get(Fornitore.class, id);
		session.delete(f);
		System.out.println("Eliminato il fornitore con id: " + f.getFornitoreId());
		try {
			session.getTransaction().commit();
		} catch (HibernateException e2) {
			session.getTransaction().rollback();
			throw e2;
		}finally {
			session.close();
		}
	}
	
	/**
	 * insert into Fornitore (COD_IDENTIFICATIVO, dataRiunione, descrizione, nome, telefono, fornitoreId) values (?, ?, ?, ?, ?, ?)
	 */
	@Test
	public void testAggiungiModificaFornitore(){
		/* in input mi aspetto 2 possibili scenari: 
		 * 	1)	nome e descrizione. 	=>	Modalità aggiungi */
		/* 	2)	id, nome e descrizione  => 	Modalità Modifica  */
		/*  */
		String nome = "Test Nome";
		String descrizione = "Test Descrizione";
		String telefono = "325654897";
		String codIdentificativo = "ddf5665sa";
		Calendar dataRiunione = new GregorianCalendar(2009, 05, 26);
		FornitoreService fornitoreService = new FornitoreService();
		Fornitore f = fornitoreService.buildFornitore( nome,  descrizione, telefono, codIdentificativo, dataRiunione);
		long idFornitore = fornitoreService.createFornitore(f);
		System.out.println(idFornitore);
	}
}