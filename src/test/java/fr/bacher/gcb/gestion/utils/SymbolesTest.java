package fr.bacher.gcb.gestion.utils;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import fr.bacher.gcb.gestion.constantes.Symboles;
import fr.bacher.gcb.gestion.exceptions.OperationException;

/**
* @author Fayçal BACHER
**/
class SymbolesTest {
	
	static  final String  EURO_SYMBOLE = "\u20ac";
	
	@Test
	void testEuroSymbole() throws OperationException {
		assertEquals(EURO_SYMBOLE, Symboles.EURO_SYMBOLE);
	}
}
