package vo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "RISORSE")
public class Risorse {

	private long id;
	private String nome;
	private String cognome;
	private String info;
	private long valSkillSync; 
	private Set<Skill> skills = new HashSet<Skill>(0);
	
	public Risorse( ) {  }
	
	public Risorse(String nome, String cognome, String info, long valSkillSync) {
		this.nome = nome;
		this.cognome = cognome;
		this.info = info;
		this.valSkillSync = valSkillSync;
	}
	
	public Risorse(String nome, String cognome, String info) {
		this.nome = nome;
		this.cognome = cognome;
		this.info = info;
	}

	public Risorse(long id, String nome, String cognome, String info) {
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.info = info;
	}

	@Id
	@GeneratedValue
	@Column(name = "ID")
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "NOME", nullable = false, length=10)
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(name = "COGNOME", nullable = false, length=10)
	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	@Column(name = "INFO", nullable = false, length=10)
	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	@Column(name = "VAL_SKILL_PER_SYNC", length=2)
	public long getValSkillSync() {
		return valSkillSync;
	}

	public void setValSkillSync(long valSkillSync) {
		this.valSkillSync = valSkillSync;
	}
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "SKILL_RISORSE", joinColumns = { @JoinColumn(name = "SKILL_ID") }, inverseJoinColumns = { @JoinColumn(name = "RISORSA_ID") })
	public Set<Skill> getSkills() {
		return skills;
	}

	public void setSkills(Set<Skill> skills) {
		this.skills = skills;
	}
}