package managedBean;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class MessageBean {
  private String message="";

  public String getMessage() {
    return(message);
  }

  public void setMessage(String message) {
    this.message = message;
  }
  
  public String makeUpperCase() {
    message = message.toUpperCase();
    return(null);
  }
  
  public String makeLowerCase() {
    message = message.toLowerCase();
    return(null);
  }
  
  public String makeDoubled() {
    message = message +  message;
    return(null);
  }
  
  public String makeReversed() {
    message =
      new StringBuilder(message).reverse().toString();
    return(null);
  }

  public String showSeparately() {
    return("show-message");
  }
}