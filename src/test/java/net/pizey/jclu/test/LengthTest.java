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


  @Requirements({"05"})
  /** If x = y and y = z then x = z */
  public void testTransitive(){
    assertEquals(new Length(1.1, "yd").toUnit("m").toUnit("in"),
                 new Length(39.6, "in"));
    assertEquals(new Length(39.6, "in").toUnit("m").toUnit("yd"),
                 new Length(1.1, "yd"));
  }

  @Requirements({"06", "07"})
  public void testConvertBetweenAllUnits(){
    assertEquals("39.6 in", new Length(1.1, "yd").toUnit("in").toString());
    assertEquals("3.3 ft",  new Length(1.1, "yd").toUnit("ft").toString());
    assertEquals("1.006 m", new Length(1.1, "yd").toUnit("m").toString());
    assertEquals("100.584 cm", new Length(1.1, "yd").toUnit("cm").toString());

    assertEquals("0.367 yd", new Length(1.1, "ft").toUnit("yd").toString());
    assertEquals("13.2 in", new Length(1.1, "ft").toUnit("in").toString());
    assertEquals("0.335 m", new Length(1.1, "ft").toUnit("m").toString());
    assertEquals("33.528 cm", new Length(1.1, "ft").toUnit("cm").toString());

    assertEquals("3.056 yd", new Length(110, "in").toUnit("yd").toString());
    assertEquals("9.167 ft", new Length(110, "in").toUnit("ft").toString());
    assertEquals("2.794 m", new Length(110, "in").toUnit("m").toString());
    assertEquals("279.4 cm", new Length(110, "in").toUnit("cm").toString());

    assertEquals("1.203 yd", new Length(1.1, "m").toUnit("yd").toString());
    assertEquals("3.609 ft", new Length(1.1, "m").toUnit("ft").toString());
    assertEquals("43.307 in", new Length(1.1, "m").toUnit("in").toString());
    assertEquals("110 cm", new Length(1.1, "m").toUnit("cm").toString());

    assertEquals("0.012 yd", new Length(1.1, "cm").toUnit("yd").toString());
    assertEquals("0.036 ft", new Length(1.1, "cm").toUnit("ft").toString());
    assertEquals("0.433 in", new Length(1.1, "cm").toUnit("in").toString());
    assertEquals("0.011 m", new Length(1.1, "cm").toUnit("m").toString());

  }
  
  @Requirements({"08"})
  public void testRounding(){
    assertEquals(new Length(36, "in").toUnit("yd").toString(), new Length(1, "yd").toString());
  }
}
