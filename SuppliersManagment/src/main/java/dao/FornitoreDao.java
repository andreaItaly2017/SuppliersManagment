package dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import util.HibernateUtil;
import vo.Fornitore;

public class FornitoreDao {
	
	public  int createFornitore(Fornitore f){
		System.out.println("Creazione Fornitore: " + f);
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
		System.out.println("Supplier Successfly create" + f);
		return f.getFornitoreId();
	}
	
	public Fornitore updateFornitore(Fornitore f)	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Fornitore fornitore = (Fornitore) session.get(Fornitore.class, f.getFornitoreId());
		fornitore.setNome(f.getNome());
		fornitore.setDescrizione(f.getDescrizione());
		fornitore.setDataRiunione(f.getDataRiunione());
		fornitore.setTelefono(f.getTelefono());
		fornitore.setCodIdentificativo(f.getCodIdentificativo());
		try{
			session.getTransaction().commit();
		}catch(HibernateException he){
			session.getTransaction().rollback();
			throw he;
		}finally{
			session.close();
		}
		System.out.println("Successfully updated " + fornitore);
		return fornitore;
	}
	
	public Fornitore deleteFornitore(int id){
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Fornitore f = (Fornitore)session.get(Fornitore.class, id);
		session.delete(f);
		System.out.println("Eliminato il fornitore con id: " + f.getFornitoreId());
		try {
			session.getTransaction().commit();
			return f;
		} catch (HibernateException e2) {
			session.getTransaction().rollback();
			throw e2;
		}finally {
			session.close();
		}
	}
	
	public Fornitore getFornitoreById(int id){
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		Fornitore fornitore = null;
		try {
			transaction = session.beginTransaction();

			fornitore = (Fornitore)session.get(Fornitore.class, id);
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
		return fornitore;
	}
	
	public Fornitore getFornitoreByName(String nome){
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		Fornitore f = null;
		try {
			transaction = session.beginTransaction();

			Query query = session.createQuery("from Fornitore f where f.nome like '" + nome + "'"); 
			f = (Fornitore)query.list().get(0);
			System.out.println(f);
			
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return f;
	}
	
	
	public List<Fornitore> readFornitori(){ 
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		List<Fornitore> fornitori = null;
		try{
			Query query = session.createQuery("from Fornitore f order by f.nome"); 
			fornitori = (List<Fornitore>)query.list();
		}catch(HibernateException he){
			session.getTransaction().rollback();
			throw he;
		}
		finally{
			session.close();
		}
		return fornitori;
	}
	
	public List<Fornitore> readFornitoriByName(String name){ 
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		List<Fornitore> fornitori = null;
		try{
			Query query = session.createQuery("from Fornitore f where f.nome like '" + name + "'"); 
			fornitori = (List<Fornitore>)query.list();
		}catch(HibernateException he){
			session.getTransaction().rollback();
			throw he;
		}
		finally{
			session.close();
		}
		return fornitori;
	}
	
	public List<Object[]> readFornitoriJoinRisorse(){ 
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		List<Object[]> fornitori = null;
		try{
			Query query = session.createQuery("from Fornitore f join f.risorse ris order by ris.cognome "); 
			fornitori = (List<Object[]>)query.list();
		}catch(HibernateException he){
			session.getTransaction().rollback();
			throw he;
		}
		finally{
			session.close();
		}
		return fornitori;
	}
	
	public List<Object[]> readFornitoriJoinRisorseByName(String name){ 
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		List<Object[]> fornitori = null;
		try{
			Query query = session.createQuery("from Fornitore f join f.risorse r where r.nome like '" + name + "'"); 
			fornitori = (List<Object[]>)query.list();
		}catch(HibernateException he){
			session.getTransaction().rollback();
			throw he;
		}
		finally{
			session.close();
		}
		return fornitori;
	}
	
	public List<Object[]> readFornitoriJoinRisorseByCognome(String cognome){ 
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		List<Object[]> fornitori = null;
		try{
			Query query = session.createQuery("from Fornitore f join f.risorse r where r.cognome like '" + cognome + "'"); 
			fornitori = (List<Object[]>)query.list();
		}catch(HibernateException he){
			session.getTransaction().rollback();
			throw he;
		}
		finally{
			session.close();
		}
		return fornitori;
	}
	
	/**
	 * Estrae una lista di risorse con il proprio fornitore (si aspetta che n risorse abbiano uno stesso fornitore)
	 * @param idRisorsa
	 * @return
	 */
	public List<Object[]> readFornitoriJoinRisorseByIdRisorsa(int idRisorsa){ 
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		List<Object[]> listOfRowValues = null;
		try{
			listOfRowValues = (List<Object[]>) session.createQuery("from Fornitore f join f.risorse risorse where risorse.id = " + idRisorsa).list() ;
			System.out.println(listOfRowValues);
			
		}catch(HibernateException he){
			session.getTransaction().rollback();
			throw he;
		}
		finally{
			session.close();
		}
		return listOfRowValues;
	}
}