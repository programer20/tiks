package com.fon.tiks.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Monitoring {
	
	public Database database();
	
	public DataSize dataSize();
	
	public Operation operation();
	
	public enum Database {
		ORACLE, MONGODB, EXISTDB
	}
	
	public enum DataSize {
		SMALL, MEDIUM, LARGE
	}
	
	public enum Operation {
		INSERT, SELECT, UPDATE, DELETE
	}

}
