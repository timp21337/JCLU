package net.pizey.jclu;

import java.util.HashMap;
import java.util.Map;

/** 
 * A definite magnitude of a physical quantity: length.
 * 
 * @author timp
 * @since 2013-08-13
 */
public class LengthUnit implements Comparable<LengthUnit> {

  public static LengthUnit IN = new LengthUnit("in", "inches", 0.0254);
  public static LengthUnit FT = new LengthUnit("ft", "feet", 0.9144);
  public static LengthUnit YD = new LengthUnit("yd", "yards", 0.9144);
  public static LengthUnit M = new LengthUnit("m", "metres", 1.0);
  public static LengthUnit CM = new LengthUnit("cm", "centimetres", 0.01);
  
  private static Map<String, LengthUnit> units = new HashMap<String, LengthUnit>();
  static { 
    units.put(IN.getSymbol(), IN);
    units.put(FT.getSymbol(), FT);
    units.put(YD.getSymbol(), YD);
    units.put(M.getSymbol(), M);
    units.put(CM.getSymbol(), CM);
  }
  private String symbol;
  private String name;
  private Double metres;
  

  public LengthUnit(String symbol, String name, double metres) {
    this.setSymbol(symbol);
    this.setName(name);
    this.setMetres(new Double(metres)); // none of this new fangled autoboxing :) 
  }

  public static LengthUnit fromSymbol(String symbol){
    return units.get(symbol);
  }

  public void addUnit(LengthUnit lengthUnit){
    units.put(lengthUnit.getSymbol(), lengthUnit);
  }
  
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSymbol() {
    return symbol;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  public Double getMetres() {
    return metres;
  }

  public void setMetres(Double metres) {
    this.metres = metres;
  }

  /** Note: this is not an enum type as we need our own compareTo. */
  @Override
  public int compareTo(LengthUnit o) {
    return getMetres().compareTo(o.getMetres());
  }

}
