package com.titanic.data.analisys.app.statistics.utils;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.titanic.data.analisys.app.statistics.PassengersGroup;

/**
 * This class is a final class with implementations of the kinds of the {@code Consumer} for printer of the statistical 
 * @author jcantero
 *
 */
public final class PrinterFormats {

	private static DecimalFormat df = new DecimalFormat("0.000000");
	
	public static final BiConsumer<String, List<PassengersGroup>> SYSTEM_OUT_TABLE = 
			(statistical, groups) -> {
				
				String[][] table = new String[3][4];
				
				table[0][0] = "";
				table[0][1] = "First class";
				table[0][2] = "Second class";
				table[0][3] = "Third class";
				
				table[1][0] = "FEMALE";
				table[2][0] = "MALE";
				
				List<PassengersGroup> sorted =
						groups
						.stream()
						.sorted((o1, o2) -> o1.getSorter().getGender().compareTo(o2.getSorter().getGender()))
						.sorted((o1, o2) -> o1.getSorter().getPclass().compareTo(o2.getSorter().getPclass()))
						.collect(Collectors.toList());
				
				int k = 1;
				int j = 1;
				for (PassengersGroup group : sorted) {
					table[k][j] = df.format(group.getStatisticValueBy(statistical));
					
					k++;
					
					if (k > 2) {
						j++;
						k = 1;
					}
					
				}
				
				/*
				 * leftJustifiedRows - If true, it will add "-" as a flag to format string to
				 * make it left justified. Otherwise right justified.
				 */
				boolean leftJustifiedRows = false;
				
				/*
				 * Calculate appropriate Length of each column by looking at width of data in
				 * each column.
				 * 
				 * Map columnLengths is <column_number, column_length>
				 */
				Map<Integer, Integer> columnLengths = new HashMap<>();
				Arrays.stream(table).forEach(a -> Stream.iterate(0, (i -> i < a.length), (i -> ++i)).forEach(i -> {
					if (columnLengths.get(i) == null) {
						columnLengths.put(i, 0);
					}
					if (columnLengths.get(i) < a[i].length()) {
						columnLengths.put(i, a[i].length());
					}
				}));
				//System.out.println("columnLengths = " + columnLengths);
			 
				/*
				 * Prepare format String
				 */
				final StringBuilder formatString = new StringBuilder("");
				String flag = leftJustifiedRows ? "-" : "";
				columnLengths.entrySet().stream().forEach(e -> formatString.append("| %" + flag + e.getValue() + "s "));
				formatString.append("|\n");
			 
				/*
				 * Prepare line for top, bottom & below header row.
				 */
				String line = columnLengths.entrySet().stream().reduce("", (ln, b) -> {
					String templn = "+-";
					templn = templn + Stream.iterate(0, (i -> i < b.getValue()), (i -> ++i)).reduce("", (ln1, b1) -> ln1 + "-",
							(a1, b1) -> a1 + b1);
					templn = templn + "-";
					return ln + templn;
				}, (a, b) -> a + b);
				line = line + "+\n";
				System.out.println("Relative Frequency:");
			 
				/*
				 * Print table
				 */
				System.out.print(line);
				Arrays.stream(table).limit(1).forEach(a -> System.out.printf(formatString.toString(), a));
				System.out.print(line);
			 
				Stream.iterate(1, (i -> i < table.length), (i -> ++i))
						.forEach(a -> System.out.printf(formatString.toString(), table[a]));
				System.out.print(line);
			};
		
			
	/**
	 * Hide constructor		
	 */
	private PrinterFormats() {
	}
}
