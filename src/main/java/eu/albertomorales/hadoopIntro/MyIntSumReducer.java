package eu.albertomorales.hadoopIntro;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class MyIntSumReducer extends Reducer<Text, IntWritable, Text, MyIntWritable> {
	
	private MyIntWritable result = new MyIntWritable();

	public void reduce(Text key, Iterable<IntWritable> values, Context context)				throws IOException, InterruptedException {
		int sum = 0;
		for (IntWritable val : values) {
			sum += val.get();
		}
		result.setValor(sum);
		context.write(key, result);
	}	
	
}
