package eu.albertomorales.hadoopIntro;

import java.io.IOException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.ToolRunner;

public class HCISLogsProccessor extends Configured implements org.apache.hadoop.util.Tool {

	public static class TokenizerMapper extends Mapper<Object, Text, Text, IntWritable> {

		private final static IntWritable one = new IntWritable(1);
		private Text lauri = new Text();
		private DoubleWritable tiempo = new DoubleWritable();

		public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
			Matcher matcher = HCISLogPattern.matcher(value.toString());
			if (matcher.find()) {
			    String tiempoStr	= matcher.group(GRUPO_TIEMPO);
			    String lauriStr		= matcher.group(GRUPO_URI);
		    	Matcher matcherEstaticos = HCISEstaticosURLPattern.matcher(lauriStr);
		    	Matcher matcherAjaxServlet = HCISAjaxServletURLPattern.matcher(lauriStr);
		    	Matcher matcherAjaxServletGenerico = HCISAjaxServletGenericoURLPattern.matcher(lauriStr);
		    	Matcher matcherServletCexCita = HCISServletCexCitaURLPattern.matcher(lauriStr);
		    	Matcher matcherServletCexCitaGenerico = HCISServletCexCitaGenericoURLPattern.matcher(lauriStr);
		    	Matcher matcherImprimirEscritos = HCISImprimirEscritosURLPattern.matcher(lauriStr);
		    	Matcher matcherPlantilla = HCISPlantillaURLPattern.matcher(lauriStr);
		    	Matcher matcherGenerico = HCISGenericURLPattern.matcher(lauriStr);
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
				    	lauriStr = "/hphis//edoctor/tmp/plantillaXXX.html";
				    } else if (matcherGenerico.find()) {
				    	String base = matcherGenerico.group(1);
				    	lauriStr = "/hphis/" + base;
				    } 
					lauri.set(lauriStr);
					context.write(lauri, one);
		    	}
		    }
		}
	}

	public static class IntSumReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
		private IntWritable result = new IntWritable();

		public void reduce(Text key, Iterable<IntWritable> values, Context context)
				throws IOException, InterruptedException {
			int sum = 0;
			for (IntWritable val : values) {
				sum += val.get();
			}
			result.set(sum);
			context.write(key, result);
		}	
	}

	public int run(String[] args) throws Exception {
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://bigdata:9000/");
		Job job = Job.getInstance(conf, "HCIS Logs Proccessor");
		job.setJarByClass(HCISLogsProccessor.class);
		job.setMapperClass(TokenizerMapper.class);
		// job.setCombinerClass(IntSumReducer.class);
		job.setReducerClass(MyIntSumReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		String inputPath = args[0];
		FileInputFormat.addInputPath(job, new Path(inputPath));
		String outputPath = args[1];
		FileOutputFormat.setOutputPath(job, new Path(outputPath));
		System.out.println("INICIO:"+new Date());
		boolean success = job.waitForCompletion(true);
		System.out.println("FIN:"+new Date());
		return success ? 0 : 1;
	}

	public static void main(String[] args) throws Exception {
		HCISLogsProccessor driver = new HCISLogsProccessor();
		int exitCode = ToolRunner.run(driver, args);
		System.exit(exitCode);
	}

	private final static Pattern HCISLogPattern = Pattern
	        .compile("^(?!#)(\\S+\\s+\\S+)\\s+(\\S+)\\s+(\\S+)\\s+(\\S+)\\s+(\\S+\\??\\S+)\\s+(\\S+)\\s+(\\S+)\\s+(.+)\\s+(\\S+)\\s+(\\S+)$");

	private final static Pattern HCISGenericURLPattern = Pattern
	        .compile("^/hphis/(\\S+)\\?\\S+([^(&)]+)");

	private final static Pattern HCISAjaxServletURLPattern = Pattern
	        .compile("^\\S+AjaxServlet\\.servlet\\S+classtoexecute=([^(&)]+)");

	private final static Pattern HCISAjaxServletGenericoURLPattern = Pattern
	        .compile("^\\S+AjaxServlet\\.servlet\\S+");

	private final static Pattern HCISServletCexCitaURLPattern = Pattern
	        .compile("^\\S+ServletCexCita\\.servlet\\S+accion=([^(&)]+)");

	private final static Pattern HCISServletCexCitaGenericoURLPattern = Pattern
	        .compile("^\\S+ServletCexCita\\.servlet\\S+");

	private final static Pattern HCISImprimirEscritosURLPattern = Pattern
	        .compile("^\\\\S+imprimirEscritos.do\\\\?escrito=([^(&)]+)");
	
	private final static Pattern HCISPlantillaURLPattern = Pattern
			.compile("^\\S+/edoctor/tmp/plantilla(\\d+)\\.html$");
	
	private final static Pattern HCISEstaticosURLPattern = Pattern
	        .compile("^\\S+(\\.js|\\.gif|\\.css|\\.jpg)$");
	
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