/**
 * @author Raydelto *
 */
package org.raydelto.srtsynch.file;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.raydelto.srtsynch.srt.SrtSynchronizer;

public class FileParser {
	private SrtSynchronizer synchronizer;
	
	
	public FileParser(SrtSynchronizer synchronizer) {
		super();
		this.synchronizer = synchronizer;
	}

	public void parse(String fileName) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		String line;
		while( (line = reader.readLine()) != null){
			synchronizer.synch(line);
		}
		reader.close();
		synchronizer.write();
	}

}
