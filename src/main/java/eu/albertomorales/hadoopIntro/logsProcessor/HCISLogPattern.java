package eu.albertomorales.hadoopIntro.logsProcessor;

import java.util.regex.Pattern;

public interface HCISLogPattern {

	public static final Pattern LinePattern = Pattern
	        .compile("^(?!#)(\\S+\\s+\\S+)\\s+(\\S+)\\s+(\\S+)\\s+(\\S+)\\s+(\\S+\\??\\S+)\\s+(\\S+)\\s+(\\S+)\\s+(.+)\\s+(\\S+)\\s+(\\S+)$");

	public static final Pattern GenericURLPattern = Pattern
	        .compile("^/hphis/(\\S+)\\?\\S+([^(&)]+)");

	public static final Pattern AjaxServletURLPattern = Pattern
	        .compile("^\\S+AjaxServlet\\.servlet\\S+classtoexecute=([^(&)]+)");

	public static final Pattern AjaxServletGenericoURLPattern = Pattern
	        .compile("^\\S+AjaxServlet\\.servlet\\S+");

	public static final Pattern ServletCexCitaURLPattern = Pattern
	        .compile("^\\S+ServletCexCita\\.servlet\\S+accion=([^(&)]+)");

	public static final Pattern ServletCexCitaGenericoURLPattern = Pattern
	        .compile("^\\S+ServletCexCita\\.servlet\\S+");

	public static final Pattern ImprimirEscritosURLPattern = Pattern
	        .compile("^\\\\S+imprimirEscritos.do\\\\?escrito=([^(&)]+)");
	
	public static final Pattern PlantillaURLPattern = Pattern
			.compile("^\\S+/edoctor/tmp/plantilla(\\d+)\\.html$");
	
	public static final Pattern EstaticosURLPattern = Pattern
	        .compile("^\\S+(\\.js|\\.gif|\\.css|\\.jpg)$");
	
}
