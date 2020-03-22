package com.company;

/*

1620 One for all, all for one
1. Understand how the program works.
1.1. Note that the Water object is one for all threads.
2. Implement ourInterruptMethod method to interrupt all threads from threads.
3. In the run method, correct the values ​​of the variables:
3.1. isCurrentThreadInterrupted - must be equal to the value of the isInterrupted method on the current thread.
3.2. threadName - must be equal to the value of the getName method (implemented in the Thread class) on the current thread.

Requirements:
1. OurInterruptMethod method should interrupt all threads from the threads list.
2. The run method must receive the current thread using Thread.currentThread.
3. The run method must use the isInterrupted method of the current thread.
4. The run method must use the getName method of the current thread.
5. The main method should run for about 3 seconds.


 */

import java.util.ArrayList;
import java.util.List;

/*
Horse Racing
*/

public class Solution {
    public static byte countThreads = 3;
    static List<Thread> threads = new ArrayList<Thread>(countThreads);

    public static void main(String[] args) throws InterruptedException {
        initThreadsAndStart();
        Thread.sleep(3000);
        ourInterruptMethod();
    }

    public static void ourInterruptMethod() {
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }

    private static void initThreadsAndStart() {
        Water water = new Water("water");
        for (int i = 0; i < countThreads; i++) {
            threads.add(new Thread(water, "#" + i));
        }

        for (int i = 0; i < countThreads; i++) {
            threads.get(i).start();
        }
    }

    public static class Water implements Runnable {
        private String commonResource;

        public Water(String commonResource) {
            this.commonResource = commonResource;
        }

        public void run() {
            //fix 2 variables - исправь 2 переменных
            boolean isCurrentThreadInterrupted = Thread.currentThread().isInterrupted();
            String threadName = Thread.currentThread().getName();

            try {
                while (!isCurrentThreadInterrupted) {

                    System.out.println("Объект " + commonResource + ", нить " + threadName);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
            }
        }
    }
}



