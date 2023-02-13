package org.ucm.tp1.logic.gameobject;

import org.ucm.tp1.control.exception.UnvalidPositionException;
import org.ucm.tp1.logic.Game;

public class Vampire extends GameObject{

	private static int resistencia = 5;
	private static int vampirequda;
	protected static int vampireEntablero;
	private static boolean vampireGanar;
	protected int hayQueMover;
	
	public Vampire(int x, int y, Game game) {
	
		super(x,y,game,resistencia,1);
		this.hayQueMover = 0;

	}
	
	@Override
	public String toString(){
		
		return "V ["+vida+"]";
		
	}
	
	
	@Override
	public void mover() {
						
		hayQueMover++;
		
		if(hayQueMover >= 2) {
			
			hayQueMover= 0;
			
			try {
				game.posicionOcupado(x-1, y);
				x -= 1 ;
			} catch (UnvalidPositionException e) {
				
			}
								
			if(x < 0) vampireGanar = true;
		}
		
		}
	



	public static boolean vampirosGanar() {
		
		return vampireGanar;
		
	}

	public static int getVampireEntablero() {
		
		return vampireEntablero;
	}

	public static void setVampireEntablero(int vampireEntablero) {
		Vampire.vampireEntablero = vampireEntablero;
	}

	public static int getVampirequda() {
		return vampirequda;
	}

	public static void setVampirequda(int vampirequda) {
		Vampire.vampirequda = vampirequda;
	}
	
	@Override
	public boolean receiveSlayerAttack(int damage) {
		
		vida -= damage;
		if(!estarVivo()) {
			vampireEntablero--;

		}
		
		return true;
		
	}

	@Override
	public void attack() {
			
			if (estarVivo() ) {
			IAttack other = game.getAttackableInPosition(x - 1, y);
			if (other != null )		other. receiveVampireAttack(danio);
			}
			
		
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
			vampireEntablero--;
			
		}
		return true;
	}

	@Override
	public boolean receiveLightFlash() {
		
		vida=0;
		vampireEntablero--;
		
		return true;
		
	}

	@Override
	public String letra() {
		// TODO Auto-generated method stub
		return "V";
	}

	@Override
	public int nextStep() {
		
		return hayQueMover;
	}

	@Override
	public int cost() {
		// TODO Auto-generated method stub
		return 0;
	}
}
