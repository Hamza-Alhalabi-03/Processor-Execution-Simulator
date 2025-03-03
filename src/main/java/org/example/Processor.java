package org.example;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import static org.example.ColoredText.printColoredText;


public class Processor implements Runnable{
    private String id;
    private Task currentTask;
    private final AtomicBoolean available;

    public Processor(String id) {
        this.id = id;
        this.available = new AtomicBoolean(true);
    }

    private void execute() throws InterruptedException{
        synchronized (this){
        executeCycle();
        while (!currentTask.isCompleted()) {
            printColoredText("task " + currentTask.getId() + " remaining time: " + currentTask.getExecutionTime(), "yellow");
            executeCycle();
            Thread.sleep(1000);
        }
        }
        currentTask.removeProcessor();
    }

    @Override
    public void run() {
        try {
            printColoredText("Processor " + id + " started processing: " + currentTask.getId(), "green");
            execute();
            printColoredText("Processor " + id + " completed task: " + currentTask.getId(), "red");
        } catch (InterruptedException e) {
            printColoredText("Processor " + id + " interrupted: " + e.getMessage(), "red");
        } finally {
            currentTask = null;
            available.set(true);
        }
    }

    public boolean isAvailable(){
        return available.get();
    }

    public Task getCurrentTask(){
        return currentTask;
    }

    private void executeCycle() throws InterruptedException{
            currentTask.executeCycle();
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
                "id=" + id +
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

// TODO: create static method to create processor
