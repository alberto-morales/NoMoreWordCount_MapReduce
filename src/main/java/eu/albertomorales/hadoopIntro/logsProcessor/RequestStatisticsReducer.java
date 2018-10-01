package eu.albertomorales.hadoopIntro.logsProcessor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.stat.descriptive.rank.Max;
import org.apache.commons.math3.stat.descriptive.rank.Min;
import org.apache.commons.math3.stat.descriptive.rank.Percentile;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class RequestStatisticsReducer extends Reducer<Text, DoubleWritable, Text, RequestStatistics> {

	public RequestStatisticsReducer() {
		super();
	}
	
	@Override
	protected void setup(Reducer<Text, DoubleWritable, Text, RequestStatistics>.Context context)
			throws IOException, InterruptedException {
		super.setup(context);
	}

	RequestStatistics result = new RequestStatistics();
	List<Double> tiempos = new ArrayList<Double>();
	int count = 0;
	
	public void reduce(Text key, Iterable<DoubleWritable> values, Context context)
			throws IOException, InterruptedException {
		try {
			
			count = 0;
			tiempos.clear();

			for (DoubleWritable val : values) {
				count++;
				tiempos.add(val.get());
			}
			
			double[] arrayTiempos = new double[tiempos.size()];
			int n = 0;
			for (Double tiempoBucle : tiempos) {
				arrayTiempos[n] = tiempoBucle;
				n++;
			}

			double percentile90 = new Percentile().evaluate(arrayTiempos, 90);
			double min = new Min().evaluate(arrayTiempos);
			double max = new Max().evaluate(arrayTiempos);
			
			result.setCount(count);
			result.setPercentile90(percentile90);
			result.setMin(min);
			result.setMax(max);
			context.write(key, result);
		} catch (Exception e) {
			System.out.println("Peta el reducer en la clave de "+key.toString());
			System.out.println(e);
			e.printStackTrace();
			throw e;
		} catch (Error err) {
			System.out.println("Peta el reducer en la clave de "+key.toString());
			System.out.println(err);
			err.printStackTrace();
			throw err;	
		}
	}	
}
