package com.multithreading;

public class Car {
   private static Car instance;
   
   private Car() {}
   
   public static Car getInstance() {
	   if(instance == null) {
		   instance = new Car();
	   }
	   return instance;
   }
}
