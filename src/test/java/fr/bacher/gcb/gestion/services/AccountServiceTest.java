package fr.bacher.gcb.gestion.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.stream.Stream;

import org.junit.Rule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.rules.ExpectedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.bacher.gcb.gestion.constantes.Symboles;
import fr.bacher.gcb.gestion.constantes.TypeOperations;
import fr.bacher.gcb.gestion.exceptions.OperationException;
import fr.bacher.gcb.gestion.persistence.model.Account;
import fr.bacher.gcb.gestion.services.interfaces.IAcountService;

/**
 * @author Faycal BACHER
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AccountServiceTest {

	private static Logger logger = LoggerFactory.getLogger(AccountServiceTest.class);
	
	private IAcountService transactionService = new AcountService();

	@Test
	@Order(1)
	@DisplayName("US 1:In order to save money As a bank client I want to make a deposit in my account")
	void GivenAccount_WhenDepositMoney_ThenUpdateAccount() throws OperationException {
		System.out.println("\nUS 1:In order to save money As a bank client I want to make a deposit in my account");
		// Given : make a deposit in my account
		Account account = new Account(11111111111111L);
		String operation = TypeOperations.DEPOSIT;
		BigDecimal amount = BigDecimal.valueOf(1000.59);// positif amount
		// When : save money in my account
		transactionService.traitementSolde(account, operation, amount);
		// Then
		System.out.println("Deposit : " + amount + " " + Symboles.EURO_SYMBOLE + " with succes");
		System.out.println("New account balance : " + account.getSolde() + " " + Symboles.EURO_SYMBOLE);
		System.out.println("Nombre d'operation du compte : " + account.getListMouvement().size());

		Assertions.assertEquals(amount, account.getSolde());
	}

	@Test
	@Order(2)
	@DisplayName("US 2: PART 2-1 : In order to retrieve some of my savings As a bank client I want to make a withdrawal from my account")
	void GivenAccount_WheRetrieveAPartOfMoney_ThenUpdateAccount() throws OperationException {
		System.out.println(	"\nUS 2: PART 2-1 : In order to retrieve some of my savings As a bank client I want to make a withdrawal from my account");
		// Given
		Account account = new Account(11111111111111L);
		// When Account not empty
		String operation = TypeOperations.DEPOSIT;
		Stream.of(new BigDecimal(1000), new BigDecimal(500)).forEach(e -> {
			try {
				
				transactionService.traitementSolde(account, operation, e);
			} catch (OperationException e1) {
			}

		});
		System.out.println("Deposit : 1000 AND "+Symboles.EURO_SYMBOLE +" 500 "+Symboles.EURO_SYMBOLE +" with succes");
		System.out.println("New account balance : " + account.getSolde() + " " + Symboles.EURO_SYMBOLE);

		// AND WITHDRAWAL amount 
		BigDecimal amountToRetrieve = new BigDecimal(400);
		String operationWithdrawal = TypeOperations.WITHDRAWAL;
		//THEN Update account
		transactionService.traitementSolde(account, operationWithdrawal, amountToRetrieve);

		System.out.println("Retreive : " + amountToRetrieve + " " + Symboles.EURO_SYMBOLE + " with succes");
		System.out.println("New account balance : " + account.getSolde() + " " + Symboles.EURO_SYMBOLE);
		System.out.println("Nombre d'operation du compte : " + account.getListMouvement().size());

		Assertions.assertEquals(3, account.getListMouvement().size());
		Assertions.assertEquals(BigDecimal.valueOf(1100).setScale(2, BigDecimal.ROUND_HALF_UP), account.getSolde());
	}

	@Test
	@Order(3)
	@DisplayName("US 2: PART 2-2 : In order to retrieve All of my savings As a bank client I want to make a withdrawal from my account")
	void GivenAccount_WheRetrieveAllMoney_ThenUpdateAccount() throws OperationException {
		System.out.println(
				"\nUS 2: PART 2-2 : In order to retrieve All of my savings As a bank client I want to make a withdrawal from my account");
		// Given
		Account account = new Account(11111111111111L);
		String operation = TypeOperations.DEPOSIT;
		Stream.of(new BigDecimal(1000), new BigDecimal(500)).forEach(e -> {
			try {
				transactionService.traitementSolde(account, operation, e);
			} catch (OperationException e1) {
			}
		});
		System.out.println("Deposit : 1000 AND "+Symboles.EURO_SYMBOLE +" 500 "+Symboles.EURO_SYMBOLE +" with succes");
		System.out.println("New account balance : " + account.getSolde() + " " + Symboles.EURO_SYMBOLE);
		
		
		BigDecimal amountToRetrieve = new BigDecimal(1500);
		// WHEN retrieving All of savings money from a account
		transactionService.traitementSolde(account, TypeOperations.WITHDRAWAL, amountToRetrieve);
		// THEN
		System.out.println("Retreive : 1500 "+Symboles.EURO_SYMBOLE +" with succes");
		System.out.println("New account balance : " + account.getSolde() + " " + Symboles.EURO_SYMBOLE);
		System.out.println("Nombre d'operation du compte : " + account.getListMouvement().size());

		Assertions.assertEquals(3, account.getListMouvement().size());
		Assertions.assertEquals(BigDecimal.ZERO, account.getSolde().setScale(0));
	}

	@Rule
	ExpectedException thrownException = ExpectedException.none();

	@Test
	@Order(4)
	@DisplayName("US 2: PART 3 : Test Découvert non permit")
	void GivenAccount_WhenTryToRetrieveMoneyAndSoldeNotSuffisante_ThenLunchException() throws OperationException {
		System.out.println(
				"\nUS 2: PART 2-2 : In order to retrieve All of my savings As a bank client I want to make a withdrawal from my account");
		// Given
		Account account = new Account(11111111111111L);
		String operation = TypeOperations.WITHDRAWAL;
		BigDecimal amount = new BigDecimal(1000);// positif amount
		// When : save update my account
		transactionService.traitementSolde(account, operation, amount);

		thrownException.expect(OperationException.class);
		// thrownException.expectMessage("Erreur: Montant non valide a encaisser devrait etre superieur de 0");
	}

	@Test
	@Order(5)
	@DisplayName("US 2: PART 4 : Test Depo ou Retrait avec un montant 0 non permit")
	void GivenAccount_WhenTryToDepositNullMoney_ThenLunchException() throws OperationException {
			// Given
				Account account = new Account(11111111111111L);
				String operation = TypeOperations.DEPOSIT;
				BigDecimal amount = BigDecimal.ZERO;// positif amount
		 
		 assertThrows(OperationException.class, () -> // When : try to save amount<=0 throws OperationException.
			transactionService.traitementSolde(account, operation, amount)
			);
		 //	thrownException.expect(OperationException.class); //thrownException.expectMessage("Le montant devrait etre positif");
	}
	
	@Test
	@Order(6)
	@DisplayName("US 3 : 1- ShowHistory GivenAccount_WhenShowHistory_PrintAllHistories")
	void GivenAccount_WhenShowHistory_PrintAllHistories() throws OperationException {
		System.out.println("\nUS 3: GivenAccount_WhenShowHistory_PrintAllHistories");
		// Given account with a positif solde
		Account account = new Account(11111111111111L);
		String operation = TypeOperations.DEPOSIT;
		System.out.println("Deposit : 1000"+Symboles.EURO_SYMBOLE +" Deposit : 500"+Symboles.EURO_SYMBOLE );
		
		Stream.of(new BigDecimal(1000), new BigDecimal(500)).forEach(e -> {
			try {
				transactionService.traitementSolde(account, operation, e);
			} catch (OperationException e1) {
			}
		});
		
		// WHEN retreive (or deposit) a money to account
		BigDecimal amountToRetrieve = new BigDecimal(200);
		System.out.println("Retreive : " + amountToRetrieve + " " + Symboles.EURO_SYMBOLE);
		// AND update account
		transactionService.traitementSolde(account, TypeOperations.WITHDRAWAL, amountToRetrieve);
		
		System.out.println("New account balance : " + account.getSolde() + " " + Symboles.EURO_SYMBOLE);
		System.out.println("Nombre d'operation du compte : " + account.getListMouvement().size());

		// THEN Print History
		logger.info("\nHISTORIQUE DU COMPTE numero {}",account.getAccountNumber()); System.out.println("\\nHISTORIQUE DU COMPTE numero "+account.getAccountNumber());
		transactionService.showHistory(account);
		assertEquals(3, account.getListMouvement().size()); 
	}
	
	
	@Test
	@Order(7)
	@DisplayName("US 3 : 2- showHistory")
	void GivenAccount_WhenShowHistoryOperation2_PrintAllHistories() throws OperationException {
		System.out.println("\nUS 3: showHistory");
		// Given
		Account account = new Account(11111111111111L);
		String operation = TypeOperations.DEPOSIT;
		System.out.println("Deposit : 1000"+Symboles.EURO_SYMBOLE +" Deposit : 500"+Symboles.EURO_SYMBOLE );
		Stream.of(new BigDecimal(1000), new BigDecimal(500)).forEach(e -> {
			try {
				transactionService.traitementSolde(account, operation, e);
			} catch (OperationException e1) {
			}
		});
		System.out.println("New account balance : " + account.getSolde() + " " + Symboles.EURO_SYMBOLE);
		
		// WHEN retreive (or deposit) a money to account
		BigDecimal amountToRetrieve = new BigDecimal(200);
		System.out.println("Retreive : " + amountToRetrieve + " " + Symboles.EURO_SYMBOLE);
		// AND Update account 
		transactionService.traitementSolde(account, TypeOperations.WITHDRAWAL, amountToRetrieve);
		
		System.out.println("New account balance : " + account.getSolde() + " " + Symboles.EURO_SYMBOLE);
		System.out.println("Nombre d'operation du compte : " + account.getListMouvement().size());

		// THEN Print History
		logger.info("\nHISTORIQUE DU COMPTE numero {}",account.getAccountNumber());System.out.println("\\nHISTORIQUE DU COMPTE numero "+account.getAccountNumber());
		transactionService.showHistory(account);
		
		System.out.println("---------------printStatement--------------------");
		
		transactionService.printStatement(account);
		assertEquals(3, account.getListMouvement().size()); 
	}

}
