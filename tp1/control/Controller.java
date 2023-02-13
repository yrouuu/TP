package org.ucm.tp1.control;

import java.util.Scanner;

import org.ucm.tp1.control.commands.Command;
import org.ucm.tp1.control.commands.CommandGenerator;
import org.ucm.tp1.control.exception.CommandExecuteException;
import org.ucm.tp1.control.exception.CommandParseException;
import org.ucm.tp1.control.exception.GameException;
import org.ucm.tp1.logic.Game;
import org.ucm.tp1.logic.gameobject.Vampire;

public class Controller {
	
	public final String prompt = "Command > ";
	
	public static final String unknownCommandMsg = String.format("Unknown command");
	public static final String invalidCommandMsg = String.format("Invalid command");
	public static final String invalidPositionMsg = String.format("Invalid position");

    private Game game;
    private Scanner sc;
  
    public Controller(Game game, Scanner scanner) {
    	
	    this.game = game;
	    this.sc = scanner;
    }
    
    public void  printGame() {
   	 System.out.println(game);
   
   }
    
    public void run() {

    	Vampire.setVampireEntablero(0);
    	Vampire.setVampirequda(game.getNumberOfVampires());


    	boolean refreshDisplay = true;

    	while (!game.isFinished()){

    		if (refreshDisplay) printGame();
    		refreshDisplay = false;

    		System.out.println(prompt);	
    		String s = sc.nextLine();
    		String[] parameters = s.toLowerCase().trim().split(" ");
    		System.out.println("[DEBUG] Executing: " + s);

    		try {
    			Command command = CommandGenerator.parseCommand(parameters);
    			refreshDisplay = command.execute(game);
    		}
    		catch (GameException ex) {
    			System.out.format(ex.getMessage() + " %n %n");
    		}

    	}

    	if (refreshDisplay) printGame();
    	System.out.println ("[Game over] " + game.getWinnerMessage());

    	sc.close();
    }

}

