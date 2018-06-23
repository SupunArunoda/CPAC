package org.eclipse.cpacep.util;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class CPACEPConnectorTest {
	//String homePath = "/home/supun/Documents/cpachecker";
	//Path homePath = Paths.get(File.separator + "home").resolve("supun").resolve("Documents").resolve("CPAchecker-1.7-unix");
	//Path homePath = Paths.get("/home/supun/Documents/CPAchecker-1.7-unix");
	Path homePath = Paths.get(File.separator + "home").resolve("travis").resolve("build").resolve("SupunArunoda").resolve("cpachecker");
	//Path homePath = StringHandler.getHomePath(File.separator + "home" + File.separator + "travis" + File.separator + "build" + File.separator + "SupunArunoda" + File.separator + "cpachecker");
	StringBuilder sb;
	String lcSpecification;
	String lcConfiguration;
	String result;

	Path outputPathOriginal = Paths.get(File.separator + "tmp").resolve("original");
	File outputDirectory;

	@Before
	public void setup() {
		CPACEPConnector cpacepConnector = new CPACEPConnector();
		lcSpecification = "default";
		lcConfiguration = "default";
		try {
			outputDirectory = FileHandler.createTempDirectory();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new AssertionError();
		}
		sb = new StringBuilder();
		sb.append(homePath.resolve("scripts").resolve("cpa.sh"));
		sb.append(" -spec " + homePath.resolve("config").resolve("specification").resolve(lcSpecification + ".spc"));
		sb.append(" -config " + homePath.resolve("config").resolve(lcConfiguration + ".properties"));
		sb.append(" " + homePath.resolve("doc").resolve("examples").resolve("example.c"));
		sb.append(" -outputpath " + outputDirectory);
		sb.append(" -stats");
		cpacepConnector.executeCommand(sb.toString());

	}

	@Test
	public void testStatistics() {
		setup();
		List<String> actualData;
		List<String> expectData;
		String testName = "Verification result:";
		try {
			actualData = FileHandler.readFile(new File(outputDirectory.getAbsolutePath() + File.separator + "Statistics.txt"));
			expectData = FileHandler.readFile(new File(outputPathOriginal + File.separator + "Statistics.txt")); //$NON-NLS-1$
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new AssertionError(e.getMessage());
		}
		//Check whether the results have testName 
		int expectedDataSize = expectData.size();
		int actualDataSize = actualData.size();
		boolean expect = expectData.get(expectedDataSize - 2).contains(testName);
		boolean actual = actualData.get(actualDataSize - 2).contains(testName);
		assertEquals(expect, actual);

	}
}
