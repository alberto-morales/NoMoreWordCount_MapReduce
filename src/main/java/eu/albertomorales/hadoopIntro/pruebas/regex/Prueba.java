package eu.albertomorales.hadoopIntro.pruebas.regex;

import java.util.regex.Matcher;

import static eu.albertomorales.hadoopIntro.logsProcessor.HCISLogPattern.*;

public class Prueba {

	public void doIt() {
		String cadena = "2018-07-30	00:18:55	0.003	-	GET	/hphis/axis/HL7Filler	200	130	-	-	prohospital01:7110";
		Matcher matcher = LinePattern.matcher(cadena);
		if (matcher.find()) {
		    String fechaHora	= matcher.group(GRUPO_FECHA_HORA);
		    String tiempo 		= matcher.group(GRUPO_TIEMPO);
		    String l1 			= matcher.group(GRUPO_L1);
		    String metodo 		= matcher.group(GRUPO_METODO);
		    String lauri 		= matcher.group(GRUPO_URI);
		    String codigo 		= matcher.group(GRUPO_CODIGO);
		    String tamanio 		= matcher.group(GRUPO_TAMANIO);
		    String agente 		= matcher.group(GRUPO_AGENTE);
		    String l2 			= matcher.group(GRUPO_L2);
		    String nodo 		= matcher.group(GRUPO_NODO);
		    System.out.println("fechaHora='"+fechaHora+"'");
		    System.out.println("tiempo='"+tiempo+"'");
		    System.out.println("l1='"+l1+"'");
		    System.out.println("metodo='"+metodo+"'");
		    System.out.println("lauri='"+lauri+"'");
		    System.out.println("codigo='"+codigo+"'");
		    System.out.println("tamanio='"+tamanio+"'");
		    System.out.println("agente='"+agente+"'");
		    System.out.println("l2='"+l2+"'");
		    System.out.println("nodo='"+nodo+"'");
		}
	}
	
	public static void main(String[] args) {
		Prueba prueba = new Prueba();
		prueba.doIt();
	}
	
	private static final int GRUPO_NODO = 10;
	private static final int GRUPO_L2 = 9;
	private static final int GRUPO_AGENTE = 8;
	private static final int GRUPO_TAMANIO = 7;
	private static final int GRUPO_CODIGO = 6;
	private static final int GRUPO_URI = 5;
	private static final int GRUPO_METODO = 4;
	private static final int GRUPO_L1 = 3;
	private static final int GRUPO_TIEMPO = 2;
	private static final int GRUPO_FECHA_HORA = 1;

}
