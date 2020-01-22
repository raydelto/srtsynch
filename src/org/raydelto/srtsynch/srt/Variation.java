/**
 * @author Raydelto
 */
package org.raydelto.srtsynch.srt;

public class Variation {
	private int hours;
	private int minutes;
	private int seconds;
	private int milliseconds;
	
	public int getMilliseconds() {
		return milliseconds;
	}
	public void setMilliseconds(int milliseconds) {
		this.milliseconds = milliseconds;
	}
	public int getHours() {
		return hours;
	}
	public void setHours(int hours) {
		this.hours = hours;
	}
	public int getMinutes() {
		return minutes;
	}
	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}
	public int getSeconds() {
		return seconds;
	}
	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}
	public Variation(int hours, int minutes, int seconds, int milliseconds) {
		super();
		this.hours = hours;
		this.minutes = minutes;
		this.seconds = seconds;
		this.milliseconds = milliseconds;
	}	
}
