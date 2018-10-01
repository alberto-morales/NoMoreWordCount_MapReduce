package eu.albertomorales.hadoopIntro.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Prueba4 {

	public void doIt() {
		String cadena1 = "/hphis/adt/controlFlujoUrgDerivacion/mostrarPantallaAltaUrg.adt?numerohc=2693084&amp;conexion=CCE001&amp;episodio=1042353805&amp;llegadaInmediata=false&amp;pacienteIncompleto=true";
		Matcher matcher = HCISGenericURLPattern.matcher(cadena1);
		if (matcher.find()) {
		    String base = matcher.group(1);
		    System.out.println("base='/hphis/"+base+"'");
		}
		String cadena2 = "/hphis/edoctor/PanelDatosPaciente.jsp?numerohc=1323170&amp;conexion=CH0089&amp;codpaciente=1323170&amp;requiereCrearNuevo=N&amp;frameSuperior=SI&amp;accion=seleccionPrincipal";
		matcher = HCISGenericURLPattern.matcher(cadena2);
		if (matcher.find()) {
		    String base = matcher.group(1);
		    System.out.println("base2='/hphis/"+base+"'");
		}
		String cadena3 = "/hphis/edoctor/PanelDatosPaciente.jsp?numerohc=1323170&amp;conexion=CH0089&amp;codpaciente=1323170&amp;requiereCrearNuevo=N&amp;frameSuperior=SI&amp;accion=seleccionPrincipal";
		matcher = HCISGenericURLPattern.matcher(cadena3);
		if (matcher.find()) {
		    String base = matcher.group(1);
		    System.out.println("base3='/hphis/"+base+"'");
		}
	}
	
	public static void main(String[] args) {
		Prueba4 prueba = new Prueba4();
		prueba.doIt();
	}
	
	private final static Pattern HCISGenericURLPattern = Pattern
	        .compile("^/hphis/(\\S+)\\?\\S+([^(&)]+)");

}
