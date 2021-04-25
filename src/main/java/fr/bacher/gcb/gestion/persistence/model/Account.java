package fr.bacher.gcb.gestion.persistence.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

/**
 * @author Fayçal BACHER
 */
@Getter
public class Account implements Serializable {

	private static final long serialVersionUID = -4473448736196671218L;

	private long accountNumber;
	private List<Mouvement> listMouvement;//OneToMany
	private BigDecimal solde;// balance : cet attribut pourrait se déplacer dans un DTO

	public Account(long accountNumber) {
		this.accountNumber = accountNumber;
		listMouvement = new ArrayList<>();
	}

	/**
	 * @return montant total du solde du compte
	 */
	public BigDecimal getSolde() {
		this.solde = listMouvement.stream().map(Mouvement::getMtMouvement).reduce(BigDecimal.ZERO,
				BigDecimal::add);
		return solde;
	}
	

}
