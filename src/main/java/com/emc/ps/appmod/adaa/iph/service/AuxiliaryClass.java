package com.emc.ps.appmod.adaa.iph.service;

import java.text.DecimalFormat;

/**
 * This class has static methods which have been used in different classes
 * 
 * @author bj3
 *
 */
public class AuxiliaryClass {

	/**
	 * This method will format the data to 2 decimal places.
	 * 
	 * @param data
	 * @return
	 */
	public static Double formatToTwoDecimalPlaces(Double data) {
		if (null != data) {
			DecimalFormat df = new DecimalFormat("#.##");
			data = Double.valueOf(df.format(data));
		}
		return data;
	}

	public static Float formatToTwoDecimalPlaces(Float data) {
		if (null != data) {
			DecimalFormat df = new DecimalFormat("#.##");
			data = Float.valueOf(df.format(data));
		}
		return data;
	}

	/**
	 * This method will format the big numbers into millions / billions format.
	 * 
	 * @param data
	 * @return
	 */
	public static String truncateNumber(double data) {
		long million = 1000000L;
		long billion = 1000000000L;
		long trillion = 1000000000000L;
		long number = Math.round(data);
		boolean negative = false;
		if (number < 0) {
			number = Math.abs(number);
			negative = true;
		}

		/*
		 * if ((number >= thousand) && (number < million)) { float fraction =
		 * calculateFraction(number, thousand); return Float.toString(fraction)
		 * + "K"; } else
		 */

		if ((number >= million) && (number < billion)) {
			float fraction = calculateFraction(number, million);
			fraction = formatToTwoDecimalPlaces(fraction);
			fraction = checkNegativeNumber(fraction, negative);
			return Float.toString(fraction) + "M";
		} else if ((number >= billion)) {
			float fraction = calculateFraction(number, billion);
			fraction = formatToTwoDecimalPlaces(fraction);
			fraction = checkNegativeNumber(fraction, negative);
			return Float.toString(fraction) + "B";
		}

		/*
		 * if ((number >= million) && (number < billion)) { float fraction =
		 * calculateFraction(number, million); fraction =
		 * formatToTwoDecimalPlaces(fraction); fraction =
		 * checkNegativeNumber(fraction, negative); return
		 * Float.toString(fraction) + "M"; } else if ((number >= billion) &&
		 * (number < trillion)) { float fraction = calculateFraction(number,
		 * billion); fraction = formatToTwoDecimalPlaces(fraction); fraction =
		 * checkNegativeNumber(fraction, negative); return
		 * Float.toString(fraction) + "B"; } else if (number >= trillion) {
		 * float fraction = calculateFraction(number, trillion); fraction =
		 * formatToTwoDecimalPlaces(fraction); fraction =
		 * checkNegativeNumber(fraction, negative); return
		 * Float.toString(fraction) + "T"; }
		 */

		String valStr = new DecimalFormat("#.##").format(data);
		return valStr;
	}

	public static float checkNegativeNumber(float fraction, boolean negative) {
		if (negative) {
			fraction = fraction * -1;
		}
		return fraction;

	}

	public static float calculateFraction(long number, long divisor) {
		long truncate = (number * 10L + (divisor / 2L)) / divisor;
		float fraction = (float) truncate * 0.10F;
		return fraction;
	}

}
