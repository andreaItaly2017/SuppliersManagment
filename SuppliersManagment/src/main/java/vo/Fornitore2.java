//package vo;
//
//import java.io.Serializable;
//import java.util.Date;
//import java.util.HashSet;
//import java.util.Set;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.JoinTable;
//import javax.persistence.OneToMany;
//import javax.persistence.Table;
//import javax.persistence.Transient;
//
//import util.DateUtils;
//
//
//@Entity
//@Table(name = "FORNITORI")
//public class Fornitore2 implements Serializable{
//	
//	private static final long serialVersionUID = 776764222850395879L;
//	private long id;
//	private String nome;
//	private String descrizione;
//	private String telefono;
//	private String codIdentifictivo;
//	private Date dataRiunione;
//	
//	private String sampleDay;
//
//	private Set<Risorsa2> risorse = new HashSet<Risorsa2>(0);
//	
//	public Fornitore2(){}
//
//	public Fornitore2(String nome, String descrizione, Set<Risorsa2> studentPhoneNumbers) {
//		this.nome = nome;
//		this.descrizione = descrizione;
//		this.risorse = studentPhoneNumbers;
//	}
//	
//	@Transient
//	public String getSampleDay() {
//		if (dataRiunione == null) {
//			sampleDay = "No date selected.";
//		} else {
//			String message = String.format("La data per la riunione è stata fissata per '%s'.", DateUtils.formatDay(dataRiunione));
//			sampleDay = (message);
//		}
//		return sampleDay;
//	}
//	
//	public void setSampleDay(String sampleDay) {
//		this.sampleDay = sampleDay;
//	}
//
//	public Fornitore2(String nome, String descrizione, String telefono, String codIdentifictivo, Set<Risorsa2> studentPhoneNumbers) {
//		this.nome = nome;
//		this.descrizione = descrizione;
//		this.risorse = studentPhoneNumbers;
//		this.telefono = telefono;
//		this.codIdentifictivo = codIdentifictivo;
//	}
//	
//	public Fornitore2(String nome, String descrizione, String telefono, String codIdentifictivo, Set<Risorsa2> studentPhoneNumbers, Date dataRiunione) {
//		this.nome = nome;
//		this.descrizione = descrizione;
//		this.risorse = studentPhoneNumbers;
//		this.telefono = telefono;
//		this.codIdentifictivo = codIdentifictivo;
//		this.dataRiunione = dataRiunione;
//	}
//
//	@Id
//	@GeneratedValue
//	@Column(name = "ID")
//	public long getId() {
//		return id;
//	}
//
//	public void setId(long id) {
//		this.id = id;
//	}
//
//	@Column(name = "NOME", nullable = false, length = 50)
//	public String getNome() {
//		return nome;
//	}
//
//	public void setNome(String nome) {
//		this.nome = nome;
//	}
//	
//	@Column(name = "DESCRIZIONE", nullable = false, length = 50)
//	public String getDescrizione() {
//		return descrizione;
//	}
//
//	public void setDescrizione(String descrizione) {
//		this.descrizione = descrizione;
//	}
//
//	@OneToMany(cascade = CascadeType.ALL)
//	@JoinTable(name = "FORNITORI_RISORSE", joinColumns = { @JoinColumn(name = "FORNITORE_ID") }, inverseJoinColumns = { @JoinColumn(name = "RISORSA_ID") })
//	public Set<Risorsa2> getRisorse() {
//		return risorse;
//	}
//
//	public void setRisorse(Set<Risorsa2> risorse) {
//		this.risorse = risorse;
//	}
//	
//	@Override
//	public String toString() {
//		StringBuffer sb = new StringBuffer();
//		sb.append("\n----FORNITORE ----\n");
//		sb.append("id=" + id + "\n");
//		sb.append("nome=" + nome + "\n");
//		sb.append("descrizione=" + descrizione + "\n");
//		
//		sb.append("----FORNITORE ----\n");
//		return sb.toString();
//	}
//
//	@Column(name = "TELEFONO", nullable = true, length = 14)
//	public String getTelefono() {
//		return telefono;
//	}
//
//	public void setTelefono(String telefono) {
//		this.telefono = telefono;
//	}
//
//	@Column(name = "CODICE_IDENTIFICATIVO", nullable = true, length = 12)
//	public String getCodIdentifictivo() {
//		return codIdentifictivo;
//	}
//	
//	public void setCodIdentifictivo(String codIdentifictivo) {
//		this.codIdentifictivo = codIdentifictivo;
//	}
//
//	@Column(name = "DT_RIUNIONE", nullable = true)
//	public Date getDataRiunione() {
//		return dataRiunione;
//	}
//
//	public void setDataRiunione(Date dataRiunione) {
//		this.dataRiunione = dataRiunione;
//	}
//
//	
//}
