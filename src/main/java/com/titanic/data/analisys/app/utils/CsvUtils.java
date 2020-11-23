package com.titanic.data.analisys.app.utils;

public final class CsvUtils {
	
	private static final Character DOUBLE_QUOTE = '"';
	
	private static final Character COMMA = ',';
	
	
	private CsvUtils() {
	}
	
	
	public static String cleanCommasBetweenDoubleQuotes(String source) {
		StringBuffer sb = new StringBuffer();
		
		boolean betweenDobleQuote = false;
		
		for (int i = 0; i < source.length(); i++) {
			Character c = source.charAt(i);
			
			if (DOUBLE_QUOTE.equals(c)) {
				betweenDobleQuote = !betweenDobleQuote;
			}
			
			if (COMMA.equals(c)) {
				if (!betweenDobleQuote) {
					sb.append(c);
				}
			} else {
				sb.append(c);
			}
		}
		
		return sb.toString();
	}
}
