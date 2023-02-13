package org.ucm.tp1.logic.gameobject;

import org.ucm.tp1.logic.Game;

public class Slayer extends GameObject {

	private static final int coste = 50;
	private static final int vidaMáxima = 3;


	public Slayer( int x, int y, Game game) {

		super(x,y,game,vidaMáxima,1);

	}



	public static int getCoste() {
		return coste;
	}


	@Override
	public String toString(){

		return "S ["+vida+"]";

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
	public void mover() {
		// TODO Auto-generated method stub

	}



	@Override
	public void attack() {

		if (estarVivo() ) {

			for(int i=x+1;i<game.dim_x();i++) {

				IAttack other = game.getAttackableInPosition(i, y);

				if (other != null )		{
					if(other.receiveSlayerAttack(danio)) {
						break;

					}

				};
			}
		}

	}



	@Override
	public String letra() {
		// TODO Auto-generated method stub
		return "S";
	}



	@Override
	public int nextStep() {
		// TODO Auto-generated method stub
		return -1;
	}



	@Override
	public int cost() {
		// TODO Auto-generated method stub
		return 0;
	}



	

}



	