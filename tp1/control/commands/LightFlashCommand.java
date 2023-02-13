package org.ucm.tp1.control.commands;

import org.ucm.tp1.control.exception.CommandExecuteException;
import org.ucm.tp1.control.exception.CommandParseException;
import org.ucm.tp1.control.exception.NotEnoughCoinsException;
import org.ucm.tp1.logic.Game;

public class LightFlashCommand extends Command{
	
	 protected static final String name = "light";
	  protected static final String shortcut = "l" ;
	  private static final String details = "[l]ight"; 
	  private static final String help = "kills all the vampires";

	public LightFlashCommand() {
		super(name, shortcut, details, help);
		
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		
		boolean b=false;
		try{
		
			game.haymonedas(50);
			game.lightFlash();
			game.quitarmonedas(50);		
			game.accion();
			b= true;

			}catch(NotEnoughCoinsException ex){

				System.out.println(ex.getMessage());
				throw new CommandExecuteException("[ERROR]: Failed to Light Flash");
			}

		return b;

		}


	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		
		if(commandWords[0].equalsIgnoreCase("light") ) {
			commandWords[0] = shortcut;
		}
		return parseNoParamsCommand(commandWords);
	}


}
