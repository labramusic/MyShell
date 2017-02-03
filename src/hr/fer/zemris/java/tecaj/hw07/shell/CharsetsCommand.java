package hr.fer.zemris.java.tecaj.hw07.shell;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;

/**
 * Lists names of supported charsets for the user's Java platform.
 * @author labramusic
 *
 */
public class CharsetsCommand extends AbstractCommand {

	/**
	 * Initializes a new charsets command.
	 */
	public CharsetsCommand() {
		super("charsets", "Lists names of supported charsets for the user's Java platform.");
	}

	@Override
	public ShellStatus executeCommand(Environment env, String arguments) throws IOException {
		Map<String, Charset> charsets = Charset.availableCharsets();
		if (arguments.length() > 0) {
			env.writeln("No arguments expected.");
			return ShellStatus.CONTINUE;
		}
		env.writeln("Available charsets on this Java platform:");
		for (String charset : charsets.keySet()) {
			env.writeln(charset);
		}

		return ShellStatus.CONTINUE;
	}

}
