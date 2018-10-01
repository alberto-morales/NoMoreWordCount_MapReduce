package eu.albertomorales.hadoopIntro.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Prueba6 {

	public void doIt() {
		String cadena = "/hphis/ping.jsp";
		Matcher matcher = HCISEstaticosURLPattern.matcher(cadena);
		if (matcher.find()) {
		    System.out.println("es estático? y una mierda.'");
		}
	}
	
	public static void main(String[] args) {
		Prueba6 prueba = new Prueba6();
		prueba.doIt();
	}
	
	private final static Pattern HCISEstaticosURLPattern = Pattern
	        .compile("^\\S+(\\.js|\\.gif|\\.css|\\.jpg)$");

}
