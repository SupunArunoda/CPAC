package org.eclipse.cpacep.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.debug.core.ILaunchConfiguration;

public class CPACEPConnector {

	public static final String NO_VALUE = ""; //$NON-NLS-1$
	public static final String LC_CPACEP_SOURCE = "cpacep.source"; //$NON-NLS-1$
	public static final String LC_CPACEP_CMD = "cpacep.cmd"; //$NON-NLS-1$
	public static final String LC_CPACEP_SPECIFICATION = "cpacep.spec"; //$NON-NLS-1$
	public static final String LC_CPACEP_CONFIGURATION = "cpacep.config"; //$NON-NLS-1$

	public static final String LC_CPACEP_EXECUTABLE = "cpacep.executable"; //$NON-NLS-1$
	public static final String LC_CPACEP_MKDIR = "cpacep.mkdir";

	public static final String SPEC_FILE_TYPE = ".spc";
	public static final String CONFIG_FILE_TYPE = ".properties";

	public static final int LC_SOURCES_TO_CHECK = 1;
	public static final int CPA_HOME_PATH = 2;

	public static File workingDirectory;
	private List<String> baseCli = new ArrayList<String>();
	//private static final String PLUGIN_ID = "org.eclipse.cpacep"; //$NON-NLS-1$

	//private ILog logger = Platform.getPlugin(PLUGIN_ID).getLog();
	private String lcCPACEPExecutable;
	private String lcSourceFile;
	private String lcCommandLine;
	private String lcSpecification;
	private String lcConfiguration;
	private String lcMkdir;

	private String CPACheckerHome;
	static {
		File[] directories = new File("/tmp/").listFiles(File::isDirectory);
		if (directories != null) {
			for (File file : directories) {
				if (file.getName().contains("cpacep")) {
					FileHandler.deleteDirectory(file);
				}
			}
		}

	}

	public CPACEPConnector(ILaunchConfiguration config) {

		try {
			lcCPACEPExecutable = config.getAttribute(LC_CPACEP_EXECUTABLE, NO_VALUE);
			lcSourceFile = config.getAttribute(LC_CPACEP_SOURCE, NO_VALUE);
			lcCommandLine = config.getAttribute(LC_CPACEP_CMD, NO_VALUE);
			lcSpecification = config.getAttribute(LC_CPACEP_SPECIFICATION, NO_VALUE);
			lcConfiguration = config.getAttribute(LC_CPACEP_CONFIGURATION, NO_VALUE);
			lcMkdir = config.getAttribute(LC_CPACEP_MKDIR, NO_VALUE);
			setCPACheckerHome();

			if (lcMkdir.equals("Y") || workingDirectory == null) {
				workingDirectory = FileHandler.createTempDirectory();
				FileHandler.copyFolder(new File(CPACheckerHome), workingDirectory);
			}

		} catch (Exception e) {
			e.printStackTrace();
			//logger.log(new Status(IStatus.ERROR, PLUGIN_ID, "Error retrieving attributes from the launch configuration", e)); //$NON-NLS-1$
		}
	}

	public void setCPACheckerHome() {
		String homeSplit[] = lcCPACEPExecutable.split("/");
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < homeSplit.length - CPA_HOME_PATH; i++) {
			if (!homeSplit[i].equals("")) {
				sb.append("/" + homeSplit[i]);
			}
		}
		sb.append("/");
		CPACheckerHome = sb.toString();
	}

	private void initializeBaseCommandLine() {
		baseCli.add(workingDirectory + "/scripts/cpa.sh");
		baseCli.add(" -spec " + workingDirectory + "/config/specification/" + lcSpecification + SPEC_FILE_TYPE);
		baseCli.add(" -config " + workingDirectory + "/config/" + lcConfiguration + CONFIG_FILE_TYPE);
		baseCli.add(lcCommandLine);
		baseCli.add(lcSourceFile);
	}

	private String executeCommand(String command) {
		Process p;
		StringBuilder output = new StringBuilder();
		try {
			p = Runtime.getRuntime().exec(command);
			p.waitFor();
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

			String line = "";
			while ((line = reader.readLine()) != null) {
				output.append(line + "\n");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return output.toString();
	}

	private void fillInLaunch() {
		StringBuilder sb = new StringBuilder();
		for (String command : baseCli) {
			if (!command.equals("")) {
				sb.append(command + " "); //$NON-NLS-1$
			}

		}
		String out = executeCommand(sb.toString());
		System.out.println(out);

	}

	public static CPACEPConnector create(ILaunchConfiguration config) {
		CPACEPConnector bridge = new CPACEPConnector(config);
		bridge.initializeBaseCommandLine();
		bridge.fillInLaunch();
		return bridge;
	}

}
