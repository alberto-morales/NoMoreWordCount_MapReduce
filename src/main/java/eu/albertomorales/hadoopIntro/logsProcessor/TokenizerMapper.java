package eu.albertomorales.hadoopIntro.logsProcessor;

import java.io.IOException;
import java.util.regex.Matcher;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import static eu.albertomorales.hadoopIntro.logsProcessor.HCISLogPattern.*;

public class TokenizerMapper extends Mapper<Object, Text, Text, DoubleWritable> {

	private Text lauri = new Text();
	private DoubleWritable tiempo = new DoubleWritable();

	public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
		Matcher matcher = LinePattern.matcher(value.toString());
		if (matcher.find()) {
		    String tiempoStr	= matcher.group(GRUPO_TIEMPO);
		    String lauriStr		= matcher.group(GRUPO_URI);
	    	Matcher matcherEstaticos = EstaticosURLPattern.matcher(lauriStr);
	    	Matcher matcherAjaxServlet = AjaxServletURLPattern.matcher(lauriStr);
	    	Matcher matcherAjaxServletGenerico = AjaxServletGenericoURLPattern.matcher(lauriStr);
	    	Matcher matcherServletCexCita = ServletCexCitaURLPattern.matcher(lauriStr);
	    	Matcher matcherServletCexCitaGenerico = ServletCexCitaGenericoURLPattern.matcher(lauriStr);
	    	Matcher matcherImprimirEscritos = ImprimirEscritosURLPattern.matcher(lauriStr);
	    	Matcher matcherPlantilla = PlantillaURLPattern.matcher(lauriStr);
	    	Matcher matcherGenerico = GenericURLPattern.matcher(lauriStr);
	    	try {				    			
		    	if (matcherEstaticos.find()) {
		    		lauriStr = "*** ESTATICO [.gif|.js|.css|.jpg] ***";
		    	} else {	
			    	if (matcherAjaxServlet.find()) {
			    		String accion = matcherAjaxServlet.group(1);
				    	lauriStr = "/hphis/AjaxServlet.servlet&classtoexecute=" 
				    			 + accion;
				    } else if (matcherAjaxServletGenerico.find()) {
				    	lauriStr = "/hphis/AjaxServlet.servlet";
				    } else if (matcherServletCexCita.find()) {
				    	String accion = matcherServletCexCita.group(1);
				    	lauriStr = "/hphis/ServletCexCita.servlet&classtoexecute=" 
				    			 + accion;
				    } else if (matcherServletCexCitaGenerico.find()) {
				    	lauriStr = "/hphis/ServletCexCita.servlet";
				    } else if (matcherImprimirEscritos.find()) {
			    		String escrito = matcherImprimirEscritos.group(1);
				    	lauriStr = "/hphis/imprimirEscritos.do?escrito=" 
				    			 + escrito;
				    } else if (matcherPlantilla.find()) {
				    	lauriStr = "/hphis/edoctor/tmp/plantillaXXX.html";
				    } else if (matcherGenerico.find()) {
				    	String base = matcherGenerico.group(1);
				    	lauriStr = "/hphis/" + base;
				    } 
		    	}
	    	} catch (Exception e) {
	    		e.printStackTrace();
	    	}
			lauri.set(lauriStr);
			try {
				Double tiempoDbl = Double.valueOf(tiempoStr);
				tiempo.set(tiempoDbl.doubleValue());
				context.write(lauri, tiempo);
			} catch (Exception e) {
				e.printStackTrace();
			}
	    }
	}
	
	// private static final int GRUPO_NODO = 10;
	// private static final int GRUPO_L2 = 9;
	// private static final int GRUPO_AGENTE = 8;
	// private static final int GRUPO_TAMANIO = 7;
	// private static final int GRUPO_CODIGO = 6;
	private static final int GRUPO_URI = 5;
	// private static final int GRUPO_METODO = 4;
	// private static final int GRUPO_L1 = 3;
	private static final int GRUPO_TIEMPO = 2;
	// private static final int GRUPO_FECHA_HORA = 1;
}
