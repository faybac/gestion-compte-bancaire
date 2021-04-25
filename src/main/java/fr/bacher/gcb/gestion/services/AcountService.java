package fr.bacher.gcb.gestion.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.bacher.gcb.gestion.exceptions.OperationException;
import fr.bacher.gcb.gestion.persistence.model.Account;
import fr.bacher.gcb.gestion.persistence.model.DepositMvt;
import fr.bacher.gcb.gestion.persistence.model.WithdrawalMvt;
import fr.bacher.gcb.gestion.persistence.model.dv.CodeTypeMouvement;
import fr.bacher.gcb.gestion.services.interfaces.IAcountService;
import fr.bacher.gcb.gestion.utils.FormatsDates;

/**
 * @author Fayçal BACHER
 */
public class AcountService implements IAcountService {

	private static Logger logger = LoggerFactory.getLogger(AcountService.class);

	public Account traitementSolde(Account account, String operation, BigDecimal amount) throws OperationException {

		amount = amount.setScale(2, BigDecimal.ROUND_HALF_UP);
		
		if (BigDecimal.ZERO.compareTo(amount) >= 0) {
			logger.info("Le montant {} devrait etre positif !", amount);
			throw new OperationException("Le montant devrait etre positif");
		}

		CodeTypeMouvement codeTypeOperationPaiement = CodeTypeMouvement.forCode(operation);
			switch (codeTypeOperationPaiement) {
			case DEPOSIT: // DEPOSER
				crediterCompte(account, amount);
				break;
			case WITHDRAWAL: // RETIRER
				debiterCompte(account, amount);
				break;
			default:
				break;
			}
		return account;
	}


	/**
	 * Depoter un montant dans le compte bancaire
	 */
	public Account crediterCompte(Account account, BigDecimal amount) throws OperationException {
		
		DepositMvt depotMvt = new DepositMvt();
		depotMvt.setMtMouvement(amount);
		account.getListMouvement().add(depotMvt);
		// MAJ du solde au niveau du mouvement de compte pour l'historique
		depotMvt.setBalance(account.getSolde());
		return account;
	}

	/**
	 * Retirer un montant dans le compte bancaire
	 */
	public Account debiterCompte(Account account, final BigDecimal amount) throws OperationException {

		// Attention hypothese : mt a retirer < solde. Pas de découver ou vider complemetement le compte
		if (account.getSolde().compareTo(amount) >= 0) {
			WithdrawalMvt retirerMvt = new WithdrawalMvt();
			retirerMvt.setMtMouvement(amount.negate());
			account.getListMouvement().add(retirerMvt);
			// MAJ du solde au niveau du mouvement de compte pour l'historique
			retirerMvt.setBalance(account.getSolde());
		}
		return account;
	}

	/**
	 * Afficher l'historique du compte depuis la création
	 */
	public void showHistory(Account account) {
		//Log
		account.getListMouvement().forEach(mvt -> logger.info(mvt.toString()));
		//affichage en console à supprimer
		account.getListMouvement().forEach(mvt -> System.out.println(mvt.toString()));
	}
	
	
	public void printStatement(Account account) {
    	System.out.println("Account [accountNumber=" + account.getAccountNumber() + " Date : "+FormatsDates.CUSTOM_DATE_FORMATTER.format(LocalDateTime.now()) + " , Solde =" + account.getSolde() + "]");
    }
	
	
}
