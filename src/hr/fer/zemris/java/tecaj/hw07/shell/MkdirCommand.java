package hr.fer.zemris.java.tecaj.hw07.shell;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * Creates a new directory at the given path.
 * @author labramusic
 *
 */
public class MkdirCommand extends AbstractCommand {

	/**
	 * Initializes a new mkdir command.
	 */
	public MkdirCommand() {
		super("mkdir", "Creates a new directory at the given path.");
	}

	@Override
	public ShellStatus executeCommand(Environment env, String arguments) throws IOException {
		if (arguments.length() == 0) {
			env.writeln("Folder path expected.");
			return ShellStatus.CONTINUE;
		}

		String path = PathnameUtility.parsePathname(arguments);
		if (path.contains("\"")) {
			env.writeln("Folder name contains illegal characters.");
			return ShellStatus.CONTINUE;
		}

		File file = new File(path);
		if (!file.exists()) {
			Files.createDirectories(file.toPath());
		}

		return ShellStatus.CONTINUE;
	}

}
