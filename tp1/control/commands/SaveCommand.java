package org.ucm.tp1.control.commands;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.ucm.tp1.control.exception.CommandExecuteException;
import org.ucm.tp1.control.exception.CommandParseException;
import org.ucm.tp1.logic.Game;
import org.ucm.tp1.logic.gameobject.Vampire;

public class SaveCommand extends Command {

	protected static final String name = "save";
	protected static final String shortcut = "s" ;
	private static final String details = "[S]ave"; 
	private static final String help = "save game";
	private String filename;

	public SaveCommand(){
		super(name, shortcut, details, help);
		
		
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException {

		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(filename+".dat")) ;
			writer.write("Buffy the Vampire Slayer v3.0\n\n");
			writer.write(game.serialize());
			writer.close();
			System.out.println("Game successfully saved to file");
		} catch (IOException e) {
			throw new CommandExecuteException("Error to write");

		}
		return false;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		if(matchCommandName(commandWords[0]) ) {

			if(commandWords.length == 1) {
				throw new CommandParseException("[ERROR]: Command "+ name+" :"+incorrectNumberOfArgsMsg);
			}

			if(commandWords.length == 2) {	
			
					this.filename = commandWords[1];
					return this;
				
				}
			}
		
		return parseNoParamsCommand(commandWords);
		}


		
	
}


