package fr.bacher.gcb.gestion.persistence.model.dv;

import lombok.Getter;

/**
 * @author Fayçal BACHER 
 * <li>Enumération des différents codes types mouvements</li>
 * <li>Exemple d'utilisation du code/valeur : DEPOSIT("DEPOT") </li>
 */
public enum CodeTypeMouvement {
	DEPOSIT("DEPOSIT"), WITHDRAWAL("WITHDRAWAL");

	@Getter
	private String code;

	private CodeTypeMouvement(final String code) {
		this.code = code;
	}

	public static CodeTypeMouvement forCode(final String code) {
		for (CodeTypeMouvement typeMouvement : values()) {
			if (typeMouvement.code.equals(code)) {
				return typeMouvement;
			}
		}
		return null;
	}
}
