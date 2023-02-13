package org.ucm.tp1.logic.gameobject;

import org.ucm.tp1.logic.Game;

public class BloodBank extends GameObject {

	int z;
	
	public BloodBank(int x, int y, Game game, int z) {
		super(x, y, game, 1, 0);
		this.z=z;
		
	}

	@Override
	public void attack() {
	
	
	}


	@Override
	public String toString(){

		return "B ["+z+"]";

	}

	@Override
	public void mover() {
		
		game.aniadirMoneda((z*10)/100);
		
		
	}
	
	@Override
	public boolean receiveVampireAttack(int damage) {

		vida -= damage;

		return true;

	}
	@Override
	public boolean receiveDraculaAttack() {

		vida = 0;

		return true;

	}

	@Override
	public String letra() {
		// TODO Auto-generated method stub
		return "B";
	}

	@Override
	public int nextStep() {
		// TODO Auto-generated method stub
		return -1;
	}

	@Override
	public int cost() {
		return z;
	}

	

}
