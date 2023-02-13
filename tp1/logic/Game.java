
package org.ucm.tp1.logic;

import org.ucm.tp1.logic.list.GameobjectBoard;
import org.ucm.tp1.view.GamePrinter;
import org.ucm.tp1.view.IPrintable;

import java.util.Random;

import org.ucm.tp1.control.exception.DraculaIsAliveException;
import org.ucm.tp1.control.exception.NoMoreVampiresException;
import org.ucm.tp1.control.exception.NotEnoughCoinsException;
import org.ucm.tp1.control.exception.UnvalidPositionException;
import org.ucm.tp1.logic.gameobject.BloodBank;
import org.ucm.tp1.logic.gameobject.Dracula;
import org.ucm.tp1.logic.gameobject.ExplosiveVampire;
import org.ucm.tp1.logic.gameobject.IAttack;
import org.ucm.tp1.logic.gameobject.Player;
import org.ucm.tp1.logic.gameobject.Slayer;
import org.ucm.tp1.logic.gameobject.Vampire;

public class Game implements IPrintable {

	private long seed;
	private Level level;
	private GameobjectBoard gameobjectBoard;
	private Player player;
	private Random r;
	private GamePrinter tablero;
	private int ciclo;
	private boolean gameEnd;

	public Game(long seed, Level level, Random r) {

		this.seed = seed;
		this.level = level;
		this.r = r;
		this.gameobjectBoard = new GameobjectBoard();
		this.player= new Player();
		this.tablero = new GamePrinter(this,this.dim_x(),this.dim_y());
		this.ciclo = 0;
		this.gameEnd = false;

	}
	
	@Override
	public String getPositionToString(int x, int y) {
		return gameobjectBoard.getStringposicion(y,x);
	}
	
	@Override
	public String getInfo() {
	StringBuilder temp = new StringBuilder("");
		
		temp.append("Number of cycles:"+ciclo+"\n");
		temp.append("Coins:"+player.getMonedas()+"\n");
		temp.append("Remaining vampires:" +Vampire.getVampirequda()+"\n");
		temp.append("Vampires on the board:"+Vampire.getVampireEntablero()+"\n");
		if(Dracula.isDraculaAlive()) {
			temp.append("Dracula is alive\n");
		}
		
		return temp.toString();
	}
	public int getCiclo() {
		return ciclo;
	}

	public Player getPlayer() {
		return player;
	}

	public Level getLevel() {
		return level;
	}

	public int dim_x() {

		return level.getDim_x();
	}

	public int dim_y() {

		return level.getDim_y();

	
	}
	
	public int getNumberOfVampires() {
		
		return level.getNumberOfVampires();
		
	}


	public double getVampireFrequency() {
		
		return level.getVampireFrequency();
		
	}

	public void aniadirSlayer(int x, int y) {
		
		gameobjectBoard.aniadirObject(new Slayer(x,y,this));
	}


	public void accion() {
		
		ciclo++;
		update();
		atacar();    	
		aniadirVampire();
		aniadirDracula();
		aniadirExplosiveVampire() ;
		quitarMuertos();
    
	}


	

	private void atacar() {
		gameobjectBoard.atacar();
		
	}

	public void quitarMuertos() {
		gameobjectBoard.quitarMuertos();
		
	}

	

	public void update() {
			
	player.actualizarMonedas(r);
	gameobjectBoard.avanzarObject();
		
	}
	

	
	public String toString() {
			
		return tablero.toString();
	
	}
	


	public void haymonedas(int coste) throws NotEnoughCoinsException {
		
		if( player.getMonedas() < coste) {
			throw new NotEnoughCoinsException("[ERROR]: Eres pobre :( ");
		}
	}

	public void quitarmonedas(int coste) {
		player.quitarmonedas(coste);
	}
	

	public void deberAniadirVampire() throws NoMoreVampiresException {
		
		if(Vampire.getVampirequda() <= 0 || r.nextDouble() >= level.getVampireFrequency()) {
		throw new NoMoreVampiresException();
		}
				
	}
	public void aniadirVampire() {

		try {
			deberAniadirVampire();	
			int x = dim_x() - 1;
			int y = r.nextInt(dim_y());
			posicionOcupado(x,y);
			gameobjectBoard.aniadirObject(new Vampire(x,y,this));
			Vampire.setVampireEntablero(Vampire.getVampireEntablero()+1);
			Vampire.setVampirequda(Vampire.getVampirequda()-1);
		}catch(NoMoreVampiresException ex) {	
		} catch (UnvalidPositionException ex) {
			System.out.println("[Debug] Vampire blocked");
		}


	}
	public void draculaAlive() throws DraculaIsAliveException {
		if(Dracula.isDraculaAlive()) {
			throw new DraculaIsAliveException("[ERROR]: Dracula is already alive");
		}
	}
	
	private void aniadirDracula() {
		
		try {
			deberAniadirVampire(); 
			draculaAlive();
			int x = dim_x() - 1;
			int y = r.nextInt(dim_y());
			posicionOcupado(x,y);
			gameobjectBoard.aniadirObject(new Dracula(x,y,this));
			Dracula.setDraculaAlive(true);
			Vampire.setVampireEntablero(Vampire.getVampireEntablero()+1);
			Vampire.setVampirequda(Vampire.getVampirequda()-1);

		}catch(NoMoreVampiresException ex) {	
			
		} catch (DraculaIsAliveException e) {
			
		} catch (UnvalidPositionException e) {
			System.out.println("[Debug] Vampire blocked");
		} 
		}


	
	private void aniadirExplosiveVampire() {
		try {
			deberAniadirVampire();
			int x = dim_x() - 1;
			int y = r.nextInt(dim_y());
			posicionOcupado(x,y);
			gameobjectBoard.aniadirObject(new ExplosiveVampire(x,y,this));
			Vampire.setVampireEntablero(Vampire.getVampireEntablero()+1);
			Vampire.setVampirequda(Vampire.getVampirequda()-1);

		}catch(NoMoreVampiresException ex) {

		} catch (UnvalidPositionException e) {
			System.out.println("[Debug] Vampire blocked");
		}
	}


	public void posicionOcupado(int x, int y) throws UnvalidPositionException {
		
		if(gameobjectBoard.posicionOcupado(x,y)) {
			throw new UnvalidPositionException("[ERROR]: Invalid position");
		}
		
	}




	public void reset() {
		
		Vampire.setVampireEntablero(0);
		Vampire.setVampirequda(getNumberOfVampires());
		Dracula.setDraculaAlive(false);
		this.gameobjectBoard = new GameobjectBoard();
		this.player= new Player();
		this.tablero = new GamePrinter(this,this.dim_x(),this.dim_y());
		this.ciclo = 0;
		 
		
	}
	
	
	
	public boolean isFinished() {
		
	
		
		if((Vampire.getVampireEntablero() == 0 && Vampire.getVampirequda() == 0) || Vampire.vampirosGanar()) {
					
			gameEnd = true;
			
			}
		
		return gameEnd;
		
	}

	public void setGameEnd(boolean gameEnd) {
		this.gameEnd = gameEnd;
	}

	public String getWinnerMessage() {
		
	
		if(Vampire.getVampireEntablero() == 0 && Vampire.getVampirequda() == 0) {
			
			return "Ha ganado Player";
			
		} else if(Vampire.vampirosGanar()) {
			
			
			return "Han ganado los vampiros";
						
		
	} 		
		return "Nobody wins...";
	}

	public IAttack getAttackableInPosition(int x, int y) {
		
		return gameobjectBoard.getAttackableInPosition(x,y);
	}

	public void aniadirBloodBank(int x, int y, int z) {
		gameobjectBoard.aniadirObject(new BloodBank(x,y,this,z));
		
	}

	public void aniadirMoneda(int moneda) {
		player.aniadirMoneda(moneda);
		
	}

	public void push() {
		gameobjectBoard.push();
		
	}

	public void lightFlash() {
		gameobjectBoard.lightFlash();
		
	}

	public void aniadirVampire(int x, int y) {

		gameobjectBoard.aniadirObject(new Vampire(x,y,this));
	}

	public void aniadirDracula(int x, int y) {
		
		gameobjectBoard.aniadirObject(new Dracula(x,y,this));
		
	}

	public void aniadirExplosiveVampire(int x, int y) {
		gameobjectBoard.aniadirObject(new ExplosiveVampire(x,y,this));
		
	}

	public int objectNum() {
	
		return 	gameobjectBoard.objectNum();
	}

	public String objectInfo(int i) {
		
		return gameobjectBoard.objectInfo( i);
	}


	
	public String serialize() {
		StringBuilder temp = new StringBuilder("");

		temp.append("Cycles: "+getCiclo()+"\n");
		temp.append("Level: "+getLevel().getName()+"\n");
		temp.append("Coins: "+getPlayer().getMonedas()+"\n");
		temp.append("Remaining Vampires: "+Vampire.getVampirequda()+"\n");
		temp.append("Vampires on the Board: "+Vampire.getVampireEntablero()+"\n");
		temp.append("Game Object List:\n");
		for(int i=0;i<objectNum();i++) {
			temp.append(objectInfo(i)+"\n");
		}
		return temp.toString();

	}
}
