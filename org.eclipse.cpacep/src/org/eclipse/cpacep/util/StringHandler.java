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

}
