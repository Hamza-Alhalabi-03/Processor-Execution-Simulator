package org.example;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaskExtractor {
    public static List<Task> extract(String filePath){
        List<Task> tasks = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            int numOfTasks = Integer.parseInt(br.readLine()); // read number of tasks

            for (int task = 1; task <= numOfTasks; task++) {
                String[] taskArguments = br.readLine().split(" ");
                int creationTime = Integer.parseInt(taskArguments[0]);
                int executionTime = Integer.parseInt(taskArguments[1]);
                boolean highPriority = Integer.parseInt(taskArguments[2]) == 1;
                tasks.add(new Task(("T" + task), creationTime, executionTime, highPriority));
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error: File not found - " + filePath);
            return Collections.emptyList();
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return Collections.emptyList();
        }
            
        return tasks;
    }
}
