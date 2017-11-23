/**
 * @author Raydelto
 */
package org.raydelto.srtsynch.ui.listeners;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class NumericListener extends KeyAdapter {

	@Override
	public void keyTyped(KeyEvent e) {
		if (e.getKeyChar() < 48 || e.getKeyChar() > 57) {
			if(e.getKeyChar() != 45) {
				e.consume();
			}			
		}
	}
}
