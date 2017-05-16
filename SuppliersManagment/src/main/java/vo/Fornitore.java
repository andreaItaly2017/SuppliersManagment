package vo;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import util.DateUtils;

@Entity
public class Fornitore {

	private int fornitoreId;
	private String nome;
	private String descrizione;
	private String telefono;
	private String codIdentificativo;
	private Calendar dataRiunione;
	private List<Risorsa> risorse;
	private String sampleDay;
	
	public Fornitore(){}

	public Fornitore(String nome, String descrizione, List<Risorsa> risorse) {
		this.nome = nome;
		this.descrizione = descrizione;
		this.risorse = risorse;
	}
	
	public Fornitore(String nome, String descrizione, String telefono, String codIdentificativo, Calendar dataRiunione, List<Risorsa> risorse) {
		this.nome = nome;
		this.descrizione = descrizione;
		this.telefono = telefono;
		this.codIdentificativo = codIdentificativo;
		this.dataRiunione = dataRiunione;
		this.risorse = risorse;
	}

	@Id
	@TableGenerator(name="fornitoreid", table="fornitorepktb", pkColumnName="fornitorekey", pkColumnValue="fornitoreValue", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.TABLE, generator="fornitoreid")
	public int getFornitoreId() {
		return fornitoreId;
	}

	public void setFornitoreId(int fornitoreId) {
		this.fornitoreId = fornitoreId;
	}

	@Column(nullable=false)
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(length=50)
	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	@Column(length=20)
	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Column(name = "COD_IDENTIFICATIVO", length=50)
	public String getCodIdentificativo() {
		return codIdentificativo;
	}

	public void setCodIdentificativo(String codIdentificativo) {
		this.codIdentificativo = codIdentificativo;
	}

	@Temporal(TemporalType.DATE)
	public Calendar getDataRiunione() {
		return dataRiunione;
	}

	public void setDataRiunione(Calendar dataRiunione) {
		this.dataRiunione = dataRiunione;
	}

	@OneToMany(targetEntity = Risorsa.class, mappedBy = "fornitore", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	public List<Risorsa> getRisorse() {
		return risorse;
	}

	public void setRisorse(List<Risorsa> risorse) {
		this.risorse = risorse;
	}
	
	@Transient
	public String getSampleDay() {
		if (dataRiunione == null) {
			sampleDay = "No date selected.";
		} else {
			Date data = dataRiunione.getTime();
			String message = String.format("La data per la riunione è stata fissata per '%s'.", DateUtils.formatDay(data));
			sampleDay = (message);
		}
		return sampleDay;
	}
	
	public void setSampleDay(String sampleDay) {
		this.sampleDay = sampleDay;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("\n----FORNITORE ----\n");
		sb.append("id=" + fornitoreId + "\n");
		sb.append("nome=" + nome + "\n");
		sb.append("descrizione=" + descrizione + "\n");
		sb.append("telefono=" + telefono + "\n");
		sb.append("codIdentificativo=" + codIdentificativo + "\n");
		sb.append("dataRiunione" + dataRiunione + "\n");
		
		sb.append("----FORNITORE ----\n");
		return sb.toString();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codIdentificativo == null) ? 0 : codIdentificativo.hashCode());
		result = prime * result + (int) (fornitoreId ^ (fornitoreId >>> 32));
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((telefono == null) ? 0 : telefono.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Fornitore))
			return false;
		Fornitore other = (Fornitore) obj;
	
		if (fornitoreId == other.fornitoreId)
			return true;
		else
			return false;
	}
}