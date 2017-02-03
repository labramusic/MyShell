package hr.fer.zemris.java.tecaj.hw07.shell;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Writes the formatted listing of the given directory. The listing shows if the file is a directory,
 * its read-write permissions, size in bytes, creation date and time, and finally the file name.
 * @author labramusic
 *
 */
public class LsCommand extends AbstractCommand {

	/**
	 * Initializes a new ls command.
	 */
	public LsCommand() {
		super("ls", "Writes the formatted listing of the given directory."
				, "The listing shows if the file is a directory, its read-write permissions,"
				, "size in bytes, creation date and time, and finally the file name.");
	}

	@Override
	public ShellStatus executeCommand(Environment env, String arguments) throws IOException {
		if (arguments.length() < 1) {
			env.writeln("Unknown command!");
		}
		String pathname = PathnameUtility.parsePathname(arguments);

		File file = new File(pathname);
		if (file.isDirectory()) {
			File[] children = file.listFiles();
			if (children != null) {
				for (File child : children) {
					list(env, child);
				}
			}

		} else {
			env.writeln("Path must lead to a directory.");
		}


		return ShellStatus.CONTINUE;
	}

	/**
	 * Lists the data for the given file.
	 * @param env the environment
	 * @param file file to list info of
	 * @throws IOException thrown in case of I/O error
	 */
	private static void list(Environment env, File file) throws IOException {
		Path path = file.toPath();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		BasicFileAttributeView faView = Files.getFileAttributeView(
				path, BasicFileAttributeView.class, LinkOption.NOFOLLOW_LINKS);
		BasicFileAttributes attributes = faView.readAttributes();

		String dir = (Files.isDirectory(path) ? "d" : "-");
		String read = (Files.isReadable(path) ? "r" : "-");
		String write = (Files.isWritable(path) ? "w" : "-");
		String exec = (Files.isExecutable(path) ? "x" : "-");
		String size = String.format("%10s", String.valueOf(attributes.size()));
		FileTime fileTime = attributes.creationTime();
		String formattedDateTime = sdf.format(new Date(fileTime.toMillis()));
		String name = path.getFileName().toString();

		env.writeln(dir+read+write+exec+" "+size+" "+formattedDateTime+" "+name);
	}

}
