package org.example;

import java.util.ArrayList;
import java.util.List;


public class Clock implements Runnable {
    private static volatile Clock singletonInstance;
    private int currentCycle = 1;
    private final int maxCycle;
    private final List<ClockObserver> observers = new ArrayList<>();

    private Clock(int maxCycle){
        this.maxCycle = maxCycle;
    }

    public static Clock getInstance(int maxCycle) {
        if (singletonInstance == null) {
            synchronized (Clock.class) {
                if (singletonInstance == null) {
                    singletonInstance = new Clock(maxCycle);
                }
            }
        }
        return singletonInstance;
    }

    public void addObserver(ClockObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(ClockObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers() {
        for (ClockObserver observer : observers) {
            observer.onClockTick(currentCycle);
        }
    }

    public int getCurrentCycle() {
        return currentCycle;
    }

    @Override
    public void run() {
        while (currentCycle <= maxCycle) {
            notifyObservers();
            try {
                Thread.sleep(900); // Simulate a cycle duration of 1 second
            } catch (InterruptedException e) {
                System.out.println("Clock interrupted: " + e.getMessage());
            }
            currentCycle++;
        }
    }
}
