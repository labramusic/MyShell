package hr.fer.zemris.java.tecaj.hw07.shell;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * An abstract class modeling a shell command containing a command name and
 * description.
 * 
 * @author labramusic
 *
 */
public abstract class AbstractCommand implements ShellCommand {

	/**
	 * The name of the command.
	 */
	private final String commandName;

	/**
	 * The description of the command.
	 */
	private List<String> commandDescription;

	/**
	 * Constructor which sets command name and description.
	 * 
	 * @param name
	 *            command name
	 * @param description
	 *            command description
	 */
	public AbstractCommand(String name, String... description) {
		commandName = name;
		commandDescription = new ArrayList<String>();
		for (String s : description) {
			commandDescription.add(s);
		}
		commandDescription = Collections.unmodifiableList(commandDescription);
	}

	@Override
	public String getCommandName() {
		return commandName;
	}

	@Override
	public List<String> getCommandDescription() {
		return commandDescription;
	}

}
