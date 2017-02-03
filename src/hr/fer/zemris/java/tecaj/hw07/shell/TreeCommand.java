package hr.fer.zemris.java.tecaj.hw07.shell;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * Prints a tree of the given directory.
 * @author labramusic
 *
 */
public class TreeCommand extends AbstractCommand {

	/**
	 * Initializes a new tree command.
	 */
	public TreeCommand() {
		super("tree", "Prints a tree of the given directory.");
	}

	@Override
	public ShellStatus executeCommand(Environment env, String arguments) throws IOException {
		if (arguments.length() < 1) {
			env.writeln("Unknown command!");
		}
		String pathname = PathnameUtility.parsePathname(arguments);

		File file = new File(pathname);
		if (file.isDirectory()) {
			Files.walkFileTree(file.toPath(), new PrintFileVisitor());

		} else {
			env.writeln("Path must lead to a directory.");
		}

		return ShellStatus.CONTINUE;
	}

	/**
	 * A file visitor which prints each file and folder using the environment.
	 * @author labramusic
	 *
	 */
	private static class PrintFileVisitor extends SimpleFileVisitor<Path> {
		/**
		 * The level of recursion.
		 */
		private int level;

		@Override
		public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
			print(dir);
			++level;
			return FileVisitResult.CONTINUE;
		}

		@Override
		public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
			--level;
			return FileVisitResult.CONTINUE;
		}

		@Override
		public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
			print(file);
			return FileVisitResult.CONTINUE;
		}

		/**
		 * Prints the file name using the environment.
		 * @param file file to be written
		 */
		private void print(Path file) {
			if (level == 0) {
				System.out.println(file.toAbsolutePath().normalize());
			} else {
				System.out.printf("%"+(2*level)+"s%s%n", "", file.getFileName());
			}
		}
	}


}
