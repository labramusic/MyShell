package hr.fer.zemris.java.tecaj.hw07.shell;

import java.io.IOException;

/**
 * Prints all commands if no arguments are received. If command name is
 * received as an argument, its desctiption is printed.
 * @author labramusic
 *
 */
public class HelpCommand extends AbstractCommand {

	/**
	 * Initializes a new Help command.
	 */
	public HelpCommand() {
		super("help", "Prints all commands if no arguments are received."
				,"If command name is received as an argument, its decription is printed.");
	}

	@Override
	public ShellStatus executeCommand(Environment env, String arguments) throws IOException {
		if (arguments.length() == 0) {
			env.writeln("Available commands:");
			for (ShellCommand cmd : env.commands()) {
				env.writeln(cmd.getCommandName());
			}

		} else {
			ShellCommand cmd = env.getCommand(arguments.toLowerCase());
			if (cmd == null) {
				env.writeln("Unknown command!");
			} else {
				env.write(cmd.getCommandName()+": ");
				for (String line : cmd.getCommandDescription()) {
					env.writeln(line);
				}
			} 
		}

		return ShellStatus.CONTINUE;
	}

}
