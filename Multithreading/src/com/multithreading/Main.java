package com.multithreading;

public class Main {

	public static void main(String[] args) {
		Car car1 = Car.getInstance();
		Car car2 = Car.getInstance();
		Car car3 = Car.getInstance();
		
		System.out.println(car1);
		System.out.println(car2);
		System.out.println(car3);
	}

}
