package service;

import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import vo.Fornitore;
import vo.Risorsa;

public class FornitoriRisorseTest3 {

	public static void main(String[] args) { 
		
		AnnotationConfiguration config = new AnnotationConfiguration();
		config.addAnnotatedClass(Fornitore.class);
		config.addAnnotatedClass(Risorsa.class);
		config.configure("hibernate.cfg.xml");

		new SchemaExport(config).create(true, true);

		System.out.println();
	}
}