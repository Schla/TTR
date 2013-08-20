package com.beppo.AnTTRoid;

import java.util.ArrayList;

public class Player {

	private ArrayList<Day> days;
	
	public Player() {
		super();
		
		days = new ArrayList<Day>();
		
		addDay();
	}


	public void addDay(){
		/* Add another day */
		days.add(new Day());
	}
	
	public Day getDay(int index){
		/* Get day at index */
		return days.get(index);
	}
	
	public void removeDay(int index){
		/* Remove day at index */
		days.remove(index);
	}
		
}
