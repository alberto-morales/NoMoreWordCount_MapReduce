package eu.albertomorales.hadoopIntro;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;

public class MyIntWritable implements Writable {

	int valor = 0;
		
	public MyIntWritable() {
		this.valor = 0;
	}
	
	@Override
	public void readFields(DataInput in) throws IOException {
		valor = in.readInt();
	}

	@Override
	public void write(DataOutput out) throws IOException {
		out.writeInt(valor);		
	}
	
	public String toString() {
		return ""+valor;
	}
	
	public void setValor(int valor) {
		this.valor = valor;
	}

}
