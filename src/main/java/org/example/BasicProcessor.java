package org.example;

import static org.example.ColoredText.printColoredText;

public class BasicProcessor extends Processor{
    protected BasicProcessor(String id){
        super(id);
    }

    public static Processor createBasicProcessor(String id){
        return new BasicProcessor(id);
    }

    protected void execute() throws InterruptedException{
        synchronized (this){
            executeCycle();
            while (!currentTask.isCompleted()) {
                printColoredText("Task " + currentTask.getId() + " remaining time: " + currentTask.getExecutionTime(), "yellow");
                executeCycle();
                Thread.sleep(1000);
            }
        }
        currentTask.removeProcessor();
    }

    private void executeCycle() throws InterruptedException{
        currentTask.executeCycle();
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

}
