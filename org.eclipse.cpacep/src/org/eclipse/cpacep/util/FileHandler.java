package org.eclipse.cpacep.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.FileAttribute;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;

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
		try {
			FileUtils.deleteDirectory(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void copyFolder(File sourceFolder, File destinationFolder) throws IOException {
		//		Check if sourceFolder is a directory or file
		//		If sourceFolder is file; then copy the file directly to new location
		if (sourceFolder.isDirectory()) {
			//Verify if destinationFolder is already present; If not then create it
			if (!destinationFolder.exists()) {
				destinationFolder.mkdir();
				System.out.println("Directory created :: " + destinationFolder);
			}

			//Get all files from source directory
			String files[] = sourceFolder.list();

			//Iterate over all files and copy them to destinationFolder one by one
			for (String file : files) {
				File srcFile = new File(sourceFolder, file);
				File destFile = new File(destinationFolder, file);

				//Recursive function call
				copyFolder(srcFile, destFile);
			}
		} else {
			//Copy the file content from one place to another
			Files.copy(sourceFolder.toPath(), destinationFolder.toPath(), StandardCopyOption.REPLACE_EXISTING);
			System.out.println("File copied :: " + destinationFolder);
		}
		//FileUtils.copyDirectory(sourceFolder, destinationFolder);
	}
}
