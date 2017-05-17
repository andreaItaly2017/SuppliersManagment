package service;

import java.util.ArrayList;
import java.util.List;

import dao.SkillDao;
import managedBean.FornitoriRisorseSkill;
import vo.Fornitore;
import vo.Risorsa;
import vo.Skill;

public class SkillService {
	
	SkillDao skillDao = new SkillDao();
	
	public Skill createSkill(int idRisorsa,  String nome, int valutazione){
		return skillDao.createSkill(idRisorsa, nome, valutazione);
	}

	public int createSkill(Skill skill) {
		return skillDao.createSkill(skill);
	}

	public void deleteSkill(int idSkill1) {
		skillDao.deleteSkill(idSkill1);
	}
	
	public List<FornitoriRisorseSkill> readSkillJoinRisorseJoinFornitori()	{
		List<FornitoriRisorseSkill> risorseFornitoriSkill = new ArrayList<FornitoriRisorseSkill>(0);
		List<Risorsa> risorse = new ArrayList<Risorsa>(0);
		List<Object[]> skills = skillDao.readSkillJoinRisorseJoinFornitori();

		for (Object[] forn_risorsa_skill : skills) {
			Fornitore forn = (Fornitore) forn_risorsa_skill[0];
			Risorsa ris = (Risorsa) forn_risorsa_skill[1];
			Skill skill = (Skill) forn_risorsa_skill[2];
			risorse.add(ris);
			System.out.print("Fornitore: " +  "id: " + forn.getFornitoreId() + " nome: " + forn.getNome() + " descrizione: " + forn.getDescrizione() + "\nRisorsa " + 
			" id: " + ris.getRisorsaId() + " cognome: " + ris.getCognome() + " nome: " + ris.getNome() + " info: " + ris.getInfo() + " val skill sync: " + ris.getValSkill()
			+ " skillId: " + skill.getSkillId() + " nome skill: " + skill.getNome() + " valutazione: " + skill.getValutazione());
			FornitoriRisorseSkill fornitoriRisorseSkill = new FornitoriRisorseSkill( forn.getFornitoreId(), forn.getDescrizione(), forn.getNome(),
															ris.getRisorsaId(), ris.getCognome(), ris.getInfo(), ris.getNome(), ris.getValSkill(), 
															skill.getSkillId(), skill.getNome(), skill.getValutazione());
			risorseFornitoriSkill.add(fornitoriRisorseSkill);
		}
		return risorseFornitoriSkill;
	}

}
