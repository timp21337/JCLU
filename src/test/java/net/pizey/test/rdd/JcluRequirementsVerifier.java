package net.pizey.test.rdd;

import java.util.ArrayList;

import net.pizey.jclu.AppTest;

public class JcluRequirementsVerifier extends RequirementsVerifier {

  public static void addRequirements() {
    addRequirement("01", "foo");
    addRequirement("02", "bar");
  }

  private static void addRequirement(String ref, String title) {
    requirements.put(ref, title);
    requirementTests.put(ref, new ArrayList<String>());
  }

  public static void main(String[] args) {
    addRequirements();
    Class<?>[] tests = new Class<?>[] { AppTest.class };
    for (Class<?> t : tests)
      readAnnotation(t);
    printRequirementVerificationMatrix();
  }

}
