package org.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Simulator implements ClockObserver{
    private int numOfProcessors;
    private ExecutorService processorPool;
    private List<Processor> processors;
    private int maxCycle;
    private int currentCycle;
    private List<Task> tasks;

    private Clock clock;
    private Scheduler scheduler = new Scheduler();

    public Simulator(int numOfProcessors, int maxCycle, String inputFilePath){
        this.numOfProcessors = numOfProcessors;
        this.maxCycle = maxCycle;
        this.clock = new Clock(maxCycle);
        clock.addObserver(this);
        tasks = TaskExtractor.extract(inputFilePath);
        InitializeProcessors();
    }

    private void InitializeProcessors() {
        processorPool = Executors.newFixedThreadPool(numOfProcessors);
        processors = new ArrayList<>();

        for (int i = 1; i <= numOfProcessors; i++) {
            Processor processor = new Processor("P" + i);
            processors.add(processor);
            processorPool.submit(processor);
        }
    }

    private void scheduleTasks(){
        List<Task> tasksToRemove = new ArrayList<>();

        for (Task task : tasks) {
            if (task.getCreationTime() == clock.getCurrentCycle()) {
                scheduler.scheduleTask(task);
                tasksToRemove.add(task);
            }
        }

        tasks.removeAll(tasksToRemove);
    }

    public void startSimulation(){
        Thread clockThread = new Thread(clock);
        clockThread.start();


        try {
            clockThread.join();
            shutdown();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            shutdown();
        }
        // TODO: refactor shutdown with try-catch-finally or try-with-resources
    }

    @Override
    public void onClockTick(int currentCycle) {
        this.currentCycle = currentCycle;
        scheduleTasks();
        scheduler.assignTasks(processors, processorPool);
        //
        //
        //
        generateReport();
    }

    private void generateReport() {
        System.out.println("Current Cycle: " + currentCycle);
    }

    public void shutdown() {
        processorPool.shutdown();
    }
}

/**
 * <p>using the principles of object-oriented programming and take into </p>
 * <p>consideration that your simulator is maybe extended in the future</p>
 *
 *
 * <p>make the processors as a pool of threads</p>
 * ::manage pool manually or use ExecutorService?::
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
