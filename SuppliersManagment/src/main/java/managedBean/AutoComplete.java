package managedBean;

import javax.faces.bean.ManagedBean;

@ManagedBean(name = "autoCompleteView")
public class AutoComplete {
	
	public String paginaRisorse(){
		return ("autocomplete-view");
	}
	
	
	
}