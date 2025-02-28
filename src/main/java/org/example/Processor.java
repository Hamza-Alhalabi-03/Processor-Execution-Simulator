package org.example;

public class Processor implements Runnable{
    private int ID;
    private Task currentTask; // runningTask or executedTask

    @Override
    public void run() {

    }

    public Task getCurrentTask(){
        if (currentTask != null){
            return currentTask;
        }
        return null;
    }

    public void executeCycle(){
        if (currentTask != null){
            currentTask.executeCycle();
        }
        if(currentTask.isCompleted()){
            currentTask = null;
        }
    }
}

/**
 * <p>
 * each processor is a thread
 * </p>
 */