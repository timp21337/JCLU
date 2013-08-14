package net.pizey.test.rdd;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.SortedMap;
import java.util.TreeMap;

public abstract class RequirementsVerifier {

  static SortedMap<String, String> requirements = new TreeMap<String, String>();
  static SortedMap<String, ArrayList<String>> requirementTests = new TreeMap<String, ArrayList<String>>();
  
  static void readAnnotation(Class<?> test) {
    System.out.println("Annotation element for " + test.getSimpleName());
    for (Method method : test.getMethods()) {
      if (method.isAnnotationPresent(Requirements.class)) {
        System.out.println(method.getName());
        // getAnnotation returns Annotation type
        Annotation singleAnnotation = method.getAnnotation(Requirements.class);
        Requirements requirementRefs = (Requirements) singleAnnotation;

        for (String id : requirementRefs.value()){
          System.out.println(" " + id + ", " + requirements.get(id));
          requirementTests.get(id).add(test.getSimpleName() + "." + method.getName());
        }
        System.out.print("\n");
      }
    }
  }
  
  static void printRequirementVerificationMatrix() { 
    System.out.println("<html>");
    System.out.println("<body>");
    System.out.println("<h1>Requirements Verification Matrix</h1>");
    System.out.print("<h2>");
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    Calendar cal = Calendar.getInstance();
    System.out.print(dateFormat.format(cal.getTime()));
    System.out.println("</h2>");
    
    for (String ref : requirements.keySet()){
      System.out.println("<h3>" + ref + " " + requirements.get(ref) + "</h3>");
      System.out.println("<ul>");
      boolean hadOne = false;
      for (String test : requirementTests.get(ref)){
        System.out.println(" <li>" + test + "</li>");
        hadOne = true;
      }
      if (!hadOne)
        System.out.println(" <li><font color='red'>None</font></li>");
      System.out.println("</ul>");
    }
    
    System.out.println("</body>");
    System.out.println("</html>");
  }

}
