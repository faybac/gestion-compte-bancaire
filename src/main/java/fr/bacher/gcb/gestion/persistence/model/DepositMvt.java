package fr.bacher.gcb.gestion.persistence.model;

import fr.bacher.gcb.gestion.constantes.TypeOperations;
import fr.bacher.gcb.gestion.persistence.model.dv.CodeTypeMouvement;

/**
 * @author Fayçal BACHER
 *         <li>Class des mouvement de compte de type Depo</li>
 */
public class DepositMvt extends Mouvement {
	private static final long serialVersionUID = -3070176481385975108L;

	private static String typeMouvement = TypeOperations.DEPOSIT;
	
	public static final CodeTypeMouvement CODE_TYPE_MOUVEMENT_ENUM = CodeTypeMouvement.forCode(typeMouvement);

	@Override
	public String typeMouvement() {
		return typeMouvement;
	}

}
