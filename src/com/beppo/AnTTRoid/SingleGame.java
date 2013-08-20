package com.beppo.AnTTRoid;

public class SingleGame {

	private Integer opponentTTR;
	private Boolean won;

	public SingleGame() {
		super();
		// TODO Auto-generated constructor stub
		opponentTTR = new Integer(0);
		won = new Boolean(false);

	}

	public Integer getOpponentTTR() {
		return opponentTTR;
	}

	public void setOpponentTTR(Integer opponentTTR) {
		this.opponentTTR = opponentTTR;
	}

	public Boolean getWon() {
		return won;
	}

	public void setWon(Boolean won) {
		this.won = won;
	}
	
	

}
