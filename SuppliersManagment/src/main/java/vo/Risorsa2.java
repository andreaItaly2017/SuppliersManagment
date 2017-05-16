//package vo;
//
//import java.io.Serializable;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.Table;
//
//@Entity
//@Table(name = "RISORSE")
//public class Risorsa2 implements Serializable {
//
//	private static final long serialVersionUID = 2535526998573640747L;
//	private long id;
//	private String nome;
//	private String cognome;
//	private String info;
//	private long val_skill_per_sync;
//	
//	public Risorsa2() { }
//
//	public Risorsa2(String nome, String cognome, String info, long val_skill_per_sync) {
//		this.nome = nome;
//		this.cognome = cognome;
//		this.info = info;
//		this.val_skill_per_sync = val_skill_per_sync;
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
//	@Column(name = "NOME", nullable = false, length=50)
//	public String getNome() {
//		return nome;
//	}
//
//	public void setNome(String nome) {
//		this.nome = nome;
//	}
//
//	@Column(name = "COGNOME", nullable = false, length=50)
//	public String getCognome() {
//		return cognome;
//	}
//
//	public void setCognome(String cognome) {
//		this.cognome = cognome;
//	}
//
//	@Column(name = "INFO", nullable = false, length=100)
//	public String getInfo() {
//		return info;
//	}
//
//	public void setInfo(String info) {
//		this.info = info;
//	}
//
//	@Column(name = "VAL_SKILL_PER_SYNC", nullable = true, length=2)
//	public long getVal_skill_per_sync() {
//		return val_skill_per_sync;
//	}
//
//	public void setVal_skill_per_sync(long val_skill_per_sync) {
//		this.val_skill_per_sync = val_skill_per_sync;
//	}
//
//	@Override
//	public String toString() {
//		StringBuffer sb = new StringBuffer();
//		sb.append("\n----RISORSA----\n");
//		sb.append("id=" + id + "\n");
//		sb.append("nome=" + nome + "\n");
//		sb.append("cognome=" + cognome + "\n");
//		sb.append("info=" + info + "\n");
//		sb.append("val_skill_per_sync=" + val_skill_per_sync + "\n");
//		
//		sb.append("----RISORSA----\n");
//		return sb.toString();
//	}
//	
//}