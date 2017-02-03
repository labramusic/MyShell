package hr.fer.zemris.java.tecaj.hw07.shell;

import java.io.IOException;

/**
 * Prints the symbol for the requested function. Valid functions are PROMPT, MORELINES and MULTILINE.
 * If a new symbol is given as the second argument, it's set as the new symbol for the given function.
 * @author labramusic
 *
 */
public class SymbolCommand extends AbstractCommand {

	/**
	 * Initializes a new symbol command.
	 */
	public SymbolCommand() {
		super("symbol"
				, "Prints the symbol for the requested function. Valid functions are PROMPT, MORELINES and MULTILINE."
				, "If a new symbol is given as the second argument, it's set as the new symbol for the given function.");
	}

	@Override
	public ShellStatus executeCommand(Environment env, String arguments) throws IOException {
		String[] args = arguments.split(" ");
		String arg = "";
		if (args.length == 2) {
			arg = args[1];
		} else if (args.length < 1 || args.length > 2) {
			env.writeln("Invalid number of arguments.");
			return ShellStatus.CONTINUE;
		}
		if (arg.length() > 1) {
			env.writeln("Symbol must be one character long.");
			return ShellStatus.CONTINUE;

		} 

		switch (args[0]) {
		case "PROMPT":
			setPrompt(env, arg);
			break;

		case "MORELINES":
			setMorelines(env, arg);
			break;

		case "MULTILINE":
			setMultiline(env, arg);
			break;

		default:
			env.writeln("Unknown command!");
		}
		return ShellStatus.CONTINUE;
	}

	/**
	 * Sets the prompt symbol if possible.
	 * @param env the environment
	 * @param arg new symbol or empty
	 * @throws IOException thrown in case of I/O error
	 */
	private static void setPrompt(Environment env, String arg) throws IOException {
		Character prompt = env.getPromptSymbol();
		if (arg.length() == 0) {
			env.writeln("Symbol for PROMPT is '"+prompt+"'");
		} else {
			Character symbol = arg.charAt(0);
			if (!prompt.equals(symbol)) {
				env.writeln("Symbol for PROMPT changed from '"+prompt+"' to '"+symbol+"'");
				env.setPromptSymbol(symbol);
			} else {
				env.writeln("Symbol for PROMPT is already set to '"+symbol+"'");
			}
		}
	}

	/**
	 * Sets the morelines symbol if possible.
	 * @param env the environment
	 * @param arg new symbol or empty
	 * @throws IOException thrown in case of I/O error
	 */
	private static void setMorelines(Environment env, String arg) throws IOException {
		Character morelines = env.getMorelinesSymbol();
		if (arg.length() == 0) {
			env.writeln("Symbol for MORELINES is '"+morelines+"'");
		} else {
			Character symbol = arg.charAt(0);
			if (!morelines.equals(symbol)) {
				env.writeln("Symbol for MORELINES changed from '"+morelines+"' to '"+symbol+"'");
				env.setMorelinesSymbol(symbol);
			} else {
				env.writeln("Symbol for MORELINES is already set to '"+symbol+"'");
			}
		}
	}

	/**
	 * Sets the multiline symbol if possible.
	 * @param env the environment
	 * @param arg new symbol or empty
	 * @throws IOException thrown in case of I/O error
	 */ static void setMultiline(Environment env, String arg) throws IOException {
		 Character multiline = env.getMultilineSymbol();
		 if (arg.length() == 0) {
			 env.writeln("Symbol for MULTILINE is '"+multiline+"'");
		 } else {
			 Character symbol = arg.charAt(0);
			 if (!multiline.equals(symbol)) {
				 env.writeln("Symbol for MULTILINE changed from '"+multiline+"' to '"+symbol+"'");
				 env.setMultilineSymbol(symbol);
			 } else {
				 env.writeln("Symbol for MULTILINE is already set to '"+symbol+"'");
			 }
		 }
	 }

}
