package fr.bacher.gcb.gestion.services.interfaces;

import java.math.BigDecimal;

import fr.bacher.gcb.gestion.exceptions.OperationException;
import fr.bacher.gcb.gestion.persistence.model.Account;

//@Service
public interface IAcountService {

	public Account traitementSolde(Account account, String operation, BigDecimal amount)	throws OperationException ;
	
	public Account crediterCompte(Account account, BigDecimal amount) throws OperationException ;

	public Account debiterCompte(Account account, BigDecimal amount) throws OperationException;

	/**
	 * 
	 * @param account
	 * Afficher l'historique du compte en paramêtre.
	 */
	public void showHistory(Account account);

	public void printStatement(Account account);

}
