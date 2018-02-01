package com.emc.ps.appmod.adaa.iph.constants;

public enum Color {
	
	color0("#e12d2d"),
	color1("#eb5541"), 
	color2("#FE8523"), 
	color3("#FFA019"), 
	color4("#FFC000"), 
	color5("#F2D616"),
	color6("#BDD72A"),
	color7("#8DBE6D"),
	color8("#00B386"),
	color9("#009A8D"),
	colorMinus("#A9A9A9");
	
	private String color;
	
	private Color(String color){
		this.color = color;
	}
	
	public String getColor() {
		return this.color;
	}

	public static Color convert(String color) {
		for (Color colorEnum : Color.values()) {
			if (color.equals(colorEnum.getColor())) {
				return colorEnum;
			}
		}
		return null;
	}
}
