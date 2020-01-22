/**
 * @author Raydelto
 */
package org.raydelto.srtsynch.srt;

import java.util.StringTokenizer;

public class Time {
	private int hour;
	private int minute;
	private int second;
	private int millisecond;
	
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
	
	public Time(int hour, int minute, int second, int millisecond) {
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
		millisecond = Integer.parseInt(parts[1].trim());		
	}
	
	public int getMillisecond() {
		return millisecond;
	}
	
	public void setMillisecond(int millisecond) {
		this.millisecond = millisecond;
	}

	@Override
	public String toString() {
		return addZeros(hour) + ":" + addZeros(minute) +":" + addZeros(second) + "," + addZerosMilli(millisecond);
	}
	
	private String addZerosMilli(int number){
		if(number <= 9){
			return  "00" + number;	
		}else if(number <= 99){
			return  "0" + number;				
		}else
		{
			return number+"";
		}
	}
	
	private String addZeros(int number){
		return number <= 9 ? "0" + number : number+"";
	}
	
	void change(Variation var){
		hour += var.getHours();
		minute += var.getMinutes();
		second += var.getSeconds();
		millisecond += var.getMilliseconds();
		
		//adjusting values, converting minutes to hours, seconds to minutes and milliseconds to seconds if necessary
		
		if(millisecond<0){
			if(second > 0) {
				second -= 1;
				millisecond += 1000;				
			}else {
				second = 0;
				millisecond = 0;
			}
		}	
		if(second<0){
			if(minute > 0) {
				minute -= 1;
				second += 60;				
			}else {
				minute = 0;
				second = 0;
			}
		}
		if(minute<0){
			if(hour > 0 ) {
				minute+=60;
				hour-=1;				
			}else {
				minute = 0;
				hour = 0;
			}
		}
		
		if(minute>59){
			hour += (minute / 60);
			minute = minute % 60;
		}
		if(second>59){
			minute += (second / 60);
			second = second % 60;
		}if(millisecond>999){
			second += (millisecond / 1000);
			millisecond = millisecond % 1000;
		}
		
		if(hour < 0) {
			hour = 0;
		}
	}	
}
