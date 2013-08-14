package net.pizey.jclu;

/** 
 * A tuple of a real and a unit to represent a length. 
 * @author timp
 * @since 2013-08-13
 */
public class Length {

  private Double real;
  private LengthUnit lengthUnit;

  public Length(Double real, String unitSymbol) {
    this.setReal(real);
    this.setLengthUnit(LengthUnit.fromSymbol(unitSymbol));
  }

  public Double getReal() {
    return real;
  }

  public void setReal(double real) {
    this.real = new Double(real);
  }

  public String getUnitSymbol() {
    return lengthUnit.getSymbol();
  }

  public LengthUnit getLengthUnit() {
    return lengthUnit;
  }

  public void setLengthUnit(LengthUnit lengthUnit) {
    this.lengthUnit = lengthUnit;
  }

  /** Print with full unit name. */
  public String pprint() { 
    return real.toString() + " " + lengthUnit.getName(); 
  }
  
  private static String prettyDouble(Double d){
    String out = d.toString();
    return out.replaceFirst("\\.0", "");
  }
  @Override
  public String toString() { 
    return prettyDouble(real) + " " + lengthUnit.getSymbol(); 
  }
  
  /** Mint a Length using for example "9 in". */
  public Length fromString(String stringLength){
    String[] bits = stringLength.trim().split(" ");
    if (bits.length != 2)
      throw new IllegalArgumentException("Cannot parse " + stringLength);
    return new Length(new Double(bits[0]), bits[1]);      
  }
  
  /** Convert Length of given unit. */
  public Length toUnit(String symbol){
    // autoboxed
    return new Length(
        (real * lengthUnit.getMetres()) / LengthUnit.fromSymbol(symbol).getMetres(), 
        symbol);
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result
        + lengthUnit.hashCode();
    result = prime * result + real.hashCode();
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Length other = (Length) obj;
    if (!lengthUnit.equals(other.lengthUnit))
      return false;
    if (!real.equals(other.real))
      return false;
    return true;
  }

}