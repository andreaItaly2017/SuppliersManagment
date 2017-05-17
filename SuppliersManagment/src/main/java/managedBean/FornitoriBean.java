package managedBean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import enums.Azione;
import util.DateUtils;
import service.FornitoreService;
import vo.Fornitore;

@ManagedBean(name = "fornitore")
public class FornitoriBean {

	@ManagedProperty(value = "#{param.id}")
	private int id;
	@ManagedProperty(value = "#{param.nome}")
	private String nome;

	@ManagedProperty(value = "#{param.descrizione}")
	private String descrizione;
	@ManagedProperty(value = "#{param.telefono}")
	private String telefono;
	@ManagedProperty(value = "#{param.codIdentifictivo}")
	private String codIdentifictivo;
	@ManagedProperty(value = "#{param.dataRiunione}")
	private Date dataRiunione;
	@ManagedProperty(value = "#{param.message}")
	private String message = "";

	private String messageError;
	private List<Fornitore> fornitori = new ArrayList<>();
	private String dataRiunioneStrs;

	public String getDataRiunioneStr() {
		return this.dataRiunioneStrs;
	}

	public void setDataRiunioneStrs(String dataRiunioneStrs) {
		this.dataRiunioneStrs = dataRiunioneStrs;
	}


	public Date getDataRiunione() {
		return dataRiunione;
	}

	public void setDataRiunione(Date dataRiunione) {
		this.dataRiunione = dataRiunione;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCodIdentifictivo() {
		return codIdentifictivo;
	}

	public void setCodIdentifictivo(String codIdentifictivo) {
		this.codIdentifictivo = codIdentifictivo;
	}


	public List<Fornitore> getFornitori() {
		return fornitori;
	}

	public void setFornitori(List<Fornitore> fornitori) {
		this.fornitori = fornitori;
	}

	public FornitoriBean() {
	}

	public FornitoriBean(int id, String nome, String descrizione) {
		this.id = id;
		this.nome = nome;
		this.descrizione = descrizione;
	}

	public FornitoriBean(String nome, String descrizione) {
		this.nome = nome;
		this.descrizione = descrizione;
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

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public void getAllFornitori() {
		System.out.println("getAllFornitori");
		FornitoreService fornitoreService = new FornitoreService();
		List<Fornitore> fornitori = null;

		/* controllo se sto facendo una ricerca per nome */
		if (this.nome != null && this.id == 0 && this.descrizione == null) {
			fornitori = fornitoreService.readFornitoriByName(this.nome);
		} else {
			fornitori = fornitoreService.readFornitori();
		}
		System.out.println("fornitori che si visualizzeranno: ");
		for (Fornitore fornitore : fornitori) {
			System.out.println(fornitore);
		}
		messageError = null;
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("fornitori", fornitori);
	}

	public List<Fornitore> getAllFornitoriByName() {
		System.out.println("getAllFornitoriByName");
		FornitoreService fornitoreService = new FornitoreService();
		List<Fornitore> fornitori = fornitoreService.readFornitori();
		messageError = null;
		return fornitori;
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

	public String elimina() {
		System.out.println(">>>elimina<<<");
		FornitoreService fornitoreService = new FornitoreService();
		Fornitore fornitoreEliminato = fornitoreService.deleteFornitore(this.id);
		aggiornaSessione(fornitoreEliminato, Azione.ELIMINA);
		messageError = null;
		return ("fornitore-view");
	}

	public String maskAggiungiModifica() {
		System.out.println(">>>maskAggiungiModifica<<<");
		// ho in input id, nome e descrizione
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();

		String id = "";
		String nome = "";
		String descrizione = "";
		String dataRiunione = "";
		String actionFrom = "";
		if (request != null) {
			id = request.getParameter("id");
			nome = request.getParameter("nome");
			descrizione = request.getParameter("descrizione");
			dataRiunione = request.getParameter("sampleDay");
			actionFrom = request.getParameter("actionFrom");
		}
		if (actionFrom.equals("modifica")) {
			this.id = Integer.parseInt(id);
			this.nome = nome;
			this.descrizione = descrizione;
			this.dataRiunioneStrs = dataRiunione;
		}
		messageError = null;
		return ("add-edit-fornitore");
	}

	public String getSampleDay() {
		if (dataRiunione == null) {
			return ("No date selected.");
		} else {
			// Date d = dataRiunione.getTime();
			String message = String.format("You chose '%s'.", DateUtils.formatDay(dataRiunione));
			return (message);
		}
	}

	public String paginaFornitori() {
		System.out.println(">>>paginaFornitori<<<");
		getAllFornitori();
		messageError = null;
		return "fornitore-view";
	}

	public String cercaDettaglio() {
		System.out.println(">>>CERCADETTAGLIO<<<");
		List<Fornitore> fornitori = (List<Fornitore>) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("fornitori");
		for (Fornitore fornitore : fornitori) {
			if (fornitore.getFornitoreId() == this.id) {
				this.dataRiunioneStrs = fornitore.getSampleDay();
			}
		}
		System.out.println("cercaDettaglio");
		return "fornitore-dett";
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
			String descrizione = "";
			String telefono = "";
			String codIdentificativo = "";
			Calendar dataRiunione = Calendar.getInstance();
			if (actionFrom.equals("aggiungi")) {
				nome = this.nome;
				descrizione = this.descrizione;
				telefono = this.telefono;
				codIdentificativo = this.codIdentifictivo;
				if (this.dataRiunione != null)
					dataRiunione.setTime(this.dataRiunione);

				if (nome == null || nome.equals("")) {
					messageError = "Il nome del fornitore non può esser nullo";
					return ("add-edit-fornitore");
				}
				if (descrizione == null || descrizione.equals("")) {
					messageError = "La descrizione del fornitore non può esser nullo";
					return ("add-edit-fornitore");
				}
				/*
				 * in input mi aspetto: nome e descrizione. L' verrà assegnato
				 * in automatico da hibernate
				 */
				FornitoreService fornitoreService = new FornitoreService();

				Fornitore f = fornitoreService.buildFornitore(nome, descrizione, telefono, codIdentificativo,
						dataRiunione);
				int idFornitore = fornitoreService.createFornitore(f);
				f = fornitoreService.getFornitoreById(idFornitore);

				this.id = f.getFornitoreId();
				this.nome = f.getNome();
				this.descrizione = f.getDescrizione();
				aggiornaSessione(f, Azione.MODIFICA);
				messageError = null;
			} else if (actionFrom.equals("modifica")) {

				id = request.getParameter("id");
				nome = this.nome;
				descrizione = this.descrizione;

				FornitoreService fornitoreService = new FornitoreService();
				Fornitore f = fornitoreService.getFornitoreById(Integer.parseInt(id));
				if (this.dataRiunione != null) {
					dataRiunione.setTime(this.dataRiunione);
					f.setDataRiunione(dataRiunione);
				}
				f.setNome(nome);
				f.setDescrizione(descrizione);
				fornitoreService.updateFornitore(f);
				aggiornaSessione(f, Azione.MODIFICA);

				this.id = Integer.parseInt(id);
				this.nome = nome;
				this.descrizione = descrizione;
				messageError = null;
			}
		}
		return ("fornitore-view");
	}

	private void aggiornaSessione(Fornitore f, Azione azione) {
		List<Fornitore> fornitori = (List<Fornitore>) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("fornitori");
		if (azione.equals(Azione.ELIMINA))
			fornitori.remove(f);
		else if (azione.equals(Azione.MODIFICA)) {
			fornitori.remove(f);
			fornitori.add(f);
		} else if (azione.equals(Azione.AGGIUNGI)) {
			fornitori.add(f);
		}
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("fornitori");
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("fornitori", fornitori);
	}

	public String clickDettaglio() {
		return ("fornitore-view");
	}

	public String getMessageError() {
		return messageError;
	}

	public void setMessageError(String messageError) {
		this.messageError = messageError;
	}

	public List<String> completaNomeFornitore(String nomeFornitorePrefix) {
		System.out.println(">>>COMPLETANOMEFORNITORE<<<");
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

	public List<String> completeLanguage(String languagePrefix) {
		List<String> matches = new ArrayList<>();
		List<String> nomi = getNamesFornitori();
		System.out.println(nomi);
		return (matches);
	}

	public List<String> testCompleteLanguage(String languagePrefix) {
		List<String> matches = new ArrayList<>();
		List<String> nomi = getNamesFornitori();
		System.out.println(nomi);
		return (matches);
	}

	public int ordinaPerNome(Object obj1, Object obj2) {
		System.out.println(">>>ORDINAPERNOME<<<");
		int a = -1;
		Fornitore f1 = null;
		Fornitore f2 = null;
		if (obj1 instanceof Fornitore) {
			f1 = (Fornitore) obj1;
			if (obj2 instanceof Fornitore) {
				f2 = (Fornitore) obj2;
				a = f1.getNome().compareTo(f2.getNome());
			}
		}
		return a;
	}

	public String testMenu() {
		return (null);
	}

	public String nomeScelto() {
		return ("fornitore-view");
	}

	public String goToHome() {
		return ("index");
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}