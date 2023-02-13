package org.ucm.tp1.control.commands;

import org.ucm.tp1.control.exception.CommandExecuteException;
import org.ucm.tp1.control.exception.CommandParseException;
import org.ucm.tp1.control.exception.NotEnoughCoinsException;
import org.ucm.tp1.control.exception.UnvalidPositionException;
import org.ucm.tp1.logic.Game;
import org.ucm.tp1.logic.gameobject.Slayer;

public class AddCommand extends Command{

	  protected static final String name = "add";
	  protected static final String shortcut = "a" ;
	  private static final String details = "[a]dd <x> <y>"; 
	  private static final String help = "add a slayer in position x, y";
	  private int x;
	  private int y;
	
	public AddCommand() {
		super(name, shortcut, details, help);

	}
	
	
	@Override
	public boolean execute(Game game) throws CommandExecuteException {

		
			try{
				posicionValida(game);
				game.haymonedas(Slayer.getCoste());
				game.quitarmonedas(Slayer.getCoste());				
				game.aniadirSlayer(x,y); 		
				game.accion();
				return true;

			}catch(UnvalidPositionException ex) {
				System.out.println(ex.getMessage());
					throw new CommandExecuteException("[ERROR]: Failed to add this slayer");


				}catch(NotEnoughCoinsException ex){
					System.out.println(ex.getMessage());
					throw new CommandExecuteException("[ERROR]: Failed to add this slayer");
				}


			}


	public void posicionValida(Game game) throws UnvalidPositionException {
		
		if((this.x < 0 || this.x >= game.dim_x()-1) || (this.y < 0 || this.y >= game.dim_y())  ) {
			throw new UnvalidPositionException("[ERROR]: Invalid position");
		}
		game.posicionOcupado(x, y);
	}
	@Override
	public Command parse(String[] commandWords) throws CommandParseException {

		if(matchCommandName(commandWords[0]) ) {

			if(commandWords.length == 1) {
				 throw new CommandParseException("[ERROR]: Command "+ name+" :"+incorrectNumberOfArgsMsg);
			}
			if(commandWords.length == 3) {
				
			try {
				this.x = Integer.parseInt(commandWords[1]);
				this.y = Integer.parseInt(commandWords[2]);
				return this;
				}catch (NumberFormatException nfe) {
					
			
					throw new CommandParseException("[ERROR]: Invalid argument for add slayer command, number expected: [a]dd <x> <y>");
			}
			
		}
			
		}	
	
		
		return parseNoParamsCommand(commandWords);
	}

	

}