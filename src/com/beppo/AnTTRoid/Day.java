package com.beppo.AnTTRoid;

import java.util.ArrayList;

public class Day{

	/* On a day, there can be several games */
	private ArrayList<SingleGame> games;
	/* The player has a fix TTR for each day. The new TTR is calculated overnight. Same is valid for ChangeConstant and Nachwuchsausgleich */
	private Integer ttr;
	private Integer changeConstant;
	private Integer nachwuchsausgleich;

	public Day() {
		super();
		
		ttr = new Integer(0);
		changeConstant = new Integer(0);
		nachwuchsausgleich = new Integer(0);
		
		/* Create List */
		games = new ArrayList<SingleGame>();

		/* Create first game */
		games.add(new SingleGame());
	}
	
	public Integer getTtr() {
		return ttr;
	}


	public void setTtr(Integer ttr) {
		this.ttr = ttr;
	}


	public Integer getChangeConstant() {
		return changeConstant;
	}


	public void setChangeConstant(Integer changeConstant) {
		this.changeConstant = changeConstant;
	}


	public Integer getNachwuchsausgleich() {
		return nachwuchsausgleich;
	}


	public void setNachwuchsausgleich(Integer nachwuchsausgleich) {
		this.nachwuchsausgleich = nachwuchsausgleich;
	}	
	
	public void addGame(){
		/* Add another game */
		games.add(new SingleGame());
	}
	
	public SingleGame getGame(int index){
		/* Get game at index */
		return games.get(index);
	}
	
	public void removeGame(int index){
		/* Remove game at index */
		games.remove(index);
	}
	
	public int calculateChangeFactor(){
		/* Calculate the change factor for this day. Value between -n and n (n = number of games) */
		
		long numberOfGamesWon = 0;
		double expectedResult = 0;
		
		for (SingleGame game : games) {

			/* Refer to http://www.bttv.de/fileadmin/bttv/media/000/mannschaft/click-tt/TTRL/20120515_Kurzbeschreibung_TTRL.pdf */
			if (game.getOpponentTTR() != 0) {
				expectedResult += (1 / (1 + Math.pow(10,
						(((double)game.getOpponentTTR() - ttr) / 150))));

				if (game.getWon() == true) {
					numberOfGamesWon++;
				}
			}
		}
		
		return (int) (Math.round((numberOfGamesWon - expectedResult)* changeConstant) + nachwuchsausgleich);
	}

}
