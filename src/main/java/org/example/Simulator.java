package org.example;

import java.util.List;
import java.util.concurrent.ExecutorService;


public abstract class Simulator implements ClockObserver{
    protected int numOfProcessors;
    protected ExecutorService processorPool;
    protected List<Processor> processors;
    protected int maxCycle;
    protected int currentCycle;
    protected List<Task> tasks;
    protected Clock clock;
    protected Scheduler scheduler;

    protected Simulator(int numOfProcessors, int maxCycle, String inputFilePath){
        this.numOfProcessors = numOfProcessors;
        this.maxCycle = maxCycle;
        this.clock = Clock.getInstance(maxCycle);
        clock.addObserver(this);
        tasks = TaskExtractor.extract(inputFilePath);
        InitializeProcessors();
    }

    protected abstract void InitializeProcessors();

    protected abstract void scheduleTasks();

    public final void startSimulation(){
        Thread clockThread = new Thread(clock);
        clockThread.start();

        try {
            clockThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            shutdown();
        }
    }

    protected abstract void generateReport();

    public void shutdown() {
        processorPool.shutdown();
    }
}
