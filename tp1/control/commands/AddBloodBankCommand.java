package org.ucm.tp1.control.commands;

import org.ucm.tp1.control.exception.CommandExecuteException;
import org.ucm.tp1.control.exception.CommandParseException;
import org.ucm.tp1.control.exception.MayorDiezException;
import org.ucm.tp1.control.exception.NotEnoughCoinsException;
import org.ucm.tp1.control.exception.UnvalidPositionException;
import org.ucm.tp1.logic.Game;

public class AddBloodBankCommand extends Command {

	  protected static final String name = "bank";
	  protected static final String shortcut = "b" ;
	  private static final String details = "[b]ank <x><y><z>"; 
	  private static final String help = "add a blood bank with cost z in position x, y";
	  private int x;
	  private int y;
	  private int z;
	  
	public AddBloodBankCommand() {
		super(name, shortcut, details, help);
		
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException {
	
			try {
				posicionValida(game);
				mayorDiez();
				game.haymonedas(z); 
				game.quitarmonedas(z);
				game.aniadirBloodBank(x,y,z); 	
				game.accion();
				return true;
				
				}catch(UnvalidPositionException ex) {
					System.out.println(ex.getMessage());
					throw new CommandExecuteException("[ERROR]: Failed to add this bloodbank");
				}catch(MayorDiezException ex) {
					System.out.println(ex.getMessage());
					throw new CommandExecuteException("[ERROR]: Failed to add this bloodbank");
				}catch(NotEnoughCoinsException ex){
					System.out.println(ex.getMessage());
					throw new CommandExecuteException("[ERROR]: Failed to add this bloodbank");
			
			}
				
		}


	public void posicionValida(Game game) throws UnvalidPositionException {

		if((this.x < 0 || this.x >= game.dim_x()-1) || (this.y < 0 || this.y >= game.dim_y()) ) {
			throw new UnvalidPositionException("[ERROR]: Invalid position");
		}
		game.posicionOcupado(x, y);
	}
	
	public void mayorDiez() throws MayorDiezException {
		if(z<10) {
			throw new MayorDiezException("[ERROR]: El coste tiene que ser mayor o igual que 10");
		}
	}
	@Override
	public Command parse(String[] commandWords) throws CommandParseException {

		if(matchCommandName(commandWords[0]) ) {
			
			if(commandWords.length == 1) {
				 throw new CommandParseException("[ERROR]: Command "+ name+" :"+incorrectNumberOfArgsMsg);
			}
			
			if(commandWords.length == 4) {	
				try {
					this.x = Integer.parseInt(commandWords[1]);
					this.y = Integer.parseInt(commandWords[2]);
					this.z = Integer.parseInt(commandWords[3]);
					return this;
				}catch (NumberFormatException nfe) {


					throw new CommandParseException("[ERROR]: Invalid argument for add BloodBank command, number expected: [b]ank <x><y><z>");
				}
			}
		}


		return parseNoParamsCommand(commandWords);
	}



}
