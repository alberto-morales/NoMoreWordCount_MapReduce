package eu.albertomorales.hadoopIntro.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Prueba5 {

	public void doIt() {
		String cadena = "/hphis/edoctor/tmp/plantilla1533127959823.html";
		Matcher matcher = HCISPlantillaURLPattern.matcher(cadena);
		if (matcher.find()) {
			String cual = matcher.group(1);
		    System.out.println("url='/hphis/edoctor/tmp/plantillaXXX.html'");
		    System.out.println("plantilla='"+cual+"'");
		}
	}
	
	public static void main(String[] args) {
		Prueba5 prueba = new Prueba5();
		prueba.doIt();
	}
	
	private final static Pattern HCISPlantillaURLPattern = Pattern
			.compile("^\\S+/edoctor/tmp/plantilla(\\d+)\\.html$");

}
