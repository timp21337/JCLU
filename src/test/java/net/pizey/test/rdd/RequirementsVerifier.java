package net.pizey.test.rdd;

import java.io.PrintStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.SortedMap;
import java.util.TreeMap;

import junit.framework.TestCase;

/**
 * Check hat annotated junit test classes meet requirements.
 * 
 * @author timp
 * @since 2013-08-13
 */
public abstract class RequirementsVerifier extends TestCase {

  protected static SortedMap<String, String> requirements = new TreeMap<String, String>();
  protected static SortedMap<String, ArrayList<String>> requirementTests = new TreeMap<String, ArrayList<String>>();
  protected static ArrayList<Class<?>> tests = new ArrayList<Class<?>>();
  
  protected static void addRequirement(String ref, String title) {
    requirements.put(ref, title);
    requirementTests.put(ref, new ArrayList<String>());
  }

  static void readAnnotation(Class<?> test) {
    for (Method method : test.getMethods()) {
      if (method.isAnnotationPresent(Requirements.class)) {
        Annotation singleAnnotation = method.getAnnotation(Requirements.class);
        Requirements requirementRefs = (Requirements)singleAnnotation;
        for (String id : requirementRefs.value()){
          requirementTests.get(id).add(test.getSimpleName() + "." + method.getName());
        }
      }
    }
  }

  static String otag(String tag) {
    return "<" + tag + ">" + "\n";
  }
  static String ctag(String tag) {
    return "</" + tag + ">" + "\n";
  }
  static String tag(String tag, String contents) {
    return otag(tag)
        + contents + "\n"
        + ctag(tag);
  }
  static String html(String s) {
    return tag("head", s);
  }
  static String head(String s) {
    return tag("head", s);
  }
  static String title(String s) {
    return tag("title", s);
  }
  static String body(String s) {
    return tag("body", s);
  }
  static String h1(String s) {
    return tag("h1", s);
  }
  static String h2(String s) {
    return tag("h2", s);
  }
  static String h3(String s) {
    return tag("h3", s);
  }
  static String li(String s) {
    return tag("li", s);
  }
  static boolean printRequirementVerificationMatrix(PrintStream out) {
    boolean allAddressed = true;
    out.println(otag("html")
        + head(
        title("Requirements Verification Matrix")
        + "<link rel=\"stylesheet\" type=\"text/css\" href=\"rdd.css\">\n")
        + otag("body"));
    out.println(h1("RDD - Requirements Verification Matrix"));
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    Calendar cal = Calendar.getInstance();
    out.print(h2(dateFormat.format(cal.getTime())));

    for (String ref : requirements.keySet()){
      out.println("<h3>" + ref + " " + requirements.get(ref) + "</h3>");
      out.println("<ul>");
      boolean hadOne = false;
      for (String test : requirementTests.get(ref)){
        out.println(li(test));
        hadOne = true;
      }
      if (!hadOne){ 
        out.println(li("<font color='red'>None</font>"));
        allAddressed = false;
      }
      out.println("</ul>");
    }
    
    out.println(ctag("body") + ctag("html"));
    return allAddressed;
  }

  public boolean doIt(PrintStream out) {
    addRequirements();
    addTestClasses();
    for (Class<?> t : tests)
      readAnnotation(t);
    return printRequirementVerificationMatrix(out);
  }

  public abstract void addRequirements();
  public abstract void addTestClasses();
}
