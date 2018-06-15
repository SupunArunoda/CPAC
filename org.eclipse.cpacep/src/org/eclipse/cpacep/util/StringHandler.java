package org.eclipse.cpacep.util;

import java.nio.file.Path;
import java.nio.file.Paths;

public class StringHandler {

	public static Path getHomePath(String pExecutable) {
		return Paths.get(pExecutable).getParent().getParent();
	}
}
