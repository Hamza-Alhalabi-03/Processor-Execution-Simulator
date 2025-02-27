package org.example;

import java.util.List;

public class Simulator {
    private int numOfProcessors;
    private int numOfClockCycles; // or clockCycles

    private List<Task> tasks;

    private Scheduler scheduler; // = new Scheduler();

    public Simulator(int numOfProcessors, int numOfClockCycles, String inputFilePath){

    }
}

/**
 * <p>using the principles of object-oriented programming and take into </p>
 * <p>consideration that your simulator is maybe extended in the future</p>
 *
 *
 * <p>make the processors as a pool of threads</p>
 * ::manage pool manually or use ExecuterService?::
 *
 *
 */
