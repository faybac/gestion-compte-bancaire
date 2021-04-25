package fr.bacher.gcb.gestion.persistence.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import fr.bacher.gcb.gestion.utils.FormatsDates;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Fayçal BACHER
 *         <li>Class des mouvements de compte</li>
 */
@Setter
@Getter
public abstract class Mouvement implements Serializable {

	private static final long serialVersionUID = -546403524492267182L;

	private Long idTechnique;

	private LocalDateTime dateMouvement;

	private BigDecimal mtMouvement;
	
	//private Account compte;  ManyToOne

	// Opt : Cette valeur sert à déterminer à une opération (mouvement) donnée
	// quelle était la valeur du solde.
	private BigDecimal balance;

	// Le Code Type Mouvement permet de determiner l'operation faite sur le compte
	public abstract String typeMouvement();

	protected Mouvement() {
		super();
		this.idTechnique = generateId();
		setDateMouvement(LocalDateTime.now());
	}

	/**
	 * 
	 * @return generation de l'id technique du mouvement. nb: On peux l'utiliser
	 *         pour générer le numéro de compte dans notre exemple.
	 */
	private static long generateId() {
		return UUID.randomUUID().getMostSignificantBits();
	}

	@Override
	public String toString() {
		return "History [Operation=" + typeMouvement() + "| date="
				+ FormatsDates.CUSTOM_DATE_FORMATTER.format(dateMouvement) + "| amount=" + mtMouvement
				+ "| balance=" + balance + "]";
	}

}
