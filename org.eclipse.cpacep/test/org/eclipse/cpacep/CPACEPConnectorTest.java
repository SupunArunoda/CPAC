package org.eclipse.cpacep;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.eclipse.cpacep.util.CPACEPConnector;
import org.eclipse.cpacep.util.FileHandler;
import org.junit.Before;
import org.junit.Test;

public class CPACEPConnectorTest {
	//String homePath = "/home/supun/Documents/cpachecker";
	//String homePath = "/home/supun/Documents/CPAchecker-1.7-unix";
	String homePath = File.separator + "home" + File.separator + "travis" + File.separator + "build" + File.separator + "SupunArunoda" + File.separator + "cpachecker";
	StringBuilder sb;
	String lcSpecification;
	String lcConfiguration;
	String result;

	String outputPathOriginal = File.separator + "tmp" + File.separator + "original";
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
		sb.append(homePath + File.separator + "scripts" + File.separator + "cpa.sh");
		sb.append(" -spec " + homePath + File.separator + "config" + File.separator + "specification" + File.separator + lcSpecification + ".spc");
		sb.append(" -config " + homePath + File.separator + "config" + File.separator + lcConfiguration + ".properties");
		sb.append(" " + homePath + File.separator + "doc" + File.separator + "examples" + File.separator + "example.c");
		sb.append(" -outputpath " + outputDirectory);
		sb.append(" -stats");
		cpacepConnector.executeCommand(sb.toString());

	}

	@Test
	public void testStatistics() {
		setup();
		List<String> actualData;
		List<String> expectData;
		try {
			actualData = FileHandler.readFile(new File(outputDirectory.getAbsolutePath() + File.separator + "Statistics.txt"));
			expectData = FileHandler.readFile(new File(outputPathOriginal + File.separator + "Statistics.txt")); //$NON-NLS-1$
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new AssertionError(e.getMessage());
		}
		//Check whether the "Verification result*" are equal 
		int expectedDataSize = expectData.size();
		int actualDataSize = actualData.size();
		assertEquals(expectData.get(expectedDataSize - 2), actualData.get(actualDataSize - 2));

	}
}
