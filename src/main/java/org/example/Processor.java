package org.example;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import static org.example.ColoredText.printColoredText;


public abstract class Processor implements Runnable{
    protected String id;
    protected Task currentTask;
    protected final AtomicBoolean available;

    protected Processor(String id) {
        this.id = id;
        this.available = new AtomicBoolean(true);
    }

    public boolean isAvailable(){
        return available.get();
    }

    public void assignTask(Task task){
        currentTask = task;
        task.assignProcessor(this);
        available.set(false);
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