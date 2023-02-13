package org.ucm.tp1.control.commands;

import org.ucm.tp1.control.exception.CommandParseException;
import org.ucm.tp1.logic.Game;

public class ExitCommand extends Command{

	  protected static final String name = "exit";
	  protected static final String shortcut = "e" ;
	  private static final String details = "[e]xit"; 
	  private static final String help = "exit game";
	  
	  public ExitCommand() {
		  
		  super(name,shortcut,details,help);
	  }

	@Override
	public boolean execute(Game game) {
		game.setGameEnd(true);
		return false;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		if(commandWords[0].equalsIgnoreCase("exit") ) {
			commandWords[0] = shortcut;
		}
		return parseNoParamsCommand(commandWords);
		
	}
	
}
