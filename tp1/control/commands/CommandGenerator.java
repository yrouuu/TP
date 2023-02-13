package org.ucm.tp1.control.commands;

import org.ucm.tp1.control.exception.CommandParseException;

public class CommandGenerator {

	private static Command[] availableCommands = {
			
			new AddCommand(),
			new HelpCommand(),
			new ResetCommand(),
			new ExitCommand(),
			new UpdateCommand(),
			new GarlicPushCommand(),
			new LightFlashCommand(),
			new AddBloodBankCommand(),
			new SuperCoinsCommand(),
			new AddVampireCommand(),
			new SerializeCommand(),
			new SaveCommand()
	};
	
	public static Command parseCommand(String[] commandWords) throws CommandParseException {
		
	
		
		for(int i=0 ; i < availableCommands.length ; i++) {
			
			if(availableCommands[i].parse(commandWords) != null) {
				
				return availableCommands[i].parse(commandWords);
				
			};
						
						
		}
			
		throw new CommandParseException("[Error] :Unknown command");
		
	}
	
	public static String commandHelp() {
		
		StringBuilder temp = new StringBuilder("");
		temp.append("Available commands:\n");
		
		for (int i=0 ; i < availableCommands.length ; i++) {
			
			temp.append(availableCommands[i].helpText()+"\n");				
		
	}
		return temp.toString();
	}
	
}
