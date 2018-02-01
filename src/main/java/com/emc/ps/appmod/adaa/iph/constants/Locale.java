package com.emc.ps.appmod.adaa.iph.constants;

public enum Locale {
	EN("en"), AR("ar");

	private String locale;

	private Locale(String locale) {
		this.locale = locale;
	}

	public String getLocale() {
		return this.locale;
	}

	public static Locale convert(String locale) {
		for (Locale localeEnum : Locale.values()) {
			if (locale.equals(localeEnum.getLocale())) {
				return localeEnum;
			}
		}
		return null;
	}
}
