package fr.bacher.gcb.gestion.utils;

import java.time.format.DateTimeFormatter;

/**
 *  @author Fayçal BACHER
 */
public class FormatsDates {
	
	private static final String PATTERN_DAT="dd/MM/YYYY HH:mm:ss";
	/**
	 * Date Formatter
	 */
	public static final DateTimeFormatter CUSTOM_DATE_FORMATTER = DateTimeFormatter.ofPattern(PATTERN_DAT);
	
	
	
	
}
