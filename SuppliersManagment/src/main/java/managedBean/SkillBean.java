package managedBean;

import javax.faces.bean.ManagedBean;

@ManagedBean(name = "skill")
public class SkillBean {
	
	public String paginaSkill() {
		return ("skill-view");
	}

}