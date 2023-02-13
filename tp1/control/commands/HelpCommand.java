package org.ucm.tp1.control.commands;

import org.ucm.tp1.control.exception.CommandParseException;
import org.ucm.tp1.logic.Game;

public class HelpCommand extends Command {
		
		  protected static final String name = "help";
		  protected static final String shortcut = "h" ;
		  private static final String details = "[h]elp"; 
		  private static final String help = "show this help";
		  
		  
		  public HelpCommand() {
			  
			  super(name,shortcut,details,help);
		  }

		@Override
		public boolean execute(Game game) {
			System.out.println(CommandGenerator.commandHelp());
			return false;
		}

		@Override
		public Command parse(String[] commandWords) throws CommandParseException {
			if(commandWords[0].equalsIgnoreCase("help") ) {
				commandWords[0] = shortcut;
			}
			return parseNoParamsCommand(commandWords);
		}
	


}
