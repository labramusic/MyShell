package hr.fer.zemris.java.tecaj.hw07.shell;

import java.io.IOException;

/**
 * Terminates the shell.
 * @author labramusic
 *
 */
public class ExitCommand extends AbstractCommand {

	/**
	 * Initializes a new exit command.
	 */
	public ExitCommand() {
		super("exit", "Terminates the shell.");
	}

	@Override
	public ShellStatus executeCommand(Environment env, String arguments) throws IOException {
		return ShellStatus.TERMINATE;
	}

}
