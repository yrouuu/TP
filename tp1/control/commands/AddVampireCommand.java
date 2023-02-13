package org.ucm.tp1.control.commands;

import org.ucm.tp1.control.exception.CommandExecuteException;
import org.ucm.tp1.control.exception.CommandParseException;
import org.ucm.tp1.control.exception.DraculaIsAliveException;
import org.ucm.tp1.control.exception.NoMoreVampiresException;
import org.ucm.tp1.control.exception.UnvalidPositionException;
import org.ucm.tp1.logic.Game;
import org.ucm.tp1.logic.gameobject.Dracula;
import org.ucm.tp1.logic.gameobject.Vampire;

public class AddVampireCommand extends Command{

	protected static final String name = "vampire";
	protected static final String shortcut = "v" ;
	private static final String details = "[v]ampire [<type>] <x> <y>"; 
	private static final String help = "Type = {\"\"|\"D\"|\"E\"}: add a vampire in position x, y";
	private int x;
	private int y;
	private String type;

	public AddVampireCommand() {
		super(name, shortcut, details, help);

	}

	public void hayVampire() throws NoMoreVampiresException {
		if(Vampire.getVampirequda() <= 0) {
			throw new NoMoreVampiresException("[ERROR]: No quedan vampiros");
		}
	}

	public void posicionValida(Game game) throws UnvalidPositionException {

		if((this.x < 0 || this.x >= game.dim_x()) || (this.y < 0 || this.y >= game.dim_y()) ) {
			throw new UnvalidPositionException("[ERROR]: Invalid position");
		}
		 game.posicionOcupado(x, y);
	}


	@Override
	public boolean execute(Game game) throws CommandExecuteException {

		try {
			hayVampire();
			posicionValida(game);
			
			if(this.type.equals("")) {

				game.aniadirVampire(this.x,this.y);
				Vampire.setVampireEntablero(Vampire.getVampireEntablero()+1);
				Vampire.setVampirequda(Vampire.getVampirequda()-1);
				return true;


			} else if(this.type.equals("d")) {

				game.draculaAlive();
				game.aniadirDracula(this.x, this.y);
				Vampire.setVampireEntablero(Vampire.getVampireEntablero()+1);
				Vampire.setVampirequda(Vampire.getVampirequda()-1);
				Dracula.setDraculaAlive(true);
				return true;



			} else if(this.type.equals("e")) {

				game.aniadirExplosiveVampire(this.x, this.y);
				Vampire.setVampireEntablero(Vampire.getVampireEntablero()+1);
				Vampire.setVampirequda(Vampire.getVampirequda()-1);
				return true;


			}else {System.out.println("[ERROR]: Invalid type");}

		}catch(NoMoreVampiresException ex) {

			System.out.println(ex.getMessage());
			throw new CommandExecuteException("[ERROR]: Failed to add this vampire");

		}catch(UnvalidPositionException ex) {
			
			System.out.println(ex.getMessage());
			throw new CommandExecuteException("[ERROR]: Failed to add this vampire");
			
		}catch(DraculaIsAliveException ex) {
			
			System.out.println(ex.getMessage());
			throw new CommandExecuteException("[ERROR]: Failed to add this vampire");
		}
		return false;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {

		if(matchCommandName(commandWords[0]) ) {

			if(commandWords.length == 1) {
				throw new CommandParseException("[ERROR]: Command "+ name+" :"+incorrectNumberOfArgsMsg);
			}

			if(commandWords.length == 3) {

				try {
					type="";
					this.x = Integer.parseInt(commandWords[1]);
					this.y = Integer.parseInt(commandWords[2]);
					return this;
				}catch (NumberFormatException nfe) {


					throw new CommandParseException("[ERROR]: Invalid argument for add vampire command, number expected: [v]ampire [<type>] <x> <y>");
				}
			}

			if(commandWords.length == 4) {

				try {
					this.type = commandWords[1];
					this.x = Integer.parseInt(commandWords[2]);
					this.y = Integer.parseInt(commandWords[3]);
					return this;

				}catch (NumberFormatException nfe) {


					throw new CommandParseException("[ERROR]: Invalid argument for add vampire command, number expected: [v]ampire [<type>] <x> <y>");
				}

			}



		}		

		return parseNoParamsCommand(commandWords);
	}

}
