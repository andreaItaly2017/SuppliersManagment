package managedBean;

import javax.faces.bean.ManagedBean;

@ManagedBean(name = "feedback")
public class FeedbackBean {
	
	public String paginaFeedback() {
		return ("feedback-view");
	}

}
