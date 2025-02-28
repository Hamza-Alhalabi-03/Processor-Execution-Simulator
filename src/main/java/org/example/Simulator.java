package org.example;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Simulator {
    private int numOfProcessors;
    private int numOfClockCycles; // or clockCycles
    private List<Task> tasks;
    private PriorityQueue<Task> highPriorityQueue = new PriorityQueue<>(Comparator.comparingInt((Task t) -> t.getExecutionTime()).reversed());
    private PriorityQueue<Task> lowPriorityQueue = new PriorityQueue<>(Comparator.comparingInt((Task t) -> t.getExecutionTime()).reversed());
    private Clock clock = new Clock();
    private Scheduler scheduler = new Scheduler();

    public Simulator(int numOfProcessors, int numOfClockCycles, String inputFilePath){
        this.numOfProcessors = numOfProcessors;
        this.numOfClockCycles = numOfClockCycles;
        tasks = TaskExtractor.extract(inputFilePath);
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
 * <p>
 *     will own a single Clock instance that manages cycle progression and notifies the Simulator of cycle changes
 * </p>
 *
 * <p>
 *      should have a composition relationship with the Scheduler.
 *      The Simulator will delegate task assignment decisions to the Scheduler when processors become available
 * </p>
 *
 * <p>
 *     should have a composition relationship with multiple Processor objects (based on the input).
 *     The Simulator will track the state of all processors and manage the
 *     assignment of tasks to processors based on the Scheduler's decisions
 * </p>
 *
 * <p>
 *     should maintain a queue of waiting tasks. When a task is created, it will be added to
 *     this queue, and the Simulator will ask the Scheduler to assign tasks from this queue
 *     when processors become available
 * </p>
 *
 */
