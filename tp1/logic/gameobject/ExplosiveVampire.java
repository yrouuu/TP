package org.ucm.tp1.logic.gameobject;

import org.ucm.tp1.logic.Game;

public class ExplosiveVampire extends Vampire {

	public ExplosiveVampire(int x, int y, Game game) {
		super(x, y, game);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString(){
		
		return "EV ["+vida+"]";
		
	}

	@Override
	public boolean receiveSlayerAttack(int damage) {

		vida -= damage;
		if(!estarVivo()) {

			setVampireEntablero(getVampireEntablero()-1);

			for(int i=x-1;i<x+2;i++) {

				for(int d=y-1;d<y+2;d++) {
					
					if(x!=i || y!=d) {
						IAttack other = game.getAttackableInPosition(i, d);
						if (other != null )	other.receiveSlayerAttack(danio);

					}
				}



			}
		}

		return true;

	}

	@Override
	public void attack() {
			
			if (estarVivo()) {
			IAttack other = game.getAttackableInPosition(x - 1, y);
			if (other != null )		other. receiveVampireAttack(danio);
			
			}
				
			}
	@Override
	public String letra() {
		// TODO Auto-generated method stub
		return "EV";
			
		
	}
	
}

