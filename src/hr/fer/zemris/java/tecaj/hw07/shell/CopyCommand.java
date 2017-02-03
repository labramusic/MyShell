package hr.fer.zemris.java.tecaj.hw07.shell;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Expects source file name and destination file name as arguments. If destination file exits, the user
 * is asked if it is allowed to overwrite it. If the second argument is a directory, the original file
 * is copied into it under the same name.
 * @author labramusic
 *
 */
public class CopyCommand extends AbstractCommand {

	/**
	 * Initializes a new copy command.
	 */
	public CopyCommand() {
		super("copy"
				, "Expects source file name and destination file name as arguments. If destination file exits, the user"
				, "is asked if it is allowed to overwrite it. If the second argument is a directory, the original file"
				, "is copied into it under the same name.");
	}

	@Override
	public ShellStatus executeCommand(Environment env, String arguments) throws IOException {
		String[] args = PathnameUtility.getArgs(arguments);
		if (args.length != 2) {
			env.writeln("Invalid number of arguments.");
			return ShellStatus.CONTINUE;
		}

		File source = new File(args[0]);
		File dest = new File(args[1]);

		if (source.isFile()) {

			if (dest.isDirectory()) {
				dest = Paths.get(dest.toString(), source.getName().toString()).toFile();
			} else if (dest.isFile()) {
				// file exists
				env.writeln("File already exits. Overwrite? (Y/N)");
				String answer = env.readLine();
				while (true) {
					if (answer.equalsIgnoreCase("Y")) {
						break;
					} else if (answer.equalsIgnoreCase("N")) {
						return ShellStatus.CONTINUE;
					}
					answer = env.readLine();
				}
			} 

			copyFile(source, dest);
			env.writeln("File succesfully copied.");

		} else {
			env.writeln("Source file must be a regular file.");
		}

		return ShellStatus.CONTINUE;
	}

	/**
	 * Copies the file from the source path to the destination path.
	 * If parent folder doesn't exist, it is created.
	 * @param src source path
	 * @param dest destination path
	 * @throws IOException thrown in case of I/O error
	 */
	private static void copyFile(File src, File dest) throws IOException {
		Path destPath = dest.toPath().toAbsolutePath().normalize();
		Path dir = destPath.getParent();
		System.out.println(dir);
		if (!Files.isDirectory(dir)) {
			Files.createDirectories(dir);
		}

		try (InputStream is = new BufferedInputStream(new FileInputStream(src));
				OutputStream os = new BufferedOutputStream(new FileOutputStream(dest))) {

			int r;
			while ((r = is.read()) != -1) {
				os.write(r);
			}

		}
	}

}
