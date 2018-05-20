package org.eclipse.cpacep.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.text.SimpleDateFormat;
import java.util.*;
import org.eclipse.core.runtime.*;
import org.eclipse.debug.core.ILaunchConfiguration;

public class CPACEPConnector {

	public static final String NO_VALUE = ""; //$NON-NLS-1$
	public static final String LC_CPACEP_SOURCE = "cpacep.source"; //$NON-NLS-1$
	public static final String LC_CPACEP_CMD = "cbmc.cmd"; //$NON-NLS-1$
	public static final String LC_CPACEP_SPECIFICATION = "cbmc.spec"; //$NON-NLS-1$
	public static final String LC_CPACEP_CONFIGURATION = "cbmc.config"; //$NON-NLS-1$

	public static final String LC_CPACEP_EXECUTABLE = "cpacep.executable"; //$NON-NLS-1$

	public static final int LC_SOURCES_TO_CHECK = 1;

	private File workingDirectory;
	private List<String> baseCli = new ArrayList<String>();
	private static final String PLUGIN_ID = "org.eclipse.cpacep"; //$NON-NLS-1$

	private ILog logger = Platform.getPlugin(PLUGIN_ID).getLog();
	private String lcCPACEPExecutable;
	private String lcSourceFile;
	private String lcCommandLine;
	private String lcSpecification;
	private String lcConfiguration;

	private CPACEPConnector(ILaunchConfiguration config) {
		try {
			lcCPACEPExecutable = config.getAttribute(LC_CPACEP_EXECUTABLE, NO_VALUE);
			lcSourceFile = config.getAttribute(LC_CPACEP_SOURCE, NO_VALUE);
			lcCommandLine = config.getAttribute(LC_CPACEP_SOURCE, NO_VALUE);
			lcSpecification = config.getAttribute(LC_CPACEP_SPECIFICATION, NO_VALUE);
			lcConfiguration = config.getAttribute(LC_CPACEP_CONFIGURATION, NO_VALUE);

		} catch (CoreException e) {
			logger.log(new Status(IStatus.ERROR, PLUGIN_ID, "Error retrieving attributes from the launch configuration", e)); //$NON-NLS-1$
		}
		try {
			Path tmpDir = Files.createTempDirectory("cpacep", new FileAttribute[0]); //$NON-NLS-1$
			workingDirectory = new File(tmpDir.toFile(), new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())); //$NON-NLS-1$
			workingDirectory.mkdirs();
		} catch (IOException e) {
			logger.log(new Status(IStatus.ERROR, PLUGIN_ID, "Cannot create a temporary directory", e)); //$NON-NLS-1$
		}
	}

	private void initializeBaseCommandLine() {
		baseCli.add(lcCPACEPExecutable);
		baseCli.add(lcSourceFile);
		baseCli.add(lcSpecification);
		baseCli.add(lcConfiguration);
		baseCli.add(lcCommandLine);
	}

	public static CPACEPConnector create(ILaunchConfiguration config) {
		CPACEPConnector bridge = new CPACEPConnector(config);
		bridge.initializeBaseCommandLine();
		return bridge;
	}

}
