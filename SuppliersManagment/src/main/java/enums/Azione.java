package enums;

public enum Azione {

	MODIFICA("Modifica"), ELIMINA("Elimina"), AGGIUNGI("Aggiungi");

	private String action;

	Azione(String action) {
		this.action = action;
	}

	public String action() {
		return action;
	}

}
