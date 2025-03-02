package org.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.concurrent.ExecutorService;

public class Scheduler {
    private PriorityQueue<Task> highPriorityQueue =
            new PriorityQueue<>(Comparator.comparingInt((Task t) -> t.getExecutionTime()).reversed());
    private PriorityQueue<Task> lowPriorityQueue =
            new PriorityQueue<>(Comparator.comparingInt((Task t) -> t.getExecutionTime()).reversed());

    public Scheduler() {
    }

    public void scheduleTask(Task task) {
        if (task.isHighPriority()) {
            highPriorityQueue.add(task);
        } else {
            lowPriorityQueue.add(task);
        }
    }

    public void assignTasks(List<Processor> processors,
                            ExecutorService processorPool) {
        while (true){
            Processor processor = getProcessor(processors);
            if(processor == null){
                break;
            }
            if (!highPriorityQueue.isEmpty()) {
                Task task = highPriorityQueue.poll();
                processor.assignTask(task);
                processorPool.submit(processor);
            } else if (!lowPriorityQueue.isEmpty()) {
                Task task = lowPriorityQueue.poll();
                processor.assignTask(task);
                processorPool.submit(processor);
            }
            else break;
        }
    }

    private Processor getProcessor(List<Processor> processors){
        for (Processor processor : processors) {
            if (processor.isAvailable()) {
                return processor;
            }
        }
        return null;
    }
}

/**
 * <p>
 *     responsible for determining the assignment of tasks that are
 *     waiting in the queue to processors that are available
 * </p>
 *
 * <p>
 *     should have an association relationship with Task objects. It doesn't own the tasks but works with them
 * </p>
 *
 * <p>
 *     should have an association relationship with Processor objects.
 *     It doesn't own the processors but assigns tasks to available processors
 * </p>
 */
