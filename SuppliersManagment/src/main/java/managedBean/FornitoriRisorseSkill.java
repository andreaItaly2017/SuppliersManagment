package managedBean;

public class FornitoriRisorseSkill extends FornitoriRisorse {
	
	private int skillId;
	private String nomeSkill;
	private int valutazioneSkill;

	public FornitoriRisorseSkill(){}
	
	public FornitoriRisorseSkill(int idForni, String descForn, String nomeFornitore, int idRisorsa, String cognomeRisorsa,
			String infoRisorsa, String nomeRisorsa, int valSkillSync, int skillId, String nomeSkill, int valutazioneSkill){
		super(idForni,  descForn,  nomeFornitore,  idRisorsa,  cognomeRisorsa, infoRisorsa,  nomeRisorsa,  valSkillSync);
		this.skillId = skillId;
		this.nomeSkill = nomeSkill;
		this.valutazioneSkill = valutazioneSkill;
	}

	public int getSkillId() {
		return skillId;
	}

	public void setSkillId(int skillId) {
		this.skillId = skillId;
	}

	public String getNomeSkill() {
		return nomeSkill;
	}

	public void setNomeSkill(String nomeSkill) {
		this.nomeSkill = nomeSkill;
	}

	public int getValutazioneSkill() {
		return valutazioneSkill;
	}

	public void setValutazioneSkill(int valutazioneSkill) {
		this.valutazioneSkill = valutazioneSkill;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("\n----FornitoriRisorseSkill ----\n");
		sb.append("skillId=" + skillId + "\n");
		sb.append("nomeSkill =" + nomeSkill + "\n");
		sb.append("valutazioneSkill forn =" + valutazioneSkill + "\n");
		
		sb.append("----FornitoriRisorseSkill ----\n");
		return super.toString() + sb.toString();
	}
}