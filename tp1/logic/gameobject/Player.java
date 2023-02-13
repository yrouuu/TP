package org.ucm.tp1.logic.gameobject;


import java.util.Random;

public class Player {
	
	private int monedas;
	
	
	public Player() {
		
		this.monedas = 50;
		
	}
	
	
	public void actualizarMonedas (Random r) {
		
		if(r.nextFloat() > 0.5) {
			
			monedas += 10;
		}
		
	}

	public int getMonedas() {
		return monedas;
	}


	public void quitarmonedas(int coste) {
		
		monedas-=coste;
	}


	public void aniadirMoneda(int moneda) {
		monedas+=moneda;
	}
	
	
}
