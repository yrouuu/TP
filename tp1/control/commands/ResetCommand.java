package org.ucm.tp1.control.commands;

import org.ucm.tp1.control.exception.CommandParseException;
import org.ucm.tp1.logic.Game;

public class ResetCommand extends Command{

	  protected static final String name = "reset";
	  protected static final String shortcut = "r" ;
	  private static final String details = "[r]eset"; 
	  private static final String help = "reset game";
	  
	  public ResetCommand() {
		  
		  super(name,shortcut,details,help);
	  }

	@Override
	public boolean execute(Game game) {
		game.reset();
		return true;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		if(commandWords[0].equalsIgnoreCase("reset") ) {
			commandWords[0] = shortcut;
		}
		return parseNoParamsCommand(commandWords);
	}
}
