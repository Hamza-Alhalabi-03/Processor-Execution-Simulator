package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        List<Processor> processors = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            Processor processor = new Processor("P" + i);
            processors.add(processor);

        }
        List<Task> tasks = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Task task = new Task("T" + i, i, i * 2, false); // Example task creation
            tasks.add(task);
        }

        // Assign tasks to available processors
        for (Task task : tasks) {
            for (Processor processor : processors) {
                if (processor.isAvailable()) {
                    processor.assignTask(task);
                    executorService.submit(processor);
                    break;
                }
            }
        }

        executorService.shutdown();

    }
}
