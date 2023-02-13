package org.ucm.tp1.logic.list;



import java.util.ArrayList;


import org.ucm.tp1.logic.gameobject.GameObject;
import org.ucm.tp1.logic.gameobject.IAttack;

public class GameobjectBoard {
	

	private GameObjectList gameObjectList;
	
	public GameobjectBoard() {
		
		this.gameObjectList = new GameObjectList(new ArrayList<>());
	
	}


	public String getStringposicion(int y, int x) {
		GameObject o = gameObjectList.getStringposicion(y,x);		
		if (o != null) { return o.toString();}
		return " ";
	}
	
	public void aniadirObject(GameObject gameObject) {
		
		gameObjectList.aniadirObject(gameObject);
		
		
	}
 

	public boolean posicionOcupado(int x, int y) {
		
		return gameObjectList.posicionOcupado(y,x);
		
	}


	public void quitarMuertos() {
		gameObjectList.quitarMuerto();
		
	}



	public void avanzarObject() {
		gameObjectList.avanzarObject();
		
	}


	public IAttack getAttackableInPosition(int x, int y) {
		
		return gameObjectList.getStringposicion(y, x);
	}


	public void atacar() {
		gameObjectList.atacar();
		
	}


	public void push() {
		gameObjectList.push();
		
	}


	public void lightFlash() {
		gameObjectList.lightFlash();
		
	}


	public int objectNum() {
		return gameObjectList.objectNum();
	}


	public String objectInfo(int i) {
		
		return gameObjectList.objectInfo(i);
	}


 }
	
	

