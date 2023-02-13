package org.ucm.tp1.logic.list;

import java.util.ArrayList;

import org.ucm.tp1.logic.gameobject.GameObject;
import org.ucm.tp1.logic.gameobject.IAttack;


public class GameObjectList {
	
	private ArrayList <GameObject> gameObject;

	
	public GameObjectList( ArrayList<GameObject> gameObject ) {
		
		this.gameObject = gameObject;
		
	}

	public GameObject getStringposicion(int y, int x) {
		
		GameObject o = null;

		for(int i = 0; i < gameObject.size();i++) {
			if (gameObject.get(i).getY() == y && gameObject.get(i).getX() == x && gameObject.get(i).estarVivo() ) {
				
				o = gameObject.get(i);
				break;
			}
		}
		return o;
	
	}

	public void aniadirObject(GameObject gameObject2) {
		
		gameObject.add(gameObject2);
		
	}

	public boolean posicionOcupado(int y, int x) {
		
		for(int i=0; i<gameObject.size();i++) {
			if (gameObject.get(i).getY() == y && gameObject.get(i).getX() == x) {
				
				return true;
			}
		}
		return false;
	}

	public void avanzarObject() {
		for(int i=0; i<gameObject.size();i++) {
			gameObject.get(i).mover();
		}
		
	}

	public void quitarMuerto() {

		for(int i=0; i<gameObject.size();i++) {		
			
			if(!gameObject.get(i).estarVivo()) {
				
				gameObject.remove(i);
				
				
			}
			
		}
	}



	public void atacar() {
		for(int i=0; i<gameObject.size();i++) {		
			gameObject.get(i).attack();
		}
		
	}

	public void push() {
		for(int i=0; i<gameObject.size();i++) {		
			gameObject.get(i).receiveGarlicPush();
		}
		
	}

	public void lightFlash() {
		for(int i=0; i<gameObject.size();i++) {		
			gameObject.get(i).receiveLightFlash();
		}
		
	}

	public int objectNum() {
		return gameObject.size();
	}

	public String objectInfo(int i) {
		StringBuilder temp = new StringBuilder("");
		temp.append(gameObject.get(i).letra()+";");
		temp.append(gameObject.get(i).getX()+";");
		temp.append(gameObject.get(i).getY()+";");
		
		if(gameObject.get(i).nextStep()!=-1) {
		temp.append(gameObject.get(i).nextStep()+";");
		}
		if(gameObject.get(i).cost()!=0) {
		temp.append(gameObject.get(i).cost()+";");
		}
		return temp.toString();
	}

		
	}


