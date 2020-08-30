package eu.albertomorales.hadoopIntro;

import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;

public class Listar {

	public static void main(String[] args) throws Exception {
	    Configuration conf = new Configuration();
	    FileSystem fs = FileSystem.get(new URI("hdfs://cdh:8020/"), conf);
	    FileStatus[] fileStatus = fs.listStatus(new Path("hdfs://cdh:8020/"));
	    for(FileStatus status : fileStatus){
	        System.out.println(status.getPath().toString());
	    }
	}
	
}