package net.pizey.jclu.test;

import net.pizey.jclu.LengthUnitConverter;
import net.pizey.test.rdd.Requirements;
import junit.framework.TestCase;

/**
 * @author timp
 * @since 2013-08-14
 */
public class LengthUnitConverterTest extends TestCase {

  @Requirements({"01"})
  public final void testMain() {
    try { 
      LengthUnitConverter.main(new String[] {"one in"});
      fail("Should have bombed");
    } catch (IllegalArgumentException e){ 
      e = null;
    }
  }

  @Requirements({"02"})
  public final void testRun() {
    LengthUnitConverter it = new LengthUnitConverter();
    assertEquals("10 cm equals 0.1 m", it.run("10 cm in m".split(" ")));
  }

}
