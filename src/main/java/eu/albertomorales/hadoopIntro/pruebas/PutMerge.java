package eu.albertomorales.hadoopIntro.pruebas;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;

public class PutMerge {

	private void doIt(Path inputDir, Path hdfsFile) {
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://localhost:9000/");
		// conf.set("hadoop.job.ugi", "bigdata");
		try {
			FileSystem hdfs = FileSystem.get(conf);
			FileSystem local = FileSystem.getLocal(conf);
			FileStatus[] inputFiles = local.listStatus(inputDir); 
			FSDataOutputStream out = hdfs.create(hdfsFile); 
			for (int i=0; i<inputFiles.length; i++) {
				System.out.println(inputFiles[i].getPath().getName());
				FSDataInputStream in = local.open(inputFiles[i].getPath()); 
				byte buffer[] = new byte[256];
				int bytesRead = 0;
				while( (bytesRead = in.read(buffer)) > 0) {
					out.write(buffer, 0, bytesRead);
				}
				in.close();
			}
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		String inputPath = "/home/bigdata/hadoop/ejercicios/ejercicio1/in"; // args[0];
		Path inputDir = new Path(inputPath);
		String hdfsFilePath = "/todo.txt"; // args[1];
		Path hdfsFile = new Path(hdfsFilePath);
		PutMerge putMerge = new PutMerge();
		putMerge.doIt(inputDir, hdfsFile);
	}
}
