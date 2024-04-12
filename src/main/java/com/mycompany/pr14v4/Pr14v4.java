/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.pr14v4;

import java.io.IOException;

/**
 *
 * @author ПК
 */
public class Pr14v4 {

    private static final Object monitor = new Object();
    private static volatile boolean running = true;
    private static int currentThread = 0;

    public static void main(String[] args) throws IOException {
        
        System.out.println("Дунина Виктория РИБО-02-22 Вариант 4");

        Thread thread0 = new Thread(() -> {
            while (running) {
                synchronized (monitor) {
                    try {
                        while (currentThread != 0) {
                            monitor.wait();
                        }
                        System.out.println("Thread-0");
                        currentThread = 1;
                        monitor.notifyAll(); 
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }

                try {
                    Thread.sleep(1000); 
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        Thread thread1 = new Thread(() -> {
            while (running) {
                synchronized (monitor) {
                    try {
                        while (currentThread != 1) {
                            monitor.wait();
                        }
                        System.out.println("Thread-1");
                        currentThread = 0;
                        monitor.notifyAll(); 
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }

                try {
                    Thread.sleep(1000); 
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        thread0.start();
        thread1.start();

        System.in.read();
        running = false;

        synchronized (monitor) {
            monitor.notifyAll();
        }
    }
}
