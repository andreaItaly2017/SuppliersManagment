package service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import dao.FornitoreDao;
import managedBean.FornitoriRisorse;
import util.HibernateUtil;
import vo.Fornitore;
import vo.Risorsa;
import vo.comparators.RisorseComparatorByCognome;
import vo.comparators.RisorseComparatorById;

public class FornitoreService {
	
	FornitoreDao fornitoreDao = new FornitoreDao();
	
	public int createFornitore(Fornitore f){
		return fornitoreDao.createFornitore(f);
	}
	
	public List<FornitoriRisorse> readFornitoriJoinRisorse() {
		List<FornitoriRisorse> risorseFornitori = new ArrayList<FornitoriRisorse>(0);
		List<Risorsa> risorse = new ArrayList<Risorsa>(0);
		List<Object[]> suppliers = fornitoreDao.readFornitoriJoinRisorse();

		for (Object[] forn_risorsa : suppliers) {
			Fornitore forn = (Fornitore) forn_risorsa[0];
			Risorsa ris = (Risorsa) forn_risorsa[1];
			risorse.add(ris);
			System.out.print("Fornitore: " +  "id: " + forn.getFornitoreId() + " nome: " + forn.getNome() + " descrizione: " + forn.getDescrizione() + "\nRisorsa " + " id: " + ris.getRisorsaId() + " cognome: " + ris.getCognome() + " nome: " + ris.getNome() + " info: " + ris.getInfo() + " val skill sync: " + ris.getValSkill());
			FornitoriRisorse fornRis = new FornitoriRisorse(forn.getFornitoreId(), forn.getDescrizione(), forn.getNome(),
															ris.getRisorsaId(), ris.getCognome(), ris.getInfo(), ris.getNome(), ris.getValSkill());
			risorseFornitori.add(fornRis);
		}
		
		return risorseFornitori;
	}
	
	public List<FornitoriRisorse> readRisorseJoinRisorseByName(String name) {
		List<FornitoriRisorse> risorseFornitori = new ArrayList<FornitoriRisorse>(0);
		List<Risorsa> risorse = new ArrayList<Risorsa>(0);
		List<Object[]> suppliers = fornitoreDao.readFornitoriJoinRisorseByName(name);

		for (Object[] forn_risorsa : suppliers) {
			Fornitore forn = (Fornitore) forn_risorsa[0];
			Risorsa ris = (Risorsa) forn_risorsa[1];
			risorse.add(ris);
			System.out.print("Fornitore: " +  "id: " + forn.getFornitoreId() + " nome: " + forn.getNome() + " descrizione: " + forn.getDescrizione() + "\nRisorsa " + " id: " + ris.getRisorsaId() + " cognome: " + ris.getCognome() + " nome: " + ris.getNome() + " info: " + ris.getInfo() + " val skill sync: " + ris.getValSkill());
			FornitoriRisorse fornRis = new FornitoriRisorse(forn.getFornitoreId(), forn.getDescrizione(), forn.getNome(),
															ris.getRisorsaId(), ris.getCognome(), ris.getInfo(), ris.getNome(), ris.getValSkill());
			risorseFornitori.add(fornRis);
		}
		RisorseComparatorById rc = new RisorseComparatorById();
		Collections.sort(risorseFornitori, rc);
		return risorseFornitori;
	}
	
	public List<FornitoriRisorse> readFornitoriJoinRisorseByCognome(String cognome) {
		List<FornitoriRisorse> risorseFornitori = new ArrayList<FornitoriRisorse>(0);
		List<Risorsa> risorse = new ArrayList<Risorsa>(0);
		List<Object[]> suppliers = fornitoreDao.readFornitoriJoinRisorseByCognome(cognome);

		for (Object[] forn_risorsa : suppliers) {
			Fornitore forn = (Fornitore) forn_risorsa[0];
			Risorsa ris = (Risorsa) forn_risorsa[1];
			risorse.add(ris);
			System.out.print("Fornitore: " +  "id: " + forn.getFornitoreId() + " nome: " + forn.getNome() + " descrizione: " + forn.getDescrizione() + "\nRisorsa " + " id: " + ris.getRisorsaId() + " cognome: " + ris.getCognome() + " nome: " + ris.getNome() + " info: " + ris.getInfo() + " val skill sync: " + ris.getValSkill());
			FornitoriRisorse fornRis = new FornitoriRisorse(forn.getFornitoreId(), forn.getDescrizione(), forn.getNome(),
															ris.getRisorsaId(), ris.getCognome(), ris.getInfo(), ris.getNome(), ris.getValSkill());
			risorseFornitori.add(fornRis);
		}
		RisorseComparatorByCognome rc = new RisorseComparatorByCognome();
		Collections.sort(risorseFornitori, rc);
		return risorseFornitori;
	}
	
	
	
	/**
	 * Estrae una mappa in cui, ogni elemento rappresenta: Un fornitore con la propria lista di risorse
	 * @param idRisorsa
	 * @return
	 */
	public Map<Fornitore, List<Risorsa>> readFornitoriJoinRisorseByIdRisorsa(int idRisorsa){ 
		Map<Fornitore, List<Risorsa>> fornitoreRisorsa = new HashMap<Fornitore, List<Risorsa>>();
		List<Object[]> listOfRowValues = fornitoreDao.readFornitoriJoinRisorseByIdRisorsa(idRisorsa);
		
		for (Object[] fornitoreRisorsasingleRowValues : listOfRowValues) {
			Fornitore fornitore = (Fornitore) fornitoreRisorsasingleRowValues[0];
			Risorsa risorsa = (Risorsa) fornitoreRisorsasingleRowValues[1];
			if (!fornitoreRisorsa.containsKey(fornitore)) {
				fornitoreRisorsa.put(fornitore, new ArrayList<Risorsa>());
			}
			fornitoreRisorsa.get(fornitore).add(risorsa);
		}
		return fornitoreRisorsa;
	}
	
	public Fornitore updateFornitore(Fornitore f)	{
		return fornitoreDao.updateFornitore(f);
	}
	
	public  Fornitore deleteFornitore(int id){
		return fornitoreDao.deleteFornitore(id);
	}
	
	public List<Fornitore> readFornitori() {
		List<Fornitore> fornitori = fornitoreDao.readFornitori();
		return fornitori;
	}
	
	public List<Fornitore> readFornitoriByName(String name) {
		List<Fornitore> fornitori = fornitoreDao.readFornitoriByName(name);
		return fornitori;
	}
	
	public Fornitore getFornitoreById(int id){
		return fornitoreDao.getFornitoreById(id);
	}
	
	public  Fornitore buildFornitore(String nome, String descrizione, String telefono, String codIdentificativo, Calendar dataRiunione){
		Fornitore f = new Fornitore(nome, descrizione, telefono, codIdentificativo, dataRiunione, null);
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(f);
		System.out.println("Supplier Successfly create" + f);
		return f;
	}
	
	public Fornitore getFornitoreByName(String nome){
		return fornitoreDao.getFornitoreByName(nome);
	}
}
