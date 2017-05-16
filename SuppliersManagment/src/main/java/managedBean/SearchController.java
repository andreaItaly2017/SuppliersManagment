package managedBean;

import java.io.*;

import javax.faces.bean.*;
import javax.faces.context.*;
import javax.servlet.http.*;
import java.net.*;

@ManagedBean
public class SearchController {
  private String searchString="";
  private final static String GOOGLE_URL="https://www.google.com/#q=";
  
  public String getSearchString() {
    return(searchString);
  }

  public void setSearchString(String searchString) {
    this.searchString = searchString.trim();
  }

  public String doSearch() throws IOException {
    searchString = URLEncoder.encode(searchString, "utf-8");
    String searchUrl = GOOGLE_URL + searchString;
    ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
    HttpServletResponse response = (HttpServletResponse)context.getResponse();
    response.sendRedirect(searchUrl);
    return(null);
  }
}