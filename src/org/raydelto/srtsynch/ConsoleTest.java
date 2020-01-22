/**
 * @author Raydelto
 */

package org.raydelto.srtsynch;

import java.io.IOException;

import org.raydelto.srtsynch.srt.SrtSynchronizer;
import org.raydelto.srtsynch.srt.Variation;

public class ConsoleTest {
	public static void main(String[] args) throws IOException {
		SrtSynchronizer synch = new SrtSynchronizer(new Variation(0, 0, 0,30), "test.srt", "synch-test.srt");
		synch.start();
		System.out.println("End");
	}
}
