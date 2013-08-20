package com.beppo.AnTTRoid;

public class TTRCalc {

	private Player player;

	public TTRCalc() {
		super();

		/* Only one player supported. */
		player = new Player();
		
		/* currently only three static games are supported. The first one is created in the constructor. */
		player.getDay(0).addGame();
		player.getDay(0).addGame();

	}

	public Player getPlayer() {
		return player;
	}


	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public int getChange() {

		/* Only one day supported */
		return player.getDay(0).calculateChangeFactor();
		
	}
	
}
