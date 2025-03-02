package org.example;

import java.util.Objects;

public class Task {
    private final String ID;

    private final int creationTime;
    private int executionTime;
    private final boolean highPriority;
    private boolean Completed;
    private Processor assignedProcessor;


    public Task(String ID, int creationTime, int executionTime, boolean highPriority) {
        this.ID = ID;
        this.creationTime = creationTime;
        this.executionTime = executionTime;
        this.highPriority = highPriority;
        this.Completed = false;
    }

    public String getID() {
        return ID;
    }

    public int getCreationTime() {
        return creationTime;
    }

    public int getExecutionTime() {
        return executionTime;
    }

    public boolean isHighPriority() {
        return highPriority;
    }


    public Processor getAssignedProcessor() {
        return assignedProcessor;
    }

    public void assignProcessor(Processor assignedProcessor) {
        this.assignedProcessor = assignedProcessor;
    }

    public void removeProcessor() {
        this.assignedProcessor = null;
    }

    public void executeCycle() {
        if (!isCompleted()) {
            executionTime--;
            Completed = executionTime == 0;
        }

    }

    public boolean isCompleted() {
        return Completed;
    }

    @Override
    public String toString() {
        return "Task{" +
                "ID= " + ID +
                ", creationTime= " + creationTime +
                ", highPriority= " + highPriority +
                ", executionTime= " + (Completed ? "Completed" : executionTime) +
                '}';

    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Task task)) return false;
        return Objects.equals(getID(), task.getID()) && getCreationTime() == task.getCreationTime() && isHighPriority() == task.isHighPriority();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getID(), getCreationTime(), isHighPriority());
    }
}
