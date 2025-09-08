//Logic
import java.util.*;
import java.io.*;

public class TaskManager {
    private ArrayList<Task> tasks = new ArrayList<>();
    private final String FILE_NAME = "tasks.txt";

    // Constructor -> Load tasks from file when program starts
    public TaskManager() {
        loadTasksFromFile();
    }

    // Add Task
    public void addTask(String desc) {
        tasks.add(new Task(desc));
        saveTasksToFile();
        System.out.println("✅ Task added successfully!");
    }

    // View Tasks
    public void viewTasks() {
        if(tasks.isEmpty()) {
            System.out.println("No tasks available.");
        } else {
            System.out.println("\n📋 To-Do List:");
            for(int i = 0; i < tasks.size(); i++) {
                System.out.println((i+1) + ". " + tasks.get(i));
            }
        }
    }

    // Complete Task
    public void completeTask(int index) {
        if(index > 0 && index <= tasks.size()) {
            tasks.get(index-1).markCompleted();
            saveTasksToFile();
            System.out.println("✅ Task marked as completed!");
        } else {
            System.out.println("⚠ Invalid task number.");
        }
    }

    // Delete Task
    public void deleteTask(int index) {
        if(index > 0 && index <= tasks.size()) {
            tasks.remove(index-1);
            saveTasksToFile();
            System.out.println("✅ Task deleted successfully!");
        } else {
            System.out.println("⚠ Invalid task number.");
        }
    }

    // Save tasks into file
    private void saveTasksToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for(Task t : tasks) {
                writer.write(t.getDescription() + ";" + t.isCompleted());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("⚠ Error saving tasks: " + e.getMessage());
        }
    }

    // Load tasks from file
    private void loadTasksFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if(parts.length == 2) {
                    Task task = new Task(parts[0]);
                    if(Boolean.parseBoolean(parts[1])) {
                        task.markCompleted();
                    }
                    tasks.add(task);
                }
            }
        } catch (FileNotFoundException e) {
            // First run, file doesn’t exist yet → ignore
        } catch (IOException e) {
            System.out.println("⚠ Error loading tasks: " + e.getMessage());
        }
    }
}