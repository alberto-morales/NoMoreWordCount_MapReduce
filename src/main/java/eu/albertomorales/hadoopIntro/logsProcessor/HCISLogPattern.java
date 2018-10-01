package eu.albertomorales.hadoopIntro.logsProcessor;

import java.util.regex.Pattern;

public interface HCISLogPattern {

	public static final Pattern LinePattern = Pattern
	        .compile("^(?!#)(\\S+\\s+\\S+)\\s+(\\S+)\\s+(\\S+)\\s+(\\S+)\\s+(\\S+\\??\\S+)\\s+(\\S+)\\s+(\\S+)\\s+(.+)\\s+(\\S+)\\s+(\\S+)$");

	public static final Pattern GenericURLPattern = Pattern
	        .compile("^/hphis/([^\\?]+)\\?\\S+");

	public static final Pattern AjaxServletURLPattern = Pattern
	        .compile("^\\S+AjaxServlet\\.servl\\S+classtoexecute=([^&]+)\\S*$");

	public static final Pattern ServletCexCitaURLPattern = Pattern
	        .compile("^\\S+ServletCexCita\\.servlet\\S+accion=([^&]+)\\S*$");

	public static final Pattern ImprimirEscritosURLPattern = Pattern
	        .compile("^\\S+imprimirEscritos.do\\S+escrito=(\\d+)\\S*");
	
	public static final Pattern PlantillaURLPattern = Pattern
			.compile("^\\S+/edoctor/tmp/plantilla(\\d+)\\.html$");
	
	public static final Pattern EstaticosURLPattern = Pattern
	        .compile("^\\S+(\\.js|\\.gif|\\.css|\\.jpg|\\.png)$");
	
}
