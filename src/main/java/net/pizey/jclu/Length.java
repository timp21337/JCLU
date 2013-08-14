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
  
  private static String prettyDouble(Double d){
    String out = d.toString();
    return out.replaceFirst("\\.0", "");
  }
  @Override
  public String toString() { 
    return prettyDouble(real) + " " + getLengthUnitSymbol(); 
  }
  
  /** Convert Length of given unit. */
  public Length toUnit(String symbol){
    // autoboxed
    return new Length(
        (getReal() * getLengthUnit().getMetres()) / LengthUnit.fromSymbol(symbol).getMetres(), 
        symbol);
  }
  
}
