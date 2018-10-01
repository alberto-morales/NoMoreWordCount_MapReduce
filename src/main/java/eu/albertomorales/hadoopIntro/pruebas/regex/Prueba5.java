package eu.albertomorales.hadoopIntro.pruebas.regex;

import java.util.regex.Matcher;
import static eu.albertomorales.hadoopIntro.logsProcessor.HCISLogPattern.*;

public class Prueba5 {

	public void doIt() {
		String cadena = "/hphis/edoctor/tmp/plantilla1533127959823.html";
		Matcher matcher = PlantillaURLPattern.matcher(cadena);
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
	
}
