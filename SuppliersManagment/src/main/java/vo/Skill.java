package vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.TableGenerator;

@Entity 
public class Skill {

	private int skillId;
	private String nome;
	private int valutazione;
	private Risorsa risorsa;
	
	@ManyToOne
	@JoinColumn(name="risorsa_Id")
	public Risorsa getRisorsa() {
		return risorsa;
	}

	public Skill() {}
	
	public Skill( String nome, int valutazione)	{
		this.nome = nome;
		this.valutazione = valutazione;
	}

	@Id
	@TableGenerator(name="skillid", table="skillpktb", pkColumnName="skillkey", pkColumnValue="skillValue", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.TABLE, generator="skillid")
	public int getSkillId() {
		return skillId;
	}
	
	public void setSkillId(int skillId) {
		this.skillId = skillId;
	}
	
	@Column(name = "NOME", nullable = false, length=100)
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getValutazione() {
		return valutazione;
	}

	public void setValutazione(int valutazione) {
		this.valutazione = valutazione;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer(512);
		sb.append("\n----SKILL----\n");
		sb.append("id=" + skillId + "\n");
		sb.append("nome=" + nome + "\n");
		sb.append("valutazione=" + valutazione + "\n");
		
		sb.append("----SKILL----\n");
		return sb.toString();
	}


	public void setRisorsa(Risorsa risorsa) {
		this.risorsa = risorsa;
	}

}