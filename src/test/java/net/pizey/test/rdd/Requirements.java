package net.pizey.test.rdd;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

/**
 * Enable the cross referencing of tests to requirements.
 */
@Target({ElementType.METHOD}) 
@Retention(RetentionPolicy.RUNTIME) 
public @interface Requirements {
  String[] value() default "";  
}
