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
 * Enable the cross referencing of tests to requirements.
 * 
 * @author timp
 * @since 2013-08-13
 */
public abstract class RequirementsVerifier extends TestCase {

  static SortedMap<String, String> requirements = new TreeMap<String, String>();
  static SortedMap<String, ArrayList<String>> requirementTests = new TreeMap<String, ArrayList<String>>();
  static ArrayList<Class<?>> tests = new ArrayList<Class<?>>();
  
  static void addRequirement(String ref, String title) {
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
  
  static boolean printRequirementVerificationMatrix(PrintStream out) { 
    boolean allAddressed = true;
    out.println("<html>");
    out.println("<body>");
    out.println("<h1>Requirements Verification Matrix</h1>");
    out.print("<h2>");
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    Calendar cal = Calendar.getInstance();
    out.print(dateFormat.format(cal.getTime()));
    out.println("</h2>");
    
    for (String ref : requirements.keySet()){
      out.println("<h3>" + ref + " " + requirements.get(ref) + "</h3>");
      out.println("<ul>");
      boolean hadOne = false;
      for (String test : requirementTests.get(ref)){
        out.println(" <li>" + test + "</li>");
        hadOne = true;
      }
      if (!hadOne){ 
        out.println(" <li><font color='red'>None</font></li>");
        allAddressed = false;
      }
      out.println("</ul>");
    }
    
    out.println("</body>");
    out.println("</html>");
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
