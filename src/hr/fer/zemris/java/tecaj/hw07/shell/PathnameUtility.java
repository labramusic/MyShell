package hr.fer.zemris.java.tecaj.hw07.shell;

/**
 * Utility class for parsing pathname from given argument string.
 * @author labramusic
 *
 */
public class PathnameUtility {

	/**
	 * Separates arguments from argument into strings by spaces or quotes.
	 * @param arguments single string containing arguments
	 * @return separated arguments
	 */
	public static String[] getArgs(String arguments) {
		String[] args;

		if (arguments.contains("\"")) {
			arguments = parsePathname(arguments);
			args = arguments.split("\" +\"|\" +| +\"", 2);
		} else {
			args = arguments.split(" ");
		}

		return args;
	}

	/**
	 * Returns the string containing the path after parsing the given string argument.
	 * Leading and trailing quotation marks are removed and supported characters (" and \)
	 * are escaped.
	 * @param pathname string argument
	 * @return parsed pathname
	 */
	public static String parsePathname(String pathname) {
		int length = pathname.length();
		// remove quotations at start and end of string
		if (pathname.charAt(length-1) == '"') {
			pathname = new StringBuilder(pathname).replace(length-1, length, "").toString();
		}
		if (pathname.charAt(0) == '"') {
			pathname = pathname.replaceFirst("\"", "");
		}
		pathname = pathname.trim();

		// escape characters
		pathname = pathname.replace("\\\"", "\"");
		pathname = pathname.replace("\\\\", "\\");
		return pathname;
	}

}
