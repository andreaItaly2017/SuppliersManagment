package vo;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="FEEDBACK")
public class Feedback implements Serializable { 
/* test */
	private static final long serialVersionUID = -1030364326811360241L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String esito;
	
	public Feedback(int id, String esito){
		this.id = id;
		this.esito = esito;
	}
	
	public Feedback(String esito){
		this.esito = esito;
	}
	
	public Feedback(){}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name="ESITO")
	@Basic(optional = false)
	public String getEsito() {
		return esito;
	}

	public void setEsito(String esito) {
		this.esito = esito;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer(512);
		sb.append("\n----Feedback----\n");
		sb.append("id=" + id + "\n");
		sb.append("esito=" + esito + "\n");
		
		sb.append("----Feedback----\n");
		return sb.toString();
	}
	
	/*
	private Set<Risorse> risorse = new HashSet<Risorse>();

	public Risorse getRisorsa() {
		return risorsa;
	}

	public void setRisorsa(Risorse risorsa) {
		this.risorsa = risorsa;
		if(risorsa != null && (risorsa.getFeedback() == null || !risorsa.getFeedback().equals(this))){
			risorsa.setFeedback(this);
		}
	}

	
	public void addRisorsa(Risorse risorsa) {
		risorse.add(risorsa);
	}
*/
}
