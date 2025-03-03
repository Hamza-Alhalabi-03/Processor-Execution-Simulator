package org.example;


public class Main {
    public static void main(String[] arguments) {
        Simulator simulatorOne = new BasicSimulator(Integer.parseInt(arguments[0]), Integer.parseInt(arguments[1]), arguments[2]);
        simulatorOne.startSimulation();
    }
}