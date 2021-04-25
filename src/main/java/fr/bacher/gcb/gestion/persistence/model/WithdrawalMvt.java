package fr.bacher.gcb.gestion.persistence.model;

import fr.bacher.gcb.gestion.constantes.TypeOperations;
import fr.bacher.gcb.gestion.persistence.model.dv.CodeTypeMouvement;

/**
 * @author Fayçal BACHER
 *         <li>Class de mouvement de compte de type Retrait (Withdrawal)</li>
 */
public class WithdrawalMvt extends Mouvement {

	private static final long serialVersionUID = -7054333844907832172L;
	private static String typeMouvement = TypeOperations.WITHDRAWAL;
	
	public static final CodeTypeMouvement CODE_TYPE_MOUVEMENT_ENUM = CodeTypeMouvement.forCode(typeMouvement);

	@Override
	public String typeMouvement() {
		return typeMouvement;
	}

}