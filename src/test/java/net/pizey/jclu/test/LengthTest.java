package net.pizey.jclu.test;

import net.pizey.jclu.Length;
import net.pizey.test.rdd.Requirements;
import junit.framework.TestCase;

public class LengthTest extends TestCase {

  @Requirements({"03"})
  public void testReflexive() { 
    assertEquals(new Length(11, "in"), new Length(11, "in"));
  }

  
  @Requirements({"04"})
  public void testSymetric(){
    assertEquals(new Length(1.1, "yd").toUnit("m"),
                new Length(1.00584, "m"));
    assertEquals(new Length(1.00584, "m").toUnit("yd"),
                       new Length(1.1, "yd"));

    assertEquals(new Length(1.1, "yd"),
                new Length(1.1, "yd").toUnit("m").toUnit("yd"));
  }

}
