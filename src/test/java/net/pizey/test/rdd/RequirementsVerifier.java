package net.pizey.test.rdd;

import java.io.PrintStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import org.junit.Ignore;

import junit.framework.TestCase;

import static net.pizey.test.rdd.Html.*;

/**
 * Check hat annotated junit test classes meet requirements.
 * 
 * @author timp
 * @since 2013-08-13
 */
public abstract class RequirementsVerifier extends TestCase {

  protected static SortedMap<String, String> requirements = new TreeMap<String, String>();
  protected static SortedMap<String, Set<String>> requirementTests = new TreeMap<String, Set<String>>();
  protected static ArrayList<Class<?>> tests = new ArrayList<Class<?>>();
  
  protected static void addRequirement(String ref, String title) {
    requirements.put(ref, title);
    requirementTests.put(ref, new TreeSet<String>());
  }

  static void readAnnotation(Class<?> test) {
    for (Method method : test.getMethods()) {
      if (method.isAnnotationPresent(Requirements.class)) {
        if (!method.isAnnotationPresent(Ignore.class)) {
          Annotation singleAnnotation = method.getAnnotation(Requirements.class);
          Requirements requirementRefs = (Requirements) singleAnnotation;
          for (String id : requirementRefs.value()) {
            requirementTests.get(id).add(test.getSimpleName() + "." + method.getName());
          }
        }
      }
    }
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
      out.println(h3(ref + " " + requirements.get(ref)));
      out.println(otag("ul"));
      boolean hadOne = false;
      for (String test : requirementTests.get(ref)){
        out.println(li(test));
        hadOne = true;
      }
      if (!hadOne){ 
        out.println(li("<font color='red'>None</font>"));
        allAddressed = false;
      }
      out.println(ctag("ul"));
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
