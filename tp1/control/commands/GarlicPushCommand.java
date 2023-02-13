package org.ucm.tp1.control.commands;

import org.ucm.tp1.control.exception.CommandExecuteException;
import org.ucm.tp1.control.exception.CommandParseException;
import org.ucm.tp1.control.exception.NotEnoughCoinsException;
import org.ucm.tp1.control.exception.UnvalidPositionException;
import org.ucm.tp1.logic.Game;
import org.ucm.tp1.logic.gameobject.Slayer;


public class GarlicPushCommand extends Command {

	 protected static final String name = "garlic";
	  protected static final String shortcut = "g" ;
	  private static final String details = "[g]arlic"; 
	  private static final String help = "pushes back vampires";
	  
	public GarlicPushCommand() {
		super(name, shortcut, details, help);
		
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		boolean b=false;
		try{
		
			game.haymonedas(10);
			game.push();
			game.quitarmonedas(10);		
			game.accion();
			b= true;

			}catch(NotEnoughCoinsException ex){

				System.out.println(ex.getMessage());
				throw new CommandExecuteException("[ERROR]: Failed to garlic");
			}

		return b;

		}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		
		if(commandWords[0].equalsIgnoreCase("garlic") ) {
			commandWords[0] = shortcut;
		}
		return parseNoParamsCommand(commandWords);
	}

}
