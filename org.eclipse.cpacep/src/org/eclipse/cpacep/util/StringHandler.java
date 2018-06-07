package org.eclipse.cpacep.util;

public class StringHandler {

	public static String getHomePath(String path, int gap) {
		String homeSplit[] = path.split("/");
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < homeSplit.length - gap; i++) {
			if (!homeSplit[i].equals("")) {
				sb.append("/" + homeSplit[i]);
			}
		}
		return sb.toString();
	}

	public static String toCamelCase(final String init) {
		if (init == null)
			return null;

		final StringBuilder ret = new StringBuilder(init.length());

		for (final String word : init.split(" ")) {
			if (!word.isEmpty()) {
				ret.append(word.substring(0, 1).toUpperCase());
				ret.append(word.substring(1).toLowerCase());
			}
			if (!(ret.length() == init.length()))
				ret.append(" ");
		}

		return ret.toString();
	}

}
