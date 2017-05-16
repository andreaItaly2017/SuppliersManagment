package managedBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import service.FornitoreService;
import service.RisorseService;
import vo.Fornitore;
import vo.Risorsa;

@ManagedBean(name = "risorsa")
public class RisorseBean {

	@ManagedProperty(value = "#{param.id}")
	private int id;
	@ManagedProperty(value = "#{param.nome}")
	private String nome;
	@ManagedProperty(value = "#{param.nomeFornitore}")
	private String nomeFornitore;

	@ManagedProperty(value = "#{param.cognome}")
	private String cognome;
	@ManagedProperty(value = "#{param.info}")
	private String info;
	@ManagedProperty(value = "#{param.fornitoreId}")
	private int fornitoreId;
	@ManagedProperty(value = "#{param.valSkillSync}")
	private int valSkillSync;
	private String messageError;
	private List<FornitoriRisorse> risorseFornitori = new ArrayList<FornitoriRisorse>(0);
	private String messageErrorData = null;

	public RisorseBean() { }

	public RisorseBean(int id, String nome, String cognome, String info, int feedbackId, int fornitoreId,
			int valSkillSync) {
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.info = info;
		this.fornitoreId = fornitoreId;
		this.valSkillSync = valSkillSync;
	}

	public RisorseBean(String nome, String cognome, String info, int feedbackId, int fornitoreId, int valSkillSync) {
		this.nome = nome;
		this.cognome = cognome;
		this.info = info;
		this.fornitoreId = fornitoreId;
		this.valSkillSync = valSkillSync;
	}

	public RisorseBean(String nome, String cognome, String info) {
		this.nome = nome;
		this.cognome = cognome;
		this.info = info;
	}

	public String paginaRisorse() {
		getAllRisorse();
		return ("risorse-view");
	}

	public List<String> completaNomeRisorsa(String nomeRisorsaPrefix) {
		System.out.println("completaNomeRisorsa");
		List<String> matches = new ArrayList<>();
		Set<String> nomi = getNamesRisorse();
		Object[] nomiArray = (Object[]) nomi.toArray();
		for (Object risorsa : nomiArray) {
			String s = (String) risorsa;
			if (s.toUpperCase().startsWith(nomeRisorsaPrefix.toUpperCase())) {
				matches.add(s);
			}
		}
		return (matches);
	}

	public List<String> completaCognomeRisorsa(String cognomeRisorsaPrefix) {
		System.out.println("completaCognomeRisorsa");
		List<String> matches = new ArrayList<>();
		Set<String> cognomi = getCognomiRisorse();
		Object[] cognomiArray = (Object[]) cognomi.toArray();
		for (Object risorsa : cognomiArray) {
			String s = (String) risorsa;
			if (s.toUpperCase().startsWith(cognomeRisorsaPrefix.toUpperCase())) {
				matches.add(s);
			}
		}
		return (matches);
	}
	
	/* get tutti i nomi dei fornitori */
	public List<String> getNamesFornitori() {
		System.out.println("getNamesFornitori");
		FornitoreService fornitoreService = new FornitoreService();
		List<Fornitore> fornitori = fornitoreService.readFornitori();
		List<String> tuttiNomiPresenti = new ArrayList<String>(0);
		for (Fornitore forn : fornitori) {
			tuttiNomiPresenti.add(forn.getNome());
		}
		return tuttiNomiPresenti;
	}
	
	public List<String> completaNomeFornitore(String nomeFornitorePrefix) {
		List<String> matches = new ArrayList<>();
		List<String> nomi = getNamesFornitori();
		Object[] nomiArray = (Object[]) nomi.toArray();
		for (Object fornitore : nomiArray) {
			String s = (String) fornitore;
			if (s.toUpperCase().startsWith(nomeFornitorePrefix.toUpperCase())) {
				matches.add(s);
			}
		}
		return (matches);
	}

	private Set<String>  getNamesRisorse() {
		@SuppressWarnings("unchecked")
		List<FornitoriRisorse> risorse = (List<FornitoriRisorse>) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("risorseList");
		Set<String> tuttiNomiPresentiDistinct = new TreeSet<>();
		for (FornitoriRisorse ris : risorse) 
			tuttiNomiPresentiDistinct.add(ris.getNomeRisorsa());
		return tuttiNomiPresentiDistinct;
	}

	private Set<String> getCognomiRisorse() {
		@SuppressWarnings("unchecked")
		List<FornitoriRisorse> risorse = (List<FornitoriRisorse>) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("risorseList");
		Set<String> tuttiCognomiPresentiDistinct = new TreeSet<>();
		for (FornitoriRisorse fornitoriRisorse : risorse) 
			tuttiCognomiPresentiDistinct.add(fornitoriRisorse.getCognomeRisorsa());
		return tuttiCognomiPresentiDistinct;
	}

	public String eseguiRicerca() {
		return ("risorse-view");
	}

	private void getAllRisorse() {
		System.out.println("=================READ=================");
		List<FornitoriRisorse> result = new ArrayList<FornitoriRisorse>();
		FornitoreService fornitoreService = new FornitoreService();

		if (this.nome != null && this.id == 0 && this.cognome == null && this.info == null && this.valSkillSync == 0) {
			result =  fornitoreService.readRisorseJoinRisorseByName(this.nome);
		} else if (this.nome == null && this.id == 0 && this.cognome != null && this.info == null
				&& this.valSkillSync == 0) {
			result =  fornitoreService.readFornitoriJoinRisorseByCognome(this.cognome);
		} else {
			result =  fornitoreService.readFornitoriJoinRisorse();
		}
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("risorseList", result);
	}

	public String maskAggiungiModifica() {
		System.out.println(">>>maskAggiungiModifica<<<");
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

		String id = "";
		String nome = "";
		String cognome = "";
		String info = "";
		String actionFrom = "";
		String nomeFornitore = "";
		int valSkillSync = 0;

		if (request != null) {
			id = request.getParameter("id");
			nome = request.getParameter("nome");
			cognome = request.getParameter("cognome");
			info = request.getParameter("info");
			valSkillSync = request.getParameter("valSkillSync") != null ? Integer.parseInt(request.getParameter("valSkillSync")) : 0;
			nomeFornitore = request.getParameter("nomeFornitore");
			actionFrom = request.getParameter("actionFrom");
		}
		if (actionFrom.equals("modifica")) {
			this.id = Integer.parseInt(id);
			this.nome = nome;
			this.cognome = cognome;
			this.info = info;
			this.valSkillSync = valSkillSync;
			this.nomeFornitore = nomeFornitore;
		}
		setMessageError(null);
		return ("add-edit-risorsa");
	}

	public String aggiungiModifica() {
		System.out.println(">>>AGGIUNGIMODIFICA<<<");
		/*
		 * deve preparare la pagina con i campi valorizzati o no, a seconda se
		 * ho un id diverso da 0 o meno
		 */
		/* id diverso da 0 => Modifica */
		/* id = 0 => Aggiungi */
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		if (request != null) {
			String actionFrom = request.getParameter("actionFrom");
			String id = "";
			String nome = "";
			String cognome = "";
			String info = "";
			String nomeFornitore = "";
			int valSkillSync = 0;
			if (actionFrom.equals("aggiungi")) {
				nome = this.nome;
				cognome = this.cognome;
				info = this.info;
				nomeFornitore = this.nomeFornitore;
				valSkillSync = this.valSkillSync;

				if (nome == null || nome.equals("")) {
					messageError = "Il nome della risorsa non può esser nullo";
					return ("add-edit-fornitore");
				}
				if (cognome == null || cognome.equals("")) {
					messageError = "Il nome della risorsa non può esser nullo";
					return ("add-edit-fornitore");
				}
				/*
				 * in input mi aspetto: nome e descrizione. L' verrà assegnato
				 * in automatico da hibernate
				 */
				RisorseService risorsaService = new RisorseService();
				
				/* get id fornitore by name */
				FornitoreService fornitoreService = new FornitoreService();
				List <Fornitore> fornitori = fornitoreService.readFornitori();
				String nomeTmp = "";
				Fornitore frnTmp = new Fornitore();
				for (Fornitore forn : fornitori) {
					nomeTmp = forn.getNome();
					if(nomeTmp.equalsIgnoreCase(nomeFornitore)){
						frnTmp = forn;
						break;
					}
				}
				
				Risorsa r = risorsaService.createRisorsa(frnTmp.getFornitoreId(), nome, cognome, info, valSkillSync);

				int idRisorsa = risorsaService.createRisorsa(r);
				r = risorsaService.getRisorsaById(idRisorsa);

				this.id = r.getRisorsaId();

				this.nome = r.getNome();
				this.cognome = r.getCognome();
				this.info = r.getInfo();
				this.valSkillSync = r.getValSkill();
				messageError = null;
				@SuppressWarnings("unchecked")
				List<FornitoriRisorse> risorse = (List<FornitoriRisorse>) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("risorseList");
				FornitoriRisorse nuovaRis = new FornitoriRisorse();
				nuovaRis.setIdRisorsa(r.getRisorsaId());
				nuovaRis.setNomeRisorsa(r.getNome());
				nuovaRis.setCognomeRisorsa(r.getCognome());
				nuovaRis.setInfoRisorsa(r.getInfo());
				nuovaRis.setValSkillSync(r.getValSkill());
				nuovaRis.setNomeFornitore(frnTmp.getNome());
				nuovaRis.setDescForn(frnTmp.getDescrizione());
				nuovaRis.setIdForni(frnTmp.getFornitoreId());
				risorse.add(nuovaRis);
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("risorseList");
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("risorseList", risorse);				
				
			} else if (actionFrom.equals("modifica")) {

				id = request.getParameter("id");
				nome = this.nome;
				cognome = this.cognome;
				info = this.info;
				valSkillSync = this.valSkillSync;
				nomeFornitore = this.nomeFornitore;

				RisorseService risorsaService = new RisorseService();
				FornitoriRisorse fr = risorsaService.updateRisorsa(Integer.parseInt(id), nome, cognome, info, valSkillSync,  nomeFornitore);
				
				Risorsa ris = risorsaService.createRisorsa(fr.getIdForni(), fr.getNomeRisorsa(), fr.getCognomeRisorsa(), fr.getInfoRisorsa(), fr.getValSkillSync());
				System.out.println("Creata risorsa: " + ris.getRisorsaId());
				this.id = Integer.parseInt(id);
				this.nome = nome;
				this.cognome = cognome;
				this.info = info;
				this.valSkillSync = valSkillSync;
				messageError = null;
				@SuppressWarnings("unchecked")
				List<FornitoriRisorse> risorse = (List<FornitoriRisorse>) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("risorseList");
				FornitoriRisorse nuovaRis = new FornitoriRisorse();
				nuovaRis.setIdRisorsa(ris.getRisorsaId());
				nuovaRis.setNomeRisorsa(ris.getNome());
				nuovaRis.setCognomeRisorsa(ris.getCognome());
				nuovaRis.setInfoRisorsa(ris.getInfo());
				nuovaRis.setValSkillSync(ris.getValSkill());
				nuovaRis.setNomeFornitore(nomeFornitore);
				nuovaRis.setDescForn(fr.getDescForn());
				nuovaRis.setIdForni(fr.getIdForni());
				risorse.add(nuovaRis);
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("risorseList");
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("risorseList", risorse);			
			}
		}
		
		return ("risorse-view");
	}

	public String risorsaById() {
		RisorseService risorseService = new RisorseService();
		Risorsa r = risorseService.getRisorsaById(this.id);
		if (r != null) {
			this.id = r.getRisorsaId();
			this.nome = r.getNome();
			this.cognome = r.getCognome();
			this.info = r.getInfo();
			this.valSkillSync = r.getValSkill();
		} else {
			messageErrorData += "Risorsa cercata non esistente";
		}
		return ("risorse-view");
	}
	
	public String cercaDettaglio() {
		System.out.println("cercaDettaglio");
		return "risorsa-dett";
	}

	public String elimina() {
		// in input mi aspetto idrisorsa e id fornitore. Id Risorsa lo ricevo
		// dal form, idfornitore lo ricevo dal servizio della risorsa
		RisorseService risorsaSerice = new RisorseService();
		int idForn = risorsaSerice.getIdFornitoreByIdRisorsa(this.id);
		risorsaSerice.deleteRisorsa(idForn, this.id);
		return ("risorse-view");
	}

	public String addEdit() {
		// se in input, ho un idrisorsa esiste, allora effettuerò un update
		// se in input ho un idrisorsa non esistente nel db, effettuerò una
		// insert
		return ("add-edit-risorsa");
	}

	public String test() {
		return ("risorse-view");
	}
	
	public String goToHome() {
		return ("index");
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public int getFornitoreId() {
		return fornitoreId;
	}

	public void setFornitoreId(int fornitoreId) {
		this.fornitoreId = fornitoreId;
	}

	public int getValSkillSync() {
		return valSkillSync;
	}

	public void setValSkillSync(int valSkillSync) {
		this.valSkillSync = valSkillSync;
	}

	public List<FornitoriRisorse> getRisorseFornitori() {
		return risorseFornitori;
	}

	public void setRisorseFornitori(List<FornitoriRisorse> risorseFornitori) {
		this.risorseFornitori = risorseFornitori;
	}

	public String getMessageErrorData() {
		return messageErrorData;
	}

	public void setMessageErrorData(String messageErrorData) {
		this.messageErrorData = messageErrorData;
	}

	public String getMessageError() {
		return messageError;
	}

	public void setMessageError(String messageError) {
		this.messageError = messageError;
	}

	public String getNomeFornitore() {
		return nomeFornitore;
	}

	public void setNomeFornitore(String nomeFornitore) {
		this.nomeFornitore = nomeFornitore;
	}

}
