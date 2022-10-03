package Multithreading;

import java.io.*;
import java.util.*;

//Calories Class with Balance starting at 0 (zero).
class Calories {

	// This is the variable that will start the balance at 0 (zero).
	private int balance;

	public Calories() {
		// Balance starts and ends at 0.
		balance = 0;
	}

    public void gainCalories(int amount) {

        int newBalance = balance + amount;
        System.out.println(Thread.currentThread().getName() +" Gaining " + amount + ", new balance is " + newBalance + ".");
        balance = newBalance;
    }

    public void burnCalories(int amount) {

    	int newBalance = balance - amount;
        System.out.println(Thread.currentThread().getName() +" Burning " + amount + ", new balance is " + newBalance + ".");
        balance = newBalance;
    }
}

// Zerosum Class
public class Zerosum extends Thread {

	static final int DELAY = 10;
	static Calories cal = new Calories();
	static int init = 10;// The amount of times you want each thread to run.
	static int amount = 100;
	
	
	// Driver Method
	public static void main(String args[]) {

		// Thread 1
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				try {
					synchronized(cal)
					{
					for (int i = 1; i <= init; i++) {
						cal.gainCalories(amount);
						Thread.sleep(DELAY);
					}
					}
				} catch (InterruptedException exception) {
				}
			}
		});

		// Thread 2
		Thread t2 = new Thread(new Runnable() {
			public void run() {
				try {
					synchronized(cal)
					{
					for (int i = 1; i <= init; i++) {
						cal.burnCalories(amount);
						Thread.sleep(DELAY);
					}
					}
				} catch (InterruptedException exception) {
				}
			}
		});

		t1.start();
		t2.start();

	}
}

