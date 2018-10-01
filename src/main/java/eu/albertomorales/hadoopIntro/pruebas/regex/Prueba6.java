package eu.albertomorales.hadoopIntro.pruebas.regex;

import java.util.regex.Matcher;
import static eu.albertomorales.hadoopIntro.logsProcessor.HCISLogPattern.*;

public class Prueba6 {

	public void doIt() {
		String cadena = "/hphis/ping.jsp";
		Matcher matcher = EstaticosURLPattern.matcher(cadena);
		if (matcher.find()) {
		    System.out.println("es estático? y una mierda.'");
		}
	}
	
	public static void main(String[] args) {
		Prueba6 prueba = new Prueba6();
		prueba.doIt();
	}
	
}
