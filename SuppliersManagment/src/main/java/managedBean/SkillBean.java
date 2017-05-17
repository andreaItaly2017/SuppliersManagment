package managedBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import service.SkillService;

@ManagedBean(name = "skill")
public class SkillBean {

	@ManagedProperty(value = "#{param.id}")
	private int id;
	@ManagedProperty(value = "#{param.nome}")
	private String nome;
	@ManagedProperty(value = "#{param.valutazione}")
	private int valutazione;
	@ManagedProperty(value = "#{param.risorsaId}")
	private int risorsaId;

	public String paginaSkill() {
		getAllSkill();
		return ("skill-view");
	}

	private void getAllSkill() {
		System.out.println("=================GETALLSKILL=================");
		List<FornitoriRisorseSkill> skillRisorseFornitori = new ArrayList<FornitoriRisorseSkill>();
		SkillService skillService = new SkillService();
/*
		if (this.nome != null && this.id == 0 && this.valutazione == -1 && this.risorsaId == -1 ) {
			skillRisorseFornitori = skillService.readRisorseJoinRisorseByName(this.nome);
		}  else {
			*/skillRisorseFornitori = skillRisorseFornitori = skillService.readSkillJoinRisorseJoinFornitori();/*
		}*/
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("skillList", skillRisorseFornitori);
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

	public int getValutazione() {
		return valutazione;
	}

	public void setValutazione(int valutazione) {
		this.valutazione = valutazione;
	}

	public int getRisorsaId() {
		return risorsaId;
	}

	public void setRisorsaId(int risorsaId) {
		this.risorsaId = risorsaId;
	}
}