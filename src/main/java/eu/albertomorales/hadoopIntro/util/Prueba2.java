package eu.albertomorales.hadoopIntro.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Prueba2 {

	public void doIt() {
		String cadena1 = "/hphis/AjaxServlet.servlet?conexion=CH0089&amp;procedencia=2&amp;centro=2258&amp;modulo=QUI&amp;procedenciaObligatoria=S&amp;classtoexecute=com.hphis.corp.ajax.AjaxComprobarProcedenciaModuloCentro";
		String cadena2 = "/hphis/AjaxServlet.servlet?classtoexecute=com.hphis.outpatient.ajaxCitacion.AjaxProcedenciaComprobacion&amp;conexion=CH0089&amp;procedencia=2&amp;centroProce=2258&amp;agenteProce=2505&amp;ambito_proce=HOSP&amp;persona=108208&amp;modulo=QUI&amp;activo=&#37;24&#37;7Bparametros.activo&#37;7D&amp;tiporecurs";
		String cadena3 = "/hphis/AjaxServlet.servlet?conexion=CH0089&amp;classtoexecute=com.hphis.outpatient.ajaxCitacion.AjaxProcedenciaComprobacion&amp;conexion=CH0089&amp;procedencia=5&amp;centroProce=&amp;agenteProce=&amp;ambito_proce=&amp;persona=&amp;modulo=CEX&amp;activo=&#37;24&#37;7Bparametros.activo&#37;7D&amp;tiporecursoProce=";
		Matcher matcher = HCISAjaxServletURL2Pattern.matcher(cadena1);
		if (matcher.find()) {
		    String accion = matcher.group(1);
		    System.out.println("accion='"+accion+"'");
		}
		matcher = HCISAjaxServletURL2Pattern.matcher(cadena2);
		if (matcher.find()) {
		    String accion = matcher.group(1);
		    System.out.println("accion2='"+accion+"'");
		}
		matcher = HCISAjaxServletURL2Pattern.matcher(cadena3);
		if (matcher.find()) {
		    String accion = matcher.group(1);
		    System.out.println("accion3='"+accion+"'");
		}
	}
	
	public static void main(String[] args) {
		Prueba2 prueba = new Prueba2();
		prueba.doIt();
	}

	/*
	private final static Pattern HCISAjaxServletURL1Pattern = Pattern
    		.compile("^\\S+classtoexecute=([^(&)]+)&\\S+$");
    		*/
	private final static Pattern HCISAjaxServletURL2Pattern = Pattern	
    		.compile("^\\S+AjaxServlet\\.servl\\S+classtoexecute=([^&]+)\\S*$");
	
	// BUENO .compile("^\\S+AjaxServlet\\.servlet\\S+classtoexecute=([^(&)]+)");
	
	// .compile("^\\S+classtoexecute=([^(&)]+)");
	        // .compile("^\\S+classtoexecute=([^(&)]+)(&)\\S+$");
    		// .compile("^\\S+classtoexecute=([^(&)]+)&\\S+$");
}
