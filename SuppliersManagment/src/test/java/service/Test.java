package service;

import java.text.DateFormat;
import java.util.Date;

public class Test {
	public static void main(String[] args) {

		Date d1 = new Date(1_000_000_000_000l);
		DateFormat [] dfa = new DateFormat[6];					
		dfa[0] = DateFormat.getInstance();								//	09/09/01 3.46					
		dfa[1] = DateFormat.getDateInstance();							//	9-set-2001
		dfa[2] = DateFormat.getDateInstance(DateFormat.SHORT);			//	09/09/01
		dfa[3] = DateFormat.getDateInstance(DateFormat.MEDIUM);			//	9-set-2001
		dfa[4] = DateFormat.getDateInstance(DateFormat.FULL);			//	domenica 9 settembre 2001
		dfa[5] = DateFormat.getDateInstance(DateFormat.LONG);			//	9 settembre 2001
		for(DateFormat df : dfa){
			System.out.println(df.format(d1));
		}
		System.out.println("Mia Modifica da git hib browser!!");
		System.out.println("Modifica dal branch!");
	}

}
