package org.example;

import java.util.Objects;

public class Task {
    private final String ID;

    private final int creationTime;
    private int executionTime;
    private final boolean highPriority;
    private boolean isCompleted;


    public Task(String ID, int creationTime, int executionTime, boolean highPriority) {
        this.ID = ID;
        this.creationTime = creationTime;
        this.executionTime = executionTime;
        this.highPriority = highPriority;
        this.isCompleted = false;
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

    public void executeCycle() {

//        if (isCompleted) {
//            throw new IllegalStateException("Task is already completed and cannot be executed further.");
//        }

        if (executionTime > 0) {
            executionTime--;
            isCompleted = (executionTime == 0);
        }

    }

    public boolean isCompleted() {
        return isCompleted;
    }

    @Override
    public String toString() {
        return "Task{" +
                "ID= " + ID +
                ", creationTime= " + creationTime +
                ", highPriority= " + highPriority +
                ", executionTime= " + (isCompleted ? "Completed" : executionTime) +
                '}';

    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Task task)) return false;
        return getID() == task.getID() && getCreationTime() == task.getCreationTime() && isHighPriority() == task.isHighPriority();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getID(), getCreationTime(), isHighPriority());
    }
}
