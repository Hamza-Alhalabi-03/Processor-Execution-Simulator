package org.example;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.concurrent.ExecutorService;

public abstract class Scheduler {
    protected PriorityQueue<Task> highPriorityQueue =
            new PriorityQueue<>(Comparator.comparingInt((Task t) -> t.getExecutionTime()).reversed());
    protected PriorityQueue<Task> lowPriorityQueue =
            new PriorityQueue<>(Comparator.comparingInt((Task t) -> t.getExecutionTime()).reversed());

    protected Scheduler() {
    }

    public abstract void scheduleTask(Task task);

    public abstract void assignTasks(List<Processor> processors, ExecutorService processorPool);

    protected Processor getProcessor(List<Processor> processors){
        for (Processor processor : processors) {
            if (processor.isAvailable()) {
                return processor;
            }
        }
        return null;
    }

    public abstract void generateQueueReport();
}


