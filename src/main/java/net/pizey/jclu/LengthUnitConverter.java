package net.pizey.jclu;

/**
 * @author timp
 * @since 2013-08-14
 */
public class LengthUnitConverter {

  static LengthUnitConverter it = new LengthUnitConverter();

  public static void main(String[] args) {
    System.out.println(it.run(args));
  }

  /** Expecting args to contain for example "10 cm in m" */
  public String run(String[] args) {
    if (args.length != 4)
      throw new IllegalArgumentException("Cannot parse, arguments array length:" + args.length);
    Length lengthIn = new Length(new Double(args[0]), args[1]);
    Length lengthOut = lengthIn.toUnit(args[3]);
    return (lengthIn.toString() + " equals " + lengthOut.toString());
  }
}
