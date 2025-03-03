package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

import static org.example.ColoredText.printColoredText;

public class BasicSimulator extends Simulator{

    public BasicSimulator(int numberOfProcessors, int numberOfTasks, String schedulingAlgorithm) {
        super(numberOfProcessors, numberOfTasks, schedulingAlgorithm);
        this.scheduler = new BasicScheduler();
    }


    @Override
    public void onClockTick(int currentCycle) {
        this.currentCycle = currentCycle;
        generateReport();
        scheduleTasks();
        scheduler.assignTasks(processors, processorPool);
    }

    @Override
    protected void InitializeProcessors() {
        processorPool = Executors.newFixedThreadPool(numOfProcessors);
        processors = new ArrayList<>();

        for (int i = 1; i <= numOfProcessors; i++) {
            Processor processor = BasicProcessor.createBasicProcessor("P" + i);
            processors.add(processor);
        }
    }

    @Override
    protected void scheduleTasks(){
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

    @Override
    protected void generateReport() {
        System.out.println("\n***************************************");
        System.out.println("Current Cycle: C" + currentCycle);
        System.out.println("***************************************");
        scheduler.generateQueueReport();
    }


}
