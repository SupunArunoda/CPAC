package org.eclipse.cpacep.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.FileAttribute;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileHandler {

	public static File createTempDirectory() {
		File workingDirectory;
		Path tmpDir = null;
		try {
			tmpDir = Files.createTempDirectory("cpacep", new FileAttribute[0]);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //$NON-NLS-1$
		workingDirectory = new File(tmpDir.toFile(), new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())); //$NON-NLS-1$
		workingDirectory.mkdirs();
		return workingDirectory;
	}

	public static void deleteDirectory(File file) {
		File[] contents = file.listFiles();
		if (contents != null) {
			for (File f : contents) {
				deleteDirectory(f);
			}
		}
		file.delete();
	}

	public static void copyFolder(File sourceFolder, File destinationFolder) throws IOException {
		if (sourceFolder.isDirectory()) {
			if (!destinationFolder.exists()) {
				destinationFolder.mkdir();
				System.out.println("Directory created :: " + destinationFolder);
			}

			String files[] = sourceFolder.list();

			for (String file : files) {
				File srcFile = new File(sourceFolder, file);
				File destFile = new File(destinationFolder, file);

				//Recursive function call
				copyFolder(srcFile, destFile);
			}
		} else {
			Files.copy(sourceFolder.toPath(), destinationFolder.toPath(), StandardCopyOption.REPLACE_EXISTING);
			System.out.println("File copied :: " + destinationFolder);
		}
	}

	public static void cleanTempDirectory() {
		File[] directories = new File("/tmp/").listFiles(File::isDirectory);
		if (directories != null) {
			for (File file : directories) {
				if (file.getName().contains("cpacep")) {
					deleteDirectory(file);
				}
			}
		}
	}
}
