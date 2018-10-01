package eu.albertomorales.hadoopIntro.logsProcessor;

import java.util.Date;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.ToolRunner;

public class KK extends Configured implements org.apache.hadoop.util.Tool {

	public int run(String[] args) throws Exception {
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://bigdata:9000/");
		Job job = Job.getInstance(conf, "HCIS Logs Proccessor");
		job.setJarByClass(KK.class);
		job.setMapperClass(TokenizerMapper.class);
		job.setReducerClass(RequestStatisticsReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(DoubleWritable.class);
		String inputPath = args[0];
		FileInputFormat.addInputPath(job, new Path(inputPath));
		String outputPath = args[1];
		FileOutputFormat.setOutputPath(job, new Path(outputPath));
		System.out.println("INICIIO:"+new Date());
		boolean success = job.waitForCompletion(true);
		System.out.println("FIIN:"+new Date());
		return success ? 0 : 1;
	}

	public static void main(String[] args) throws Exception {
		KK driver = new KK();
		int exitCode = ToolRunner.run(driver, args);
		System.exit(exitCode);
	}

}

