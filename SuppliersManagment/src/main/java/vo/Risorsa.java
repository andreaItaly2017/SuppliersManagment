package vo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.TableGenerator;

@Entity
public class Risorsa {

	private int risorsaId;
	private String nome;
	private String cognome;
	private String info;
	private int valSkill;
	private Fornitore fornitore;
	private List<Skill> skills;

	@ManyToOne
	@JoinColumn(name="fornitore_Id")
	public Fornitore getFornitore() {
		return fornitore;
	}

	@OneToMany(targetEntity = Skill.class, mappedBy = "risorsa", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	public List<Skill> getSkills() {
		return skills;
	}

	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}

	public Risorsa() { }

	public Risorsa(String nome, String cognome, String info, int val_skill_per_sync) {
		this.nome = nome;
		this.cognome = cognome;
		this.info = info;
		this.valSkill = val_skill_per_sync;
	}
	
	@Id
	@TableGenerator(name="risorsaid", table="risorsapktb", pkColumnName="risorsakey", pkColumnValue="risorsaValue", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.TABLE, generator="risorsaid")
	public int getRisorsaId() {
		return risorsaId;
	}
	
	public void setRisorsaId(int risorsaId) {
		this.risorsaId = risorsaId;
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
	

	public void setFornitore(Fornitore fornitore) {
		this.fornitore = fornitore;
	}

	@Column(name = "VALUTAZIONE_SKILL")
	public int getValSkill() {
		return valSkill;
	}

	public void setValSkill(int valSkill) {
		this.valSkill = valSkill;
	}
}