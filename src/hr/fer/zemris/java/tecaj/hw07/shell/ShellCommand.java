package hr.fer.zemris.java.tecaj.hw07.shell;

import java.io.IOException;
import java.util.List;

/**
 * Models a shell command.
 * 
 * @author labramusic
 *
 */
public interface ShellCommand {

	/**
	 * Executes the command.
	 * 
	 * @param env
	 *            environment in which the shell is executed
	 * @param arguments
	 *            arguments of the command entered
	 * @throws IOException
	 *             thrown in case of I/O error
	 * @return shell status after executing command
	 */
	ShellStatus executeCommand(Environment env, String arguments) throws IOException;

	/**
	 * Returns command name.
	 * 
	 * @return command name
	 */
	String getCommandName();

	/**
	 * Returns command description.
	 * 
	 * @return command description
	 */
	List<String> getCommandDescription();

}
