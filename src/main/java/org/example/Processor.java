package org.example;

import java.util.Objects;

public class Processor implements Runnable{
    private String ID;
    private Task currentTask; // runningTask or executedTask

    public Processor(String ID) {
        this.ID = ID;
    }

    @Override
    public void run() {
        // TODO: implement the run method
        if(!isIdle()){
            executeCycle();
        }
    }

    // TODO: find a better name
    public boolean isIdle(){
        return currentTask == null;
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
    }

    public String getID() {
        return ID;
    }

    @Override
    public String toString() {
        return "Processor{" +
                "ID=" + ID +
                ", currentTask=" + currentTask +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Processor processor)) return false;
        return Objects.equals(ID, processor.ID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }

}

/**
 * <p>
 * each processor is a thread
 * </p>
 */