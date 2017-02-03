package hr.fer.zemris.java.tecaj.hw07.shell;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;


/**
 * Represents a shell (similar to Linux's Bash) which communicates with the user over the keyboard
 * and the display. Once started, the shell reads line by line from the keyboard, interpreting
 * each line as a command. A list of available commands is given if user enters the "help" command.
 * Detailed explanation of each command is given if user specifies the name of the command.
 * @author labramusic
 *
 */
public class MyShell {

	/**
	 * Table which contains a sample of each command.
	 */
	private final static Map<String, ShellCommand> commands;

	static {
		commands = new TreeMap<>();
		ShellCommand[] cc = {
				new HelpCommand(),
				new ExitCommand(),
				new SymbolCommand(),
				new CharsetsCommand(),
				new CatCommand(),
				new LsCommand(),
				new TreeCommand(),
				new CopyCommand(),
				new MkdirCommand(),
				new HexdumpCommand()
		};
		for(ShellCommand c : cc) {
			commands.put(c.getCommandName(), c);
		}
	}

	/**
	 * Shell environment implementation.
	 * @author labramusic
	 *
	 */
	protected static class EnvironmentImpl implements Environment {

		/**
		 * Buffering character input stream for reading lines from the keyboard.
		 */
		private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		/**
		 * Buffering character output stream for writing lines on the screen.
		 */
		private BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

		/**
		 * The symbol used for showing multiple lines input.
		 */
		private Character multilineSymbol;

		/**
		 * The symbol used for prompts.
		 */
		private Character promptSymbol;

		/**
		 * The symbol used for allowing multiple lines input.
		 */
		private Character morelinesSymbol;

		/**
		 * Constructor which sets the initial current path to the working directory.
		 */
		public EnvironmentImpl() {
			multilineSymbol = '|';
			promptSymbol = '>';
			morelinesSymbol = '\\';
		}

		@Override
		public String readLine() throws IOException {
			return reader.readLine();
		}
		// TODO write as UTF-8
		@Override
		public void write(String line) throws IOException {
			writer.write(line);
			writer.flush();
		}

		@Override
		public void writeln(String line) throws IOException {
			writer.write(line);
			writer.newLine();
			writer.flush();
		}

		@Override
		public Iterable<ShellCommand> commands() {
			return Collections.unmodifiableCollection(commands.values());
		}

		@Override
		public ShellCommand getCommand(String commandName) {
			return commands.get(commandName);
		}

		@Override
		public Character getMultilineSymbol() {
			return multilineSymbol;
		}

		@Override
		public void setMultilineSymbol(Character symbol) {
			multilineSymbol = symbol;

		}

		@Override
		public Character getPromptSymbol() {
			return promptSymbol;
		}

		@Override
		public void setPromptSymbol(Character symbol) {
			promptSymbol = symbol;

		}

		@Override
		public Character getMorelinesSymbol() {
			return morelinesSymbol;
		}

		@Override
		public void setMorelinesSymbol(Character symbol) {
			morelinesSymbol = symbol;
		}

	}

	/**
	 * Static reference to the environment implementation.
	 */
	public static Environment environment = new EnvironmentImpl();

	/**
	 * Main method used for input and execution of commands.
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		try {
			environment.writeln("Welcome to MyShell v 1.0");

			while(true) {
				environment.write(environment.getPromptSymbol() + " ");

				String line = environment.readLine().trim();
				String commandName = line.split(" ")[0].toLowerCase().trim();
				String argument = getArgument(line, commandName);

				ShellCommand shellCommand = commands.get(commandName);

				if(shellCommand==null) {
					environment.writeln("Unknown command!");
					continue;
				}

				ShellStatus status = shellCommand.executeCommand(environment, argument);
				if (status==ShellStatus.TERMINATE) {
					break;
				}
			}

			environment.writeln("Thank you for using this shell. Goodbye!");

		} catch (IOException e) {
			System.err.println("IOException occurred: "+e.getMessage());
			System.exit(1);
		}
	}

	/**
	 * Returns the argument as a single string.
	 * 
	 * @param line
	 *            the first read line
	 * @param commandName
	 *            entered command name
	 * @return argument string
	 * @throws IOException
	 *             thrown in case of I/O error
	 */
	private static String getArgument(String line, String commandName) throws IOException {
		String argument = "";
		String newLine = line.substring(commandName.length()).trim();
		if (newLine.length() != 0) {
			while (true) {
				argument += newLine;
				if (argument.charAt(argument.length()-1) == environment.getMorelinesSymbol()) {
					argument = argument.substring(0, argument.length()-1).trim();
				} else {
					break;
				}
				environment.write(environment.getMultilineSymbol() + " ");
				newLine = " " + environment.readLine().trim();
			}
		}
		return argument.trim();
	}

}
