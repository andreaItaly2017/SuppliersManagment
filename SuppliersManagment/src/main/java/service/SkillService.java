package service;

import dao.SkillDao;
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

}
