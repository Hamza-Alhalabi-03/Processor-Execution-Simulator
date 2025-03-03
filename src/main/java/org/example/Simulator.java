package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.example.ColoredText.printColoredText;

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
        this.clock = Clock.getInstance(maxCycle);
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
        }
    }

    private void scheduleTasks(){
        List<Task> tasksToRemove = new ArrayList<>();

        for (Task task : tasks) {
            if (task.getCreationTime() == clock.getCurrentCycle()) {
                printColoredText("Create task: " + task.getId(), "blue");
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
        generateReport();
    }

    private void generateReport() {
        System.out.println("***************************************");
        System.out.println("Current Cycle: " + currentCycle);
        System.out.println("***************************************");
        // TODO: implement report generation
    }

    public void shutdown() {
        processorPool.shutdown();
    }
}

// TODO: Use OOP to be extended in the future
// TODO: Use C1, C2, C3, C4 for cycles

