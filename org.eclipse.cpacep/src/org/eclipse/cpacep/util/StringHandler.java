package org.eclipse.cpacep.util;

import java.io.File;

public class StringHandler {

	public static String getHomePath(String path, int gap) {
		String homeSplit[] = path.split(File.separator);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < homeSplit.length - gap; i++) {
			if (!homeSplit[i].equals("")) {
				sb.append(File.separator + homeSplit[i]);
			}
		}
		return sb.toString();
	}

}
