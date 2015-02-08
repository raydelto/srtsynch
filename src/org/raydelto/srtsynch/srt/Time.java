/**
 * @author Raydelto
 */
package org.raydelto.srtsynch.srt;

import java.util.StringTokenizer;

public class Time {
	private int hour;
	private int minute;
	private int second;
	//Handling it as String since we are not doing any calculations on this value
	private String millisecond;
	public int getHour() {
		return hour;
	}
	public void setHour(int hour) {
		this.hour = hour;
	}
	public int getMinute() {
		return minute;
	}
	public void setMinute(int minute) {
		this.minute = minute;
	}
	public int getSecond() {
		return second;
	}
	public void setSecond(int second) {
		this.second = second;
	}
	public Time(int hour, int minute, int second, String millisecond) {
		super();
		this.hour = hour;
		this.minute = minute;
		this.second = second;
		this.millisecond = millisecond;
	}
	
	public Time(String rawTime){
		String[] parts = rawTime.split(",");
		StringTokenizer tokenizer = new StringTokenizer(parts[0].trim(), ":");
		hour = Integer.parseInt(tokenizer.nextToken());
		minute = Integer.parseInt(tokenizer.nextToken());
		second = Integer.parseInt(tokenizer.nextToken());
		millisecond = parts[1].trim();
		
	}
	
	public String getMillisecond() {
		return millisecond;
	}
	public void setMillisecond(String millisecond) {
		this.millisecond = millisecond;
	}
	@Override
	public String toString() {
		return addZeros(hour) + ":" + addZeros(minute) +":" + addZeros(second) + "," + millisecond;
	}
	
	void changeHour(int change){
		hour += change;
	}
	
	void changeMinute(int change){
		minute += change;
	}
	
	void changeSecond(int change){
		second += change;
	}

	
	String addZeros(int number){
		return number <= 9 ? "0" + number : number+"";
		
	}
	
	void change(Variation var){
		hour += var.getHours();
		minute += var.getMinutes();
		second += var.getSeconds();
	}
	
}

