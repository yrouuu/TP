package org.ucm.tp1.logic.gameobject;

import org.ucm.tp1.logic.Game;

public abstract class GameObject implements IAttack {

	protected int x;
	protected int y;
	protected int vida;
	protected int danio;
	protected Game game;
	
	public GameObject(int x,int y,Game game,int vida,int danio) {
		
		this.x = x;
		this.y = y;
		this.game = game;
		this.vida = vida;
		this.danio = danio;
		
	}
	

	public abstract String toString();

	public abstract void mover();

	public abstract String letra();
	
	public abstract int nextStep();
	
	public abstract int cost();
	
	public int getX() {
		return x;
	}


	public int getY() {
		return y;
	}

	

	public boolean estarVivo() {
		
		return vida > 0;	
		
	}
	
	public int getVida() {
		
		return vida;
	}
	
	public int getDanio() {
		
		return danio;
	}


	
	
	

}

