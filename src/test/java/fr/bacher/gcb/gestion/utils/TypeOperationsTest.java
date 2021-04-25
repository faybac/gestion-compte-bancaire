package fr.bacher.gcb.gestion.utils;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import fr.bacher.gcb.gestion.constantes.TypeOperations;
import fr.bacher.gcb.gestion.exceptions.OperationException;

/**
 * @author Fayçal BACHER
 *
 */
public class TypeOperationsTest {
	// DEPOSER
	public final static String DEPOSIT = "DEPOSIT";
	// RETIRER
	public final static String WITHDRAWAL = "WITHDRAWAL";
	
	
	@Test
	void testDeposit() throws OperationException {
		assertEquals(DEPOSIT, TypeOperations.DEPOSIT);
	}
	
	@Test
	void testWithdrawal() throws OperationException {
		assertEquals(WITHDRAWAL,TypeOperations.WITHDRAWAL);
	}

}
