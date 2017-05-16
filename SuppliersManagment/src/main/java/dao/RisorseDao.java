package dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Assert;

import managedBean.FornitoriRisorse;
import service.FornitoreService;
import service.RisorseService;
import util.HibernateUtil;
import vo.Fornitore;
import vo.Risorsa;

public class RisorseDao {

	public Risorsa createRisorsa(int idFornitore,  String nomeRisorsa, String cognomeRisorsa, String infoRisorsa, int valSkillSync) {
		// in input mi aspetto, idfornitore, nomerisorsa, cognomerisorsa e info  risorsa
		
		// mi aspetto in input gli id del fornitore associato alla risorsa e l'  id della risorsa
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		Risorsa r = null;
		try {
			transaction = session.beginTransaction();
			Fornitore f = (Fornitore) session.get(Fornitore.class, idFornitore);
			r = new Risorsa(nomeRisorsa, cognomeRisorsa, infoRisorsa , valSkillSync);

			r.setFornitore(f);
			session.saveOrUpdate(f);
			session.save(r);

			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return r;
	}

	/**
	 * Elimina associazione tabella joiner Fornitori_Risorse e Tabella Risorse
	 * @param idFornitore
	 * @param idRisorsaDaEliminare
	 */
	public void deleteRisorsa(int idFornitore, int idRisorsaDaEliminare) {
		// mi aspetto in input gli id del fornitore associato alla risorsa e l' id della risorsa
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Fornitore f = (Fornitore)session.get(Fornitore.class, idFornitore);
			Risorsa r = (Risorsa) session.get(Risorsa.class, idRisorsaDaEliminare);
			session.delete(r);
			f.getRisorse().remove(r);
			session.saveOrUpdate(f);
			session.flush();			
			
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	/**
	 * @param idRis
	 * @param nome
	 * @param cognome
	 * @param info
	 * @param valutazioneSkill
	 * @param nomeFornitore
	 * @return
	 */
	public FornitoriRisorse updateRisorsa(int idRis, String nome, String cognome, String info, int valutazioneSkill, String nomeFornitore)	{
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		transaction = session.beginTransaction();
		
		FornitoriRisorse fr = new FornitoriRisorse();
		
		RisorseService risService = new RisorseService();
		int idForn = risService.getIdFornitoreByIdRisorsa(idRis);
		/*
		HibernateUtil.getSessionFactory().close();
		
		session = HibernateUtil.getSessionFactory().openSession();
		transaction = session.beginTransaction();
		*/
		Risorsa r = (Risorsa) session.get(Risorsa.class, idRis);
		Fornitore fornNuovo = null;
		// mi aspetto in input gli id del fornitore associato alla risorsa, l' id della risorsa e nome ris, cognome ris, info risorsa
		// 
		try {
			FornitoreService fornitoreService = new FornitoreService();
			Fornitore f = (Fornitore)session.get(Fornitore.class, idForn);
			if(nomeFornitore != null && !nomeFornitore.equalsIgnoreCase(f.getNome()))
				fornNuovo = fornitoreService.getFornitoreByName(nomeFornitore);
			
			if(fornNuovo != null){
				fr.setIdForni(fornNuovo.getFornitoreId());
				// se sto cambiando il fornitore alla risorsa
				/* se il nome del fornitore dal form è diverso da quello dal db, elimino la risorsa dal fornitore corrente e la aggiungo al fornitore 
				 * che ricevo dal form. Altrimenti, aggiorno solo i dati relativi alla risorsa */
					if(f.getRisorse().contains(r)){
						deleteRisorsa(f.getFornitoreId(), r.getRisorsaId());
						createRisorsa(fornNuovo.getFornitoreId(), nome, cognome, info, valutazioneSkill);
					}
			}else{
				// se il fornitore è rimasto lo stesso
				if(f.getRisorse().contains(r))	{
					fr.setIdForni(f.getFornitoreId());
					
					r.setFornitore(f);
					fr.setDescForn(f.getDescrizione());
					session.save(f);
					session.save(r);
					transaction.commit();
				}
			}
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
			Assert.fail();
		} finally {
			session.close();
		}
		fr.setCognomeRisorsa(cognome);
		fr.setInfoRisorsa(info);
		fr.setNomeRisorsa(nome);
		fr.setIdRisorsa(idRis);
		fr.setValSkillSync(valutazioneSkill);
		
		return fr;
	}
	
	public List<Risorsa> readRisorse(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		List<Risorsa> risorse = null;
		try{
			risorse = session.createQuery(" from Risorsa ").list();
		}catch(HibernateException he){
			session.getTransaction().rollback();
			throw he;
		}finally{
			session.close();
		}
		System.out.println("Found " + risorse.size() + " risorse");
		return risorse;
	}
	
	public Risorsa getRisorsaById(int id){
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Risorsa r = (Risorsa)session.get(Risorsa.class, id);
		try {
			session.getTransaction().commit();
			return r;
		} catch (HibernateException e2) {
			session.getTransaction().rollback();
			throw e2;
		}finally {
			session.close();
		}
	}

	public int createRisorsa(Risorsa r) {
		System.out.println("Creazione Risorsa: " + r);
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(r);
		try{
			session.getTransaction().commit();
		}catch(HibernateException he){
			session.getTransaction().rollback();
			throw he;
		}finally{
			session.close();
		}
		System.out.println("Supplier Successfly create" + r);
		return r.getRisorsaId();
	}

	public Risorsa updateRisorsa(Risorsa r) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Risorsa ris = (Risorsa) session.get(Risorsa.class, r.getRisorsaId());
		ris.setNome(r.getNome());
		ris.setCognome(r.getCognome());
		ris.setInfo(r.getInfo());
		ris.setValSkill(r.getValSkill());
		try{
			session.getTransaction().commit();
		}catch(HibernateException he){
			session.getTransaction().rollback();
			throw he;
		}finally{
			session.close();
		}
		System.out.println("Successfully updated " + ris);
		return ris;
	}
}