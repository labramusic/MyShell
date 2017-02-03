package hr.fer.zemris.java.tecaj.hw07.shell;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**Produces hex output of given file.
 * 
 * @author labramusic
 *
 */
public class HexdumpCommand extends AbstractCommand {

	/**
	 * Initializes a new hexdump command.
	 */
	public HexdumpCommand() {
		super("hexdump", "Produces hex output of given file.");
	}

	@Override
	public ShellStatus executeCommand(Environment env, String arguments) throws IOException {
		if (arguments.length() < 1) {
			env.writeln("Unknown command!");
		}
		String filename = PathnameUtility.parsePathname(arguments);

		File file = new File(filename);
		if (!file.exists() || file.isDirectory()) {
			env.writeln("File doesn't exist.");

		}  else {
			hexDump(env, file);
		}

		return ShellStatus.CONTINUE;
	}

	/**
	 * Prints the hex dump on the environment.
	 * @param env the environment
	 * @param file file to generate hex dump from
	 * @throws IOException thrown in case of I/O error
	 */
	private void hexDump(Environment env, File file) throws IOException {
		// citaj UTF-8
		try (InputStream is = new BufferedInputStream(new FileInputStream(file))) {
			int i = 0;
			while (is.available() > 0) {
				StringBuilder sb1 = new StringBuilder();
				// u sb2 text, stavi na UTF-8
				StringBuilder sb2 = new StringBuilder();
				//env.write(new String(String.format("%08X: ", i*16).getBytes(), StandardCharsets.UTF_8));
				env.write(String.format("%08X: ", i*16));
				for (int j = 0; j < 8; ++j) {
					if (is.available() > 0) {
						int value = is.read();
						sb1.append(String.format("%02X ", value));
						if (value >= 32 && value <= 127) {
							sb2.append(new String(new byte[] {(byte)value}, StandardCharsets.UTF_8));
						} else {
							sb2.append(".");
						}
					} else {
						for (; j < 8; ++j) {
							sb1.append("   ");
						}
					}
				}
				sb1.append("|");

				for (int j = 0; j < 8; ++j) {
					if (is.available() > 0) {
						int value = is.read();
						sb1.append(String.format("%02X ", value));
						if (value >= 32 && value <= 127) {
							sb2.append(new String(new byte[] {(byte)value}, StandardCharsets.UTF_8));
						} else {
							sb2.append(".");
						}
					} else {
						for (; j < 8; ++j) {
							sb1.append("   ");
						}
					}
				}
				sb1.append("|");
				env.write(sb1.toString());
				env.writeln(sb2.toString());
				++i;
			}
		}
	}

}
