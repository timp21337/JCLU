package net.pizey.test.rdd;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public abstract class RequirementsVerifier {

  static HashMap<String, String> requirements = new HashMap<String, String>();
  static HashMap<String, ArrayList<String>> requirementTests = new HashMap<String, ArrayList<String>>();
  
  static void readAnnotation(Class<?> test) {
    System.out.println("Annotation element for " + test.getSimpleName());
    for (Method method : test.getMethods()) {
      if (method.isAnnotationPresent(Requirements.class)) {
        System.out.println(method.getName());
        // getAnnotation returns Annotation type
        Annotation singleAnnotation = method.getAnnotation(Requirements.class);
        Requirements requirementRefs = (Requirements) singleAnnotation;

        for (String id : requirementRefs.ids()){
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
      for (String test : requirementTests.get(ref)){
        System.out.println(" <li>" + test + "</li>");
      }
      System.out.println("</ul>");
    }
    
    System.out.println("</body>");
    System.out.println("</html>");
  }

}
