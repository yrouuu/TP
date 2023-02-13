package org.ucm.tp1.control.commands;

import org.ucm.tp1.control.exception.CommandParseException;
import org.ucm.tp1.logic.Game;

public class UpdateCommand extends Command{
	
	  protected static final String name = "none";
	  protected static final String shortcut = "n" ;
	  private static final String details = "[n]one | []"; 
	  private static final String help = "update";
	  
	  public UpdateCommand() {
		  
		  super(name,shortcut,details,help);
	  }

	@Override
	public boolean execute(Game game) {
		game.accion();
		return true;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		if(commandWords[0].equalsIgnoreCase("") ) {
			commandWords[0] = shortcut;
		}
		return parseNoParamsCommand(commandWords);
	}
}
