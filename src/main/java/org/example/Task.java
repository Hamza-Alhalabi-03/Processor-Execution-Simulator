package org.example;

import java.util.Objects;

public class Task {
    private final String id;

    private final int creationTime;
    private int executionTime;
    private final boolean highPriority;
    private boolean Completed;
    private Processor assignedProcessor;


    public Task(String id, int creationTime, int executionTime, boolean highPriority) {
        this.id = id;
        this.creationTime = creationTime;
        this.executionTime = executionTime;
        this.highPriority = highPriority;
        this.Completed = false;
    }

    public String getId() {
        return id;
    }

    public int getCreationTime() {
        return creationTime;
    }

    public int getExecutionTime() {
        return executionTime;
    }

    public int getProcessingTimeMs() {
        return executionTime * 1000;
    }

    public void complete() {
        this.executionTime = 0;
        this.Completed = true;
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
                "id= " + id +
                ", creationTime= " + creationTime +
                ", highPriority= " + highPriority +
                ", executionTime= " + (Completed ? "Completed" : executionTime) +
                '}';

    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Task task)) return false;
        return Objects.equals(getId(), task.getId()) && getCreationTime() == task.getCreationTime() && isHighPriority() == task.isHighPriority();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCreationTime(), isHighPriority());
    }
}

// TODO: create static method to create processor
