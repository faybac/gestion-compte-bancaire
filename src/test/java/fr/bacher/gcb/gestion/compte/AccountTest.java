package fr.bacher.gcb.gestion.compte;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
//import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import fr.bacher.gcb.gestion.constantes.TypeOperations;
import fr.bacher.gcb.gestion.exceptions.OperationException;
import fr.bacher.gcb.gestion.persistence.model.Account;
import fr.bacher.gcb.gestion.services.AcountService;
import fr.bacher.gcb.gestion.services.interfaces.IAcountService;

@TestMethodOrder(OrderAnnotation.class)
class AccountTest {

	public static IAcountService transactionService = new AcountService();
	

	/**
	 * Test d'initialisation du compte 
	 */
	@Test
	//@Order(1)
	@DisplayName("Test d'initialisation du Account")
	void testInitialisationAccount2() {
		Account account = new Account(11111111111111L);
		Assertions.assertNotEquals(0,account.getAccountNumber());
		Assertions.assertNotNull(account.getListMouvement());
		Assertions.assertEquals(0, account.getListMouvement().size());
		Assertions.assertEquals(BigDecimal.ZERO, account.getSolde());
	}
	
	/**
	 * Test de plusieurs depots et plusieurs retrait.
	 * DEPOSIT of 1000, DEPOSIT of 500.99 , WITHDRAWAL 400.53 AND  WITHDRAWAL of 600.46
	 * @throws OperationException
	 */
	@Test
	//@Order(2)
	@DisplayName("Test d'initialisation du Account")
	void testInitialisationAccount3() throws OperationException {
	Account account = new Account(111111111111111111L);
		transactionService.traitementSolde(account, TypeOperations.DEPOSIT,new BigDecimal(1000));
		transactionService.traitementSolde(account, TypeOperations.DEPOSIT, new BigDecimal(500.99));
		transactionService.traitementSolde(account, TypeOperations.WITHDRAWAL, new BigDecimal(400.53));
		transactionService.traitementSolde(account, TypeOperations.WITHDRAWAL, new BigDecimal(600.46));

		BigDecimal soldeAttendu=new BigDecimal(1000).add(new BigDecimal(500.99)).add (new BigDecimal(-400.53)).add(new BigDecimal(-600.46)).setScale(2, BigDecimal.ROUND_HALF_UP);
		
		Assertions.assertEquals(soldeAttendu, account.getSolde());
		
		transactionService.showHistory(account);

	} 


}
