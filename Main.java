//Menu
import java.util.Scanner;public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TaskManager manager = new TaskManager(); // will load tasks automatically

        while(true) {
            System.out.println("\n=== To-Do List ===");
            System.out.println("1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Complete Task");
            System.out.println("4. Delete Task");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch(choice) {
                case 1:
                    System.out.print("Enter task description: ");
                    String desc = sc.nextLine();
                    manager.addTask(desc);
                    break;
                case 2:
                    manager.viewTasks();
                    break;
                case 3:
                    System.out.print("Enter task number to complete: ");
                    int comp = sc.nextInt();
                    manager.completeTask(comp);
                    break;
                case 4:
                    System.out.print("Enter task number to delete: ");
                    int del = sc.nextInt();
                    manager.deleteTask(del);
                    break;
                case 5:
                    System.out.println("Exiting... Goodbye! 👋");
                    System.exit(0);
                default:
                    System.out.println("⚠ Invalid choice. Try again.");
            }
        }
    }
}