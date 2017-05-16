package service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import dao.RisorseDao;
import managedBean.FornitoriRisorse;
import util.HibernateUtil;
import vo.Fornitore;
import vo.Risorsa;
import vo.Risorse;

public class RisorseService {
	
	RisorseDao risorseDao = new RisorseDao();
	
	public Risorsa createRisorsa(int idFornitore,  String nomeRisorsa, String cognomeRisorsa, String infoRisorsa, int valSkillSync){
		return risorseDao.createRisorsa( idFornitore,   nomeRisorsa,  cognomeRisorsa,  infoRisorsa, valSkillSync);
	}
	
	public int createRisorsa(Risorsa r){
		return risorseDao.createRisorsa( r);
	}
	
	public void deleteRisorsa(int idFornitore, int idRisorsaDaEliminare) {
		risorseDao.deleteRisorsa(idFornitore, idRisorsaDaEliminare);
	}
	
	/**
	 * @param idRis
	 * @param nomeRisorsa
	 * @param cognomeRisorsa
	 * @param infoRisorsa
	 * @param valutazione
	 * @param nomeFornitore se valorizzato (deve esser un nome esistente nella tabella fornitori) diverso dal fornitore associato 
	 * 						alla risorsa corrente, sto assegnando la risorsa ad un altro fornitore. Se
	 * null, sto modificando al risorsa al fornitore che ha già associato
	 * @return
	 */
	public FornitoriRisorse updateRisorsa(int idRis, String nomeRisorsa, String cognomeRisorsa, String infoRisorsa, int valutazione, String nomeFornitore)	{
		return risorseDao.updateRisorsa( idRis,  nomeRisorsa,  cognomeRisorsa,  infoRisorsa, valutazione, nomeFornitore);
	}
	
	public Risorsa updateRisorsa(Risorsa r)	{
		return risorseDao.updateRisorsa(r);
	}
	
	public List<Risorsa> readRisorse(){
		return risorseDao.readRisorse();
	}
	
	public Risorsa getRisorsaById(int id){
		return risorseDao.getRisorsaById(id);
	}
	
	public  Risorse buildFornitore()	{
		Session session = HibernateUtil.getSessionFactory().openSession();

		Risorse r = new Risorse("Luca", "Gialli", "Specializzato in front end", 7);
		session.beginTransaction();
		
		try {
			session.save(r);
		} catch (HibernateException e2) {
			e2.printStackTrace();
			session.getTransaction().rollback();
			throw e2;
		} finally {
			session.close();
		}
		System.out.println("Supplier Successfly create" + r);
		return r;
	}
	
	/**
	 * dato un id risorsa, restituisce in output il suo id fornitore associato
	 * @param idRisorsa
	 * @return
	 */
	public int getIdFornitoreByIdRisorsa(int idRisorsa){
		System.out.println("=================getRisorsaAssociataConFornitoreByIdRisorsa=================");
		FornitoreService fornitoreService = new FornitoreService();
		
		int idForn = -1;
		Map<Fornitore, List<Risorsa>>  fornitori = fornitoreService.readFornitoriJoinRisorseByIdRisorsa(idRisorsa);
		Iterator<Entry<Fornitore, List<Risorsa>>> it = fornitori.entrySet().iterator();
		while(it.hasNext()){
			Entry<Fornitore, List<Risorsa>> row = (Entry<Fornitore, List<Risorsa>>) it.next();
			Object key = row.getKey();
			Fornitore f = (Fornitore) key;
			idForn = f.getFornitoreId();
			System.out.println("idForn: " + idForn);
		} 
		return idForn;
	}
	
	public  Risorsa buildRisorsa(String nome, String cognome, String info, int val_skill_per_sync){
		Risorsa r = new Risorsa(nome, cognome, info, val_skill_per_sync);
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(r);
		System.out.println("Risorsa Successfly create" + r);
		return r;
	}
}