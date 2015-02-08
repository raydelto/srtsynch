/**
 * @author Raydelto
 */
package org.raydelto.srtsynch.srt;

import java.io.IOException;

import org.raydelto.srtsynch.file.FileParser;
import org.raydelto.srtsynch.file.FileWriter;

public class SrtSynchronizer {
	private Variation variation;
	private StringBuilder builder;
	private FileWriter writer;
	private String fileName;
	private String newFileName;
	private Time startTime;
	private Time endTime;
	
	public void start() throws IOException{
		FileParser parser = new FileParser(this);
		parser.parse(fileName);
	}
	
	public SrtSynchronizer(Variation variation, String fileName, String newFileName) {
		this.variation = variation;
		builder = new StringBuilder();
		writer = new FileWriter();
		this.fileName  = fileName;
		this.newFileName  = newFileName;
	}

	public void synch(String line){
		if(line.contains(":") && line.contains(",") && line.contains("-") && line.contains(">")){
			String[] times = line.split(" --> ");
			startTime = new Time(times[0]);
			endTime = new Time(times[1]);
			startTime.change(variation);
			endTime.change(variation);
			builder.append(startTime).append(" --> ").append(endTime).append("\n");
			
		}else{
			builder.append(line).append("\n");
		}
		
	}
	
	public void write() throws IOException{
		writer.write(newFileName, builder.toString());		
		builder.setLength(0);
	}	

}
