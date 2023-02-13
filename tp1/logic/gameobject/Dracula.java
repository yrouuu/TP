package org.ucm.tp1.logic.gameobject;

import org.ucm.tp1.control.exception.UnvalidPositionException;
import org.ucm.tp1.logic.Game;

public class Dracula extends Vampire{

	private static boolean draculaAlive = false;
	
	public Dracula(int x, int y, Game game) {
		super(x, y, game);
	
		
	}

	@Override
	public String toString(){
		
		return "D ["+vida+"]";
		
	}
	
	@Override
	public void attack() {
			
			if (estarVivo() ) {
			IAttack other = game.getAttackableInPosition(x - 1, y);
			if (other != null )		other. receiveDraculaAttack();
			}
			
		

	}

	@Override
	public boolean receiveSlayerAttack(int damage) {
		
		vida -= damage;
		if(!estarVivo()) {
			setVampireEntablero(getVampireEntablero()-1);
			setDraculaAlive(false);
		}
		
		return true;
		
	}

		
	
	
	public static boolean isDraculaAlive() {
		return draculaAlive;
	}

	public static void setDraculaAlive(boolean draculaAlive) {
		Dracula.draculaAlive = draculaAlive;
	}
	
	@Override
	public boolean receiveGarlicPush() {

		hayQueMover=0;
		
		try {
			game.posicionOcupado(x+1, y);
			x+=1;
		} catch (UnvalidPositionException e) {
			
		}
			

		if(x==game.dim_x()) {
			vida=0;
			setDraculaAlive(false);
			vampireEntablero--;
			
		}
		return true;
	}

	@Override
	public boolean receiveLightFlash() {
		
		
		return false;
		
	}
	@Override
	public String letra() {
		// TODO Auto-generated method stub
		return "D";
}
}