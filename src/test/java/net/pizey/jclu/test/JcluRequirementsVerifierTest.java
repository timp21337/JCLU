package net.pizey.jclu.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;

import net.pizey.test.rdd.RequirementsVerifier;

/**
 * @author timp
 * @since 2013-08-13
 */
public class JcluRequirementsVerifierTest extends RequirementsVerifier {

  public void addRequirements() {
    addRequirement("01", "Anticipated exceptions must be tested.");
    addRequirement("02", "Integers must not be printed with trailing decimal.");
    addRequirement("03", "Conversions must be reflexive.");
    addRequirement("04", "Conversions must be symmetric.");
    addRequirement("05", "Conversions must be transitive.");
    addRequirement("06", "All length units must be convertible to each other.");
    addRequirement("07", "Decimals to be rounded to three decimal places.");
    addRequirement("08", "Rounding errors must not fail comparisons.");
    addRequirement("09", "All code must be exercised by tests.");
  }
  
  @Override
  public void addTestClasses() {
    tests.add(LengthUnitConverterTest.class);    
    tests.add(LengthTest.class);    
  }

  public void testAllRequirements() throws Exception {
    String outputDirectory = "rdd/RequirementsVerification";
    String outputFile = outputDirectory + "/index.html";
    new File(outputDirectory).mkdirs();
    PrintStream out = new PrintStream(
            new FileOutputStream(outputFile));
    boolean allSatisfied = doIt(out);
    if (allSatisfied) {
      System.out.println("All known requirements addressed, see " + outputFile);
    } else {
      System.err.println("Some known requirements still unaddressed, see " + outputFile);
    }
  }

}
