package org.ucm.tp1.control.commands;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;

import org.ucm.tp1.control.exception.CommandExecuteException;
import org.ucm.tp1.control.exception.CommandParseException;
import org.ucm.tp1.logic.Game;
import org.ucm.tp1.logic.gameobject.Vampire;

public class SerializeCommand extends Command implements Serializable{
	
	  protected static final String name = "serialize";
	  protected static final String shortcut = "z" ;
	  private static final String details = "serialize | z"; 
	  private static final String help = "serialize";

	public SerializeCommand() {
		super(name, shortcut, details, help);
	
	}
	

	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		
		System.out.println(game.serialize());

		return false;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		if(commandWords[0].equalsIgnoreCase("z") ) {
			commandWords[0] = shortcut;
		}
		return parseNoParamsCommand(commandWords);
	}

}
