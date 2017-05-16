package managedBean;

public class FornitoriRisorse {

	private int idForni;
	private String descForn;
	private String nomeFornitore;
	private int idRisorsa;
	private String cognomeRisorsa;
	private String infoRisorsa;
	private String nomeRisorsa;
	private int valSkillSync;

	/**
	 * @param idForni
	 * @param descForn
	 * @param nomeFornitore
	 * @param idRisorsa
	 * @param cognomeRisorsa
	 * @param infoRisorsa
	 * @param nomeRisorsa
	 * @param valSkillSync
	 */
	public FornitoriRisorse(int idForni, String descForn, String nomeFornitore, int idRisorsa, String cognomeRisorsa,
			String infoRisorsa, String nomeRisorsa, int valSkillSync) {
		this.idForni = idForni;
		this.descForn = descForn;
		this.nomeFornitore = nomeFornitore;
		this.idRisorsa = idRisorsa;
		this.cognomeRisorsa = cognomeRisorsa;
		this.infoRisorsa = infoRisorsa;
		this.nomeRisorsa = nomeRisorsa;
		this.valSkillSync = valSkillSync;
	}

	public FornitoriRisorse() { }

	public int getIdForni() {
		return idForni;
	}

	public void setIdForni(int idForni) {
		this.idForni = idForni;
	}

	public String getDescForn() {
		return descForn;
	}

	public void setDescForn(String descForn) {
		this.descForn = descForn;
	}

	public String getNomeFornitore() {
		return nomeFornitore;
	}

	public void setNomeFornitore(String nomeFornitore) {
		this.nomeFornitore = nomeFornitore;
	}

	public int getIdRisorsa() {
		return idRisorsa;
	}

	public void setIdRisorsa(int idRisorsa) {
		this.idRisorsa = idRisorsa;
	}

	public String getCognomeRisorsa() {
		return cognomeRisorsa;
	}

	public void setCognomeRisorsa(String cognomeRisorsa) {
		this.cognomeRisorsa = cognomeRisorsa;
	}

	public String getInfoRisorsa() {
		return infoRisorsa;
	}

	public void setInfoRisorsa(String infoRisorsa) {
		this.infoRisorsa = infoRisorsa;
	}

	public String getNomeRisorsa() {
		return nomeRisorsa;
	}

	public void setNomeRisorsa(String nomeRisorsa) {
		this.nomeRisorsa = nomeRisorsa;
	}

	public int getValSkillSync() {
		return valSkillSync;
	}

	public void setValSkillSync(int valSkillSync) {
		this.valSkillSync = valSkillSync;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("\n----FORNITORE ----\n");
		sb.append("id=" + idForni + "\n");
		sb.append("nomeForn =" + nomeFornitore + "\n");
		sb.append("descrizione forn =" + descForn + "\n");
		sb.append("id risorsa =" + idRisorsa + "\n");
		sb.append("cognomeRisorsa=" + cognomeRisorsa + "\n");
		sb.append("infoRisorsa" + infoRisorsa + "\n");
		sb.append("nomeRisorsa=" + nomeRisorsa + "\n");
		sb.append("valSkillSync" + valSkillSync + "\n");
		
		sb.append("----FORNITORE ----\n");
		return sb.toString();
	}

}
