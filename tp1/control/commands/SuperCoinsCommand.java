package org.ucm.tp1.control.commands;

import org.ucm.tp1.control.exception.CommandParseException;
import org.ucm.tp1.logic.Game;

public class SuperCoinsCommand extends Command{
	
	  protected static final String name = "coins";
	  protected static final String shortcut = "c" ;
	  private static final String details = "[c]oins"; 
	  private static final String help = "add 1000 coins";

	public SuperCoinsCommand() {
		super(name, shortcut, details, help);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean execute(Game game) {
		game.aniadirMoneda(1000);
		return true;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		
		if(commandWords[0].equalsIgnoreCase("coins") ) {
			commandWords[0] = shortcut;
		}
		return parseNoParamsCommand(commandWords);
	}

}
