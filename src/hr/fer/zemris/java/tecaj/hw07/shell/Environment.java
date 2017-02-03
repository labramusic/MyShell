package hr.fer.zemris.java.tecaj.hw07.shell;

import java.io.IOException;

/**
 * Models the shell environment.
 * 
 * @author labramusic
 *
 */
public interface Environment {

	/**
	 * Reads a line from the keyboard.
	 *
	 * @return entered command
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	String readLine() throws IOException;

	/**
	 * Writes a line on the screen.
	 *
	 * @param line
	 *            line to be written on the screen
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	void write(String line) throws IOException;

	/**
	 * Writes a line on the screen, automatically adding a new line at the end.
	 *
	 * @param line
	 *            line to be written on the screen
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	void writeln(String line) throws IOException;

	/**
	 * Creates and returns an object iterable by implemented shell commands.
	 * 
	 * @return object iterable by implemented shell commands
	 */
	Iterable<ShellCommand> commands();

	/**
	 * Gets the multiline symbol.
	 *
	 * @return the multiline symbol
	 */
	Character getMultilineSymbol();

	/**
	 * Sets the multiline symbol.
	 *
	 * @param symbol
	 *            the new multiline symbol
	 */
	void setMultilineSymbol(Character symbol);

	/**
	 * Gets the prompt symbol.
	 *
	 * @return the prompt symbol
	 */
	Character getPromptSymbol();

	/**
	 * Sets the prompt symbol.
	 *
	 * @param symbol
	 *            the new prompt symbol
	 */
	void setPromptSymbol(Character symbol);

	/**
	 * Gets the morelines symbol.
	 *
	 * @return the morelines symbol
	 */
	Character getMorelinesSymbol();

	/**
	 * Sets the morelines symbol.
	 *
	 * @param symbol
	 *            the new morelines symbol
	 */
	void setMorelinesSymbol(Character symbol);

	/**
	 * Returns the command associated with the given name.
	 * 
	 * @param commandName
	 *            command name
	 * @return command
	 */
	ShellCommand getCommand(String commandName);

}
