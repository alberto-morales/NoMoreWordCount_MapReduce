package eu.albertomorales.hadoopIntro.logsProcessor;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;

public class RequestStatistics implements Writable {
	
	@Override
	public void readFields(DataInput in) throws IOException {
		count = in.readInt();
		percentile90 = in.readDouble();
		max = in.readDouble();
		min = in.readDouble();
	}

	@Override
	public void write(DataOutput out) throws IOException {
		out.writeInt(count);
		out.writeDouble(percentile90);
		out.writeDouble(max);
		out.writeDouble(min);
	}
	
	@Override
	public String toString() {
		return count + " " + percentile90 + " " + max + " " + min;
	}
	
	public void setCount(int count) {
		this.count = count;
	}

	public void setPercentile90(double percentile90) {
		this.percentile90 = percentile90;
	}

	public void setMax(double max) {
		this.max = max;
	}

	public void setMin(double min) {
		this.min = min;
	}

	
	public RequestStatistics() {
		super();
	}
	
	private int count;
	private double percentile90;
	private double max;
	private double min;
	
}