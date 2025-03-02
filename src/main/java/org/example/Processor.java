package org.example;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

public class Processor implements Runnable{
    private String id;
    private Task currentTask;
    private final AtomicBoolean available;

    public Processor(String id) {
        this.id = id;
        this.available = new AtomicBoolean(true);
    }

    @Override
    public void run() {
        try {
            System.out.println("Processor " + id + " processing: " + currentTask.getId());
            Thread.sleep(currentTask.getProcessingTimeMs());
            currentTask.complete();
            currentTask.removeProcessor();
            System.out.println("Processor " + id + " completed: " + currentTask.getId());
            currentTask = null;
        } catch (InterruptedException e) {
            System.out.println("Processor " + id + " interrupted: " + e.getMessage());
        } finally {
            available.set(true);
        }
    }

    public boolean isAvailable(){
        return available.get();
    }

    public Task getCurrentTask(){
        return currentTask;
    }

    private void executeCycle(){
            currentTask.executeCycle();
            if(currentTask.isCompleted()){
                currentTask.removeProcessor();
//                System.out.println("Task " + currentTask.getID() + " completed on processor " + ID);
//                System.out.println("Processor " + ID + " is idle now");
                currentTask = null;
            }
    }

    public void assignTask(Task task){
        currentTask = task;
        task.assignProcessor(this);
        available.set(false);
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Processor{" +
                "iD=" + id +
                ", currentTask=" + currentTask +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Processor processor)) return false;
        return Objects.equals(id, processor.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

/**
 * <p>
 * each processor is a thread
 * </p>
 */