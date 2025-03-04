package org.example;

import java.util.List;
import java.util.concurrent.ExecutorService;

public class BasicScheduler extends Scheduler{

    public BasicScheduler(){
        super();
    }

    @Override
    public void scheduleTask(Task task) {
        if (task.isHighPriority()) {
            highPriorityQueue.add(task);
        } else {
            lowPriorityQueue.add(task);
        }
    }

    @Override
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

    @Override
    public void generateQueueReport(){
        if(highPriorityQueue.isEmpty() && lowPriorityQueue.isEmpty()) return;
        System.out.println("Waiting tasks: ");
        for (Task task : highPriorityQueue) {
            System.out.println("\t" + task.getId());
        }
        for(Task task : lowPriorityQueue) {
            System.out.println("\t" + task.getId());
        }
    }
}
