package com.prisila.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
/**
 * Indica que o método do controller pode ser acessado sem necessidade de autenticação 
 */
public @interface Publico {
	
}
