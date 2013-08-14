package net.pizey.jclu.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;

import net.pizey.jclu.AppTest;
import net.pizey.test.rdd.RequirementsVerifier;

/**
 * @author timp
 * @since 2013-08-13
 */
public class JcluRequirementsVerifier extends RequirementsVerifier {

  public void addRequirements() {
    addRequirement("01", "Conversions must be reflexive.");
    addRequirement("02", "Conversions must be symetric.");
    /*
    addRequirement("03", "Conversions must be transitive.");
    addRequirement("04", "Expressions may only be composed of units " + 
                         "where subsequent units are smaller than preceding.");
    addRequirement("05", "All length units must be convertible to each other.");
    addRequirement("06", "Rounding errors must not fail comparisons.");
    addRequirement("07", "There must be a mechanism to add new units.");
    addRequirement("08", "An object must be creatable from a string representation.");
    addRequirement("09", "Anticipated exceptions must be tested.");
    addRequirement("10", "Lengths must have an additional unabbreviated textual description.");
*/
  }
  
  @Override
  public void addTestClasses() {
    tests.add(AppTest.class);    
  }

  public void testAllRequirements() throws Exception { 
    File dir = new File("qa/RequirementsVerification");
    dir.mkdirs();
    PrintStream out = new PrintStream(
        new FileOutputStream("qa/RequirementsVerification/index.html")); 
    assertFalse("Some Requirements still unaddressed", !doIt(out));    
  }

}
