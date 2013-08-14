package net.pizey.jclu.test;

import net.pizey.jclu.Length;
import net.pizey.test.rdd.Requirements;
import junit.framework.TestCase;

public class LengthTest extends TestCase {

  @Requirements({"03"})
  public void testReflexive() { 
    assertEquals(new Length(11, "in"), new Length(11, "in"));
  }
}
