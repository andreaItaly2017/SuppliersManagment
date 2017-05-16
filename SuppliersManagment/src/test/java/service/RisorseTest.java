package service;


import org.junit.Test;

import managedBean.FornitoriRisorse;
import util.DaoUtils;
import vo.Risorsa;

public class RisorseTest extends ServiceTestRisorsa{
	
	@Test
	public void deleteRisorsaTest() {
		// mi aspetto in input gli id del fornitore associato alla risorsa e l'
		// id della risorsa
		// idForn lo prendo dal servizio
		// gli altri campi li prendo dal form
		int idRisorsa = 93;
		RisorseService risService = new RisorseService();
		int idForn = risService.getIdFornitoreByIdRisorsa(idRisorsa);
		risService.deleteRisorsa(idForn, idRisorsa);
	}
	
	@Test
	public void lastSequenceTest()	{	
		int lastSequence = DaoUtils.getLastSequence();
		System.out.println(lastSequence);
	}
	
	/**
	 * SELECT risorsa0_.risorsaId AS risorsaId1_1_0_,
       risorsa0_.cognome AS cognome2_1_0_,
       risorsa0_.fornitore_Id AS fornitore_Id6_1_0_,
       risorsa0_.info AS info3_1_0_,
       risorsa0_.nome AS nome4_1_0_,
       risorsa0_.VALUTAZIONE_SKILL AS VALUTAZIONE_SKILL5_1_0_,
       fornitore1_.fornitoreId AS fornitoreId1_0_1_,
       fornitore1_.COD_IDENTIFICATIVO AS COD_IDENTIFICATIVO2_0_1_,
       fornitore1_.dataRiunione AS dataRiunione3_0_1_,
       fornitore1_.descrizione AS descrizione4_0_1_,
       fornitore1_.nome AS nome5_0_1_,
       fornitore1_.telefono AS telefono6_0_1_
		FROM Risorsa risorsa0_
		LEFT OUTER JOIN Fornitore fornitore1_ ON risorsa0_.fornitore_Id=fornitore1_.fornitoreId
		WHERE risorsa0_.risorsaId=?
	 */
	@Test
	public void getRisorsaById(){
		int idRisorsa = 14;
		RisorseService risorseService = new RisorseService();
		Risorsa r = risorseService.getRisorsaById(idRisorsa);
		System.out.println(r);
	}
	
	
	/**
	 * insert into Risorsa (cognome, fornitore_Id, info, nome, VALUTAZIONE_SKILL, risorsaId) values (?, ?, ?, ?, ?, ?)
	 */
	@Test
	public void createRisorsaTestAnnotations() {
		// mi aspetto in input gli id del fornitore associato alla risorsa e l'  id della risorsa
		// tutti i campi li prendo dal form 
		int idFornitore = 19;
		String nomeRisorsa = "Alberto";
		String cognomeRisorsa = "Varruti";
		String infoRisorsa = "Esperto in doc net";
		
		RisorseService risorsaService = new RisorseService();
		Risorsa r = risorsaService.createRisorsa(idFornitore, nomeRisorsa, cognomeRisorsa, infoRisorsa, 7);
		
		System.out.println(r);
	} 

	/**
	 * Nome Fornitore null ==> UPDATE Risorsa
								SET cognome=?,
								    fornitore_Id=?,
								    info=?,
								    nome=?,
								    VALUTAZIONE_SKILL=?
								WHERE risorsaId=?
		Nome Fornitore <> null 	==>	delete from Risorsa where risorsaId=?
		
									insert into Risorsa (cognome, fornitore_Id, info, nome, VALUTAZIONE_SKILL, risorsaId) values (?, ?, ?, ?, ?, ?)
	 */
	@Test
	public void updateRisorsaTest()	{
		// idRisorsa lo prendo dal form, id forn dal servizio
		// gli altri campi li prendo dal formt
		RisorseService risorsaService = new RisorseService();
		FornitoriRisorse  fr = risorsaService.updateRisorsa(21, "Alberto", "Santanna", "Javista", 0, /*null*/"Acotel Group");
		System.out.println(fr);
	}
}