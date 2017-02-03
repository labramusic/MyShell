package hr.fer.zemris.java.tecaj.hw07.shell;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

/**
 * The first argument is path to some file. The second argument is optional and represents the charset name 
 * that is used to interpret characters. If not provided, a default platform charset is used. 
 * This command opens the given file and writes its content to console.
 * @author labramusic
 *
 */
public class CatCommand extends AbstractCommand {

	/**
	 * Initializes a new cat command.
	 */
	public CatCommand() {
		super("cat"
				, "The first argument is path to some file. The second argument is optional and represents the charset name"
				, "that is used to interpret characters. If not provided, a default platform charset is used."
				, "This command opens the given file and writes its content to console.");
	}

	@Override
	public ShellStatus executeCommand(Environment env, String arguments) throws IOException {
		String[] args = PathnameUtility.getArgs(arguments);
		int numArgs = args.length;
		String path = args[0];
		Charset charset;

		if (numArgs == 1) {
			charset = Charset.defaultCharset();
		} else if (numArgs == 2) {
			try {
				charset = Charset.forName(args[1]);
			} catch(IllegalArgumentException e) {
				env.writeln("Could not find charset "+e.getMessage()+".");
				return ShellStatus.CONTINUE;
			}

		} else {
			env.writeln("Invalid number of arguments.");
			return ShellStatus.CONTINUE;
		}

		File file = new File(path);
		if (file.isFile()) {
			writeFile(env, file, charset);
		} else {
			env.writeln("File doesn't exist.");
		}

		return ShellStatus.CONTINUE;
	}

	/**
	 * Writes the file on the screen using the given environment.
	 * @param env the environment
	 * @param file file to be written
	 * @param charset charset used while reading
	 * @throws IOException thrown in case of I/O error
	 */
	private static void writeFile(Environment env, File file, Charset charset) throws IOException {
		try (Reader br = new BufferedReader
				(new InputStreamReader
						(new BufferedInputStream(new FileInputStream(file)), charset))) {

			char[] buffer = new char[4096];
			int r;
			while ((r = br.read(buffer)) >= 1) {
				env.writeln(new String(buffer, 0 , r));
			}
		} 
	}

}
