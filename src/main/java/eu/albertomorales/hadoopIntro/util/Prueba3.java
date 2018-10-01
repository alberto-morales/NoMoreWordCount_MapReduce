package eu.albertomorales.hadoopIntro.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Prueba3 {

	public void doIt() {
		String cadena = "/hphis/adt/common/imprimirEscritos.do?escrito=52&amp;ncopias=1&amp;conexion=&amp;multiple=&amp;episodio=1042373072&amp;numerohc=3019247&amp;idioma_9=SELECCION_DEFECTO&amp;parametros=centro&#37;3DCH0009&#37;7Cnumerohc&#37;3D3019247&#37;7Cusuario&#37;3Dmicano&#37;7Cconexion&#37;3DCH0009&#37;7Cfgarante&#37;3D60&#37;7Cfservici";
		Matcher matcher = HCISImprimirEscritosURLPattern.matcher(cadena);
		if (matcher.find()) {
		    String escrito = matcher.group(1);
		    System.out.println("escrito='"+escrito+"'");
		}
	}
	
	public static void main(String[] args) {
		Prueba3 prueba = new Prueba3();
		prueba.doIt();
	}
	
	private final static Pattern HCISImprimirEscritosURLPattern = Pattern
	           .compile("^\\S+imprimirEscritos.do\\S+escrito=(\\d+)\\S*");
			// .compile("^\\S+imprimirEscritos.do\\?escrito=([^(&)]+)");

}
